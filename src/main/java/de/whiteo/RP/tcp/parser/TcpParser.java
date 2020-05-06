package de.whiteo.rp.tcp.parser;

import de.whiteo.rp.dto.PacketDTO;
import de.whiteo.rp.random.RandomIdGenerator;
import de.whiteo.rp.converter.DocumentConverter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpParser {

  public static PacketDTO parseCommitXmlFromPacket(String packetText) {
    PacketDTO packetDTO = null;
    Document docXml = DocumentConverter.stringXmlToDocumentConvert(packetText);
    if (null != docXml) {
      packetDTO = new PacketDTO();
      packetDTO.setNews(true);
      packetDTO.setService(false);
      packetDTO.setUpdate(false);
      packetDTO.setClientVerId(
          getClientVerIDFromPacket(docXml.getElementsByTagName("crs:clientVerID")));
      packetDTO.setBindID(getBindFromPacket(docXml.getElementsByTagName("crs:bind")));
      packetDTO.setComment(getCommentFromPacket(docXml.getElementsByTagName("crs:comment")));
      packetDTO.setUser(getUserFromPacket(docXml.getElementsByTagName("crs:auth")));
      packetDTO.setAlias(getAliasFromPacket(docXml.getElementsByTagName("crs:call")));
      processData(true, packetDTO, docXml.getElementsByTagName("crs:changes"));
    }
    return packetDTO;
  }

  public static PacketDTO parseVersionChangeXmlFromPacket(String packetText) {
    PacketDTO packetDTO = null;
    Document docXml = DocumentConverter.stringXmlToDocumentConvert(packetText);
    if (null != docXml) {
      packetDTO = new PacketDTO();
      packetDTO.setUpdate(true);
      packetDTO.setNews(false);
      packetDTO.setService(false);
      packetDTO.setClientVerId(
          getClientVerIDFromPacket(docXml.getElementsByTagName("crs:versionID")));
      packetDTO.setUserChangeCommit(
          getUserFromPacket(docXml.getElementsByTagName("crs:auth")));
      processData(false, packetDTO, docXml.getElementsByTagName("crs:newVersion"));
    }
    return packetDTO;
  }

  public static Set<PacketDTO> parseVersionsXmlFromPacket(String packetText) {
    Set<PacketDTO> packetDTOSet = null;
    Document docXml = DocumentConverter.stringXmlToDocumentConvert(packetText);
    if (null != docXml) {
      packetDTOSet = new HashSet<>();
      pocketedChildNodesWithVersions(packetDTOSet, docXml.getElementsByTagName("crs:versions"));
    }
    return packetDTOSet;
  }

  private static String getAliasFromPacket(NodeList nodesList) {
    String alias = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      alias = item.getAttributes().getNamedItem("alias").getTextContent();
    }
    return alias;
  }

  private static UUID getClientVerIDFromPacket(NodeList nodesList) {
    UUID uuidClientVerId = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      uuidClientVerId = UUID
          .fromString(item.getAttributes().getNamedItem("value").getTextContent());
    }
    return uuidClientVerId;
  }

  private static UUID getBindFromPacket(NodeList nodesList) {
    UUID uuidBind = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      uuidBind = UUID.fromString(item.getAttributes().getNamedItem("bindID").getTextContent());
    }
    return uuidBind;
  }

  private static String getCommentFromPacket(NodeList nodesList) {
    String comment = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      comment = item.getTextContent();
    }
    return comment;
  }

  private static String getUserFromPacket(NodeList nodesList) {
    String user = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      user = item.getAttributes().getNamedItem("user").getTextContent();
    }
    return user;
  }

  private static void processData(boolean newPacket, PacketDTO packetDTO, NodeList nodesList) {
    for (int i = 0; i < nodesList.getLength(); i++) {
      Element item = (Element) nodesList.item(i);
      NodeList itemsChildNodes = item.getChildNodes();
      for (int j = 0; j < itemsChildNodes.getLength(); j++) {
        Node childNode = itemsChildNodes.item(j);
        Map<String, String> tempMap = new HashMap<>();
        if (newPacket) {
          TcpParserRecursive.recursivePocketedChildNodes(tempMap, childNode);
          String verId =
              null == tempMap.get("verId") ? RandomIdGenerator.getRandomVerId()
                  : tempMap.get("verId");
          packetDTO.getClassIdMap().put(verId, UUID.fromString(tempMap.get("classId")));
          packetDTO.getObjectIdMap().put(verId, UUID.fromString(tempMap.get("objectId")));
          packetDTO.getNameMap().put(verId, tempMap.get("name"));
          packetDTO.setDate(LocalDateTime.now());
          Integer actionInt =
              null == tempMap.get("action") ? 0 : Integer.parseInt(tempMap.get("action"));
          packetDTO.getActionMap().put(verId, actionInt);
          packetDTO.getRemovedMap().put(verId, Boolean.parseBoolean(tempMap.get("removed")));
        } else {
          if (1 == j) {
            pocketedChildNodes(tempMap, childNode);
            packetDTO.setNameCommit(tempMap.get("nameCommit"));
            packetDTO.setCommentNameCommit(tempMap.get("CommentNameCommit"));
            packetDTO.setDateChangeNameCommit(LocalDateTime.now());
          }
        }
      }
    }
  }

  private static void pocketedChildNodes(Map<String, String> map, Node node) {
    NodeList childNodes = node.getChildNodes();
    for (int j = 0; j < childNodes.getLength(); j++) {
      Node childNode = childNodes.item(j);
      if (childNode.getNodeName().equals("crs:name") && !map.containsKey("nameCommit")) {
        map.put("nameCommit",
            childNode.getAttributes().getNamedItem("value").getTextContent());
      } else if (childNode.getNodeName().equals("crs:comment") && !map
          .containsKey("CommentNameCommit")) {
        map.put("CommentNameCommit", childNode.getTextContent());
      }
    }
  }

  private static void pocketedChildNodesWithVersions(Set<PacketDTO> packetDTOSet,
      NodeList nodesList) {
    for (int i = 0; i < nodesList.getLength(); i++) {
      Element item = (Element) nodesList.item(i);
      NodeList itemsChildNodes = item.getChildNodes();
      for (int j = 0; j < itemsChildNodes.getLength(); j++) {
        Node childNode = itemsChildNodes.item(j);
        PacketDTO packetDTO = new PacketDTO();
        packetDTO.setService(true);
        packetDTO.setUpdate(false);
        packetDTO.setNews(false);
        TcpParserRecursive.recursivePocketedChildNodesWithVersions(packetDTO, childNode);
        packetDTOSet.add(packetDTO);
      }
    }
  }
}