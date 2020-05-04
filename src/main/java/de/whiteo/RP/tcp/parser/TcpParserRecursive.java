package de.whiteo.rp.tcp.parser;

import de.whiteo.rp.dto.PacketDTO;
import java.util.Map;
import java.util.UUID;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpParserRecursive {

  public static void recursivePocketedChildNodes(Map<String, String> map, Node node) {
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

  public static void recursivePocketedChildNodesWithVersions(PacketDTO packetDTO, Node node) {
    NodeList childNodes = node.getChildNodes();
    for (int j = 0; j < childNodes.getLength(); j++) {
      Node childNode = childNodes.item(j);
      if (childNode.getNodeName().equals("crs:verNum")) {
        packetDTO.setVerNumCommit(childNode.getTextContent());
      } else if (childNode.getNodeName().equals("crs:versionID")) {
        packetDTO.setClientVerId(
            UUID.fromString(childNode.getAttributes().getNamedItem("value").getTextContent()));
      } else {
        recursivePocketedChildNodesWithVersions(packetDTO, childNode);
      }
    }
  }
}