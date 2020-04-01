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

  public static PacketDTO parseXmlFromPacket(String packetText) {
    PacketDTO result = null;

    Document docXml = DocumentConverter.stringXmlToDocumentConvert(packetText);
    if (null != docXml) {
      PacketDTO packetDTO = new PacketDTO(
          getBindFromPacket(docXml.getElementsByTagName("crs:bind")),
          getClientVerIDFromPacket(docXml.getElementsByTagName("crs:clientVerID")),
          getCommentFromPacket(docXml.getElementsByTagName("crs:comment")),
          getUserFromPacket(docXml.getElementsByTagName("crs:auth")),
          getAliasFromPacket(docXml.getElementsByTagName("crs:call")));
      processData(packetDTO, docXml.getElementsByTagName("crs:changes"));
      if (null != packetDTO.getClientVerId()) {
        result = packetDTO;
      }
    }
    return result;
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
    UUID uuidClientVerID = null;
    if (0 < nodesList.getLength()) {
      Element item = (Element) nodesList.item(0);
      uuidClientVerID = UUID
          .fromString(item.getAttributes().getNamedItem("value").getTextContent());
    }
    return uuidClientVerID;
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