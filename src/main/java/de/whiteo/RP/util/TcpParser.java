package de.whiteo.rp.util;

import de.whiteo.rp.service.PacketDTO;
import java.util.HashMap;
import java.util.Map;
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
      packetDTO.setClientVerId(
          getClientVerIDFromPacket(docXml.getElementsByTagName("crs:clientVerID")));
      packetDTO.setBindID(getBindFromPacket(docXml.getElementsByTagName("crs:bind")));
      packetDTO.setComment(getCommentFromPacket(docXml.getElementsByTagName("crs:comment")));
      packetDTO.setUser(getUserFromPacket(docXml.getElementsByTagName("crs:auth")));
      packetDTO.setAlias(getAliasFromPacket(docXml.getElementsByTagName("crs:call")));
      processData(packetDTO, docXml.getElementsByTagName("crs:changes"));
    }
    return packetDTO;
  }

  public static PacketDTO parseChangeVerXmlFromPacket(String packetText) {
    PacketDTO packetDTO = null;
    Document docXml = DocumentConverter.stringXmlToDocumentConvert(packetText);
    if (null != docXml) {
      packetDTO = Executor.getPacketDTOFromBase(
          getClientVerIDFromPacket(docXml.getElementsByTagName("crs:clientVerID")));
      packetDTO.setVerNumCommit(1);
      packetDTO.setNameCommit("");
      packetDTO.setCommentNameCommit("");
      packetDTO.setUserChangeCommit("");
    }
    return packetDTO;
  }

  private static Integer getVerNumCommitFromPacket(NodeList nodesList) {
    Integer verNum = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      verNum = Integer.parseInt(item.getAttributes().getNamedItem("verNum").getTextContent());
    }
    return verNum;
  }

  private static String getNameCommitFromPacket(NodeList nodesList) {
    String name = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      name = item.getAttributes().getNamedItem("name").getTextContent();
    }
    return name;
  }

  private static String getCommentNameCommitFromPacket(NodeList nodesList) {
    String commentName = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      commentName = item.getAttributes().getNamedItem("comment").getTextContent();
    }
    return commentName;
  }

  private static UUID getUserIdFromPacket(NodeList nodesList) {
    UUID uuidUserId = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      uuidUserId = UUID
          .fromString(item.getAttributes().getNamedItem("UserID").getTextContent());
    }
    return uuidUserId;
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

  public static void processData(PacketDTO packetDTO, NodeList nodesList) {
    for (int i = 0; i < nodesList.getLength(); i++) {
      Element item = (Element) nodesList.item(i);
      NodeList itemsChildNodes = item.getChildNodes();
      for (int j = 0; j < itemsChildNodes.getLength(); j++) {
        Node childNode = itemsChildNodes.item(j);
        Map<String, String> tempMap = new HashMap<>();
        TcpParserRecursive.recursivePocketedChildNodes(tempMap, childNode);
        String verId =
            null == tempMap.get("verId") ? Miscellaneous.getRandomVerId() : tempMap.get("verId");
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
}