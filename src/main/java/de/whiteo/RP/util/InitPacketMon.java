package de.whiteo.rp.util;

import de.whiteo.rp.config.SpringContext;
import de.whiteo.rp.controller.PacketController;
import de.whiteo.rp.service.PacketDTO;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

  private PacketController packetController;
  private final BigInteger MIN_FOR_RANDOM = new BigInteger("1000000000000000000000000000000");
  private final BigInteger MAX_FOR_RANDOM = new BigInteger("9000000000000000000000000000000");

  public InitPacketMon() {
    ApplicationContext context = SpringContext.getAppContext();
    packetController = (PacketController) context.getBean("packetController");
  }

  public void run(Packet packet, Map<Integer, TcpSession> sessions) {
    TcpPacket tcp = packet.get(TcpPacket.class);
    int window = tcp.getHeader().getWindowAsInt();
    TcpSession session;

    if (sessions.isEmpty()) {
      session = new TcpSession();
      session.setWindow(window);
      sessions.put(1, session);
    } else {
      session = sessions.get(1);
    }

    if (window == session.getWindow()) {
      session.getPackets().add(tcp);
    }

    String packetText = TcpReassembler.doReassemble(session.getPackets());

    if (!packetText.isEmpty()) {

      Document docXml = stringXmlToDocumentConvert(packetText);

      if (docXml != null) {

        PacketDTO packetDTO = new PacketDTO();

        packetDTO.setClientVerId(
            getClientVerIDFromPacket(docXml.getElementsByTagName("crs:clientVerID")));
        packetDTO.setBindID(getBindFromPacket(docXml.getElementsByTagName("crs:bind")));
        packetDTO.setComment(getCommentFromPacket(docXml.getElementsByTagName("crs:comment")));
        packetDTO.setUser(getUserFromPacket(docXml.getElementsByTagName("crs:auth")));
        processData(packetDTO, docXml.getElementsByTagName("crs:changes"));

        if (null != packetDTO.getClientVerId()) {
          packetController.addPacket(packetDTO);
        }
        sessions.clear();
      }
    }
  }

  private Document stringXmlToDocumentConvert(String stringXml) {
    Document document = null;
    try {
      String preparedString = prepareXmlString(stringXml);

      InputSource is = new InputSource();
      is.setCharacterStream(new StringReader(preparedString));

      DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = df.newDocumentBuilder();
      document = builder.parse(is);

    } catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();
    }
    return document;
  }

  private String prepareXmlString(String stringXml) {
    return new String(stringXml.getBytes(StandardCharsets.ISO_8859_1));
  }

  private UUID getClientVerIDFromPacket(NodeList nodesList) {
    UUID uuidClientVerID = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      uuidClientVerID = UUID.fromString(item.getAttributes()
          .getNamedItem("value").getTextContent());
    }
    return uuidClientVerID;
  }

  private UUID getBindFromPacket(NodeList nodesList) {
    UUID uuidBind = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      uuidBind = UUID.fromString(item.getAttributes().getNamedItem("bindID").getTextContent());
    }
    return uuidBind;
  }

  private String getCommentFromPacket(NodeList nodesList) {
    String comment = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      comment = item.getTextContent();
    }
    return comment;
  }

  private String getUserFromPacket(NodeList nodesList) {
    String user = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      user = item.getAttributes().getNamedItem("user").getTextContent();
    }
    return user;
  }

  private void processData(PacketDTO packetDTO, NodeList nodesList) {
    for (int i = 0; i < nodesList.getLength(); i++) {
      Element item = (Element) nodesList.item(i);

      NodeList itemsChildNodes = item.getChildNodes();
      for (int j = 0; j < itemsChildNodes.getLength(); j++) {
        Node childNode = itemsChildNodes.item(j);

        Map<String, String> tempMap = new HashMap<>();
        recursivePocketedChildNodes(tempMap, childNode);

        String verId = null == tempMap.get("verId") ? getRandomVerId() : tempMap.get("verId");
        packetDTO.getClassIdMap().put(verId, UUID.fromString(tempMap.get("classId")));
        packetDTO.getObjectIdMap().put(verId, UUID.fromString(tempMap.get("objectId")));
        packetDTO.getNameMap().put(verId, tempMap.get("name"));
        Integer actionInt =
            null == tempMap.get("action") ? 0 : Integer.parseInt(tempMap.get("action"));
        packetDTO.getActionMap().put(verId, actionInt);
        packetDTO.getRemovedMap().put(verId, Boolean.parseBoolean(tempMap.get("removed")));
      }
    }
  }

  private String getRandomVerId() {
    BigInteger bigInteger = MAX_FOR_RANDOM.subtract(MIN_FOR_RANDOM);
    Random randNum = new Random();
    int len = MAX_FOR_RANDOM.bitLength();
    BigInteger res = new BigInteger(len, randNum);
    if (res.compareTo(MIN_FOR_RANDOM) < 0) {
      res = res.add(MIN_FOR_RANDOM);
    }
    if (res.compareTo(bigInteger) >= 0) {
      res = res.mod(bigInteger).add(MIN_FOR_RANDOM);
    }
    String INIT_VER_ID = "a00000000";
    return res.toString() + INIT_VER_ID;
  }

  private void recursivePocketedChildNodes(Map<String, String> map, Node node) {
    NodeList childNodes = node.getChildNodes();
    for (int j = 0; j < childNodes.getLength(); j++) {
      Node childNode = childNodes.item(j);
      if (childNode.getNodeName().equals("crs:name") && !map.containsKey("name")) {
        map.put("name", childNode.getAttributes().getNamedItem("value").getTextContent());
      } else if (childNode.getNodeName().equals("crs:classID") && !map.containsKey("classId")) {
        map.put("classId", childNode.getAttributes().getNamedItem("value").getTextContent());
      } else if (childNode.getNodeName().equals("crs:id") && !map.containsKey("objectId")) {
        map.put("objectId", childNode.getAttributes().getNamedItem("value").getTextContent());
      } else if (childNode.getNodeName().equals("crs:verID") && !map.containsKey("verId")) {
        map.put("verId", childNode.getAttributes().getNamedItem("value").getTextContent());
      } else if (childNode.getNodeName().equals("crs:action") && !map.containsKey("action")) {
        map.put("action", childNode.getAttributes().getNamedItem("value").getTextContent());
      } else if (childNode.getNodeName().equals("crs:removed") && !map.containsKey("removed")) {
        map.put("removed", childNode.getAttributes().getNamedItem("value").getTextContent());
      } else {
        recursivePocketedChildNodes(map, childNode);
      }
    }
  }
}