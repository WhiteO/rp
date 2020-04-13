package de.whiteo.rp.util;

import static org.pcap4j.util.ByteArrays.toHexString;

import java.util.List;
import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpReassembler {

  private static final String LAST_ELEMENT_IN_STRING = "</crs:call>fS²¦";
  private final static String POST = "POST";
  private final static String IS_COMMIT = "DevDepot_commitObjects";

  public static String doReassemble(List<TcpPacket> packets) {
    StringBuilder stringBuilder = new StringBuilder();

    for (TcpPacket p : packets) {
      String hexString = toHexString(p.getPayload().getRawData(), "");
      String convertedString = convertHexToString(hexString);
      stringBuilder.append(convertedString);
    }

    String stringToReturn = "";
    if (stringBuilder.toString().contains(IS_COMMIT) && stringBuilder.toString()
        .contains(LAST_ELEMENT_IN_STRING) && stringBuilder.toString()
        .contains(POST)) {
      int indexStartXml = stringBuilder.toString().indexOf("<?xml");
      int indexEndXml = stringBuilder.toString().indexOf("fS²¦");
      stringToReturn = stringBuilder.toString()
          .substring(indexStartXml, indexEndXml);
    }
    return stringToReturn;
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