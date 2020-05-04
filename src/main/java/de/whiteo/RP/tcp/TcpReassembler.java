package de.whiteo.rp.tcp;

import static org.pcap4j.util.ByteArrays.toHexString;

import java.util.List;
import javafx.util.Pair;
import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpReassembler {

  public static Pair<String, String> doReassemble(List<TcpPacket> packets) {
    StringBuilder stringBuilder = new StringBuilder();
    for (TcpPacket p : packets) {
      String hexString = toHexString(p.getPayload().getRawData(), "");
      String convertedString = convertHexToString(hexString);
      stringBuilder.append(convertedString);
    }
    Pair<String, String> pairToReturn = null;
    String unpreparedString = stringBuilder.toString();
    int indexStartXml = unpreparedString.indexOf("<?xml");
    int indexEndXml = unpreparedString.indexOf("fS²¦");
    if (-1 != indexStartXml && -1 != indexEndXml) {
      if (unpreparedString.contains(PacketType.PACKET_COMMIT.value())) {
        pairToReturn = new Pair<>("commit", unpreparedString.substring(indexStartXml, indexEndXml));
      } else if (unpreparedString.contains(PacketType.PACKET_CHANGE_VERSION.value())) {
        pairToReturn = new Pair<>("version_change",
            unpreparedString.substring(indexStartXml, indexEndXml));
      } else if (unpreparedString.contains(PacketType.PACKET_WITH_VERSIONS.value())) {
        pairToReturn = new Pair<>("versions",
            unpreparedString.substring(indexStartXml, indexEndXml));
      }
    }
    return pairToReturn;
  }

  public static String convertHexToString(String hex) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < hex.length() - 1; i += 2) {
      String output = hex.substring(i, (i + 2));
      int decimal = Integer.parseInt(output, 16);
      sb.append((char) decimal);
    }
    return sb.toString();
  }
}