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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

    private PacketController packetController;

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
            long seq = tcp.getHeader().getSequenceNumberAsLong();
            session.setSeqNumOffset(seq + 1L);
        }

        String packetText = TcpReassembler.doReassemble(session.getPackets());

        if (!packetText.isEmpty()) {

            Document docXml = stringXmlToDocumentConvert(packetText);

            if (docXml != null) {

                PacketDTO packetDTO = new PacketDTO();
                packetDTO.setClientVerId(getClientVerIDFromPacket(docXml.getElementsByTagName("crs:clientVerID")));
                packetDTO.setBindID(getBindFromPacket(docXml.getElementsByTagName("crs:bind")));
                packetDTO.setComment(getCommentFromPacket(docXml.getElementsByTagName("crs:comment")));

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

    private void processData(PacketDTO packetDTO, NodeList nodesList) {
        for (int i = 0; i < nodesList.getLength(); i++) {
            Element item = (Element) nodesList.item(i);

            NodeList itemsChildNodes = item.getChildNodes();
            for (int j = 0; j < itemsChildNodes.getLength(); j++) {
                Node childNode = itemsChildNodes.item(j);

                Map<String, String> tempMap = new HashMap<>();
                recursivePocketedChildNodes(tempMap, childNode);

                packetDTO.getClassIdMap().put(tempMap.get("verId"), UUID.fromString(tempMap.get("classId")));
                packetDTO.getObjectIdMap().put(tempMap.get("verId"), UUID.fromString(tempMap.get("objectId")));
                packetDTO.getNameMap().put(tempMap.get("verId"), tempMap.get("name"));
            }
        }
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
            } else {
                recursivePocketedChildNodes(map, childNode);
            }
        }
    }
}

