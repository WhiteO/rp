package de.whiteo.rp.util;

import java.util.List;
import org.pcap4j.packet.TcpPacket;
import static org.pcap4j.util.ByteArrays.toHexString;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpReassembler {

    public static String doReassemble(List<TcpPacket> packets) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TcpPacket p : packets) {
            String hexString = toHexString(p.getPayload().getRawData(), "");
            String string = convertHexToString(hexString);
            stringBuilder.append(string);
        }
        int indexStartXml = stringBuilder.toString().indexOf("<?xml");
        String resultString = stringBuilder.toString().substring(indexStartXml, stringBuilder.toString().length() - 4);
        String lastElementsInString = "</crs:comment><crs:features/></crs:params></crs:call>";
        String stringToReturn = "";

        if (resultString.contains(lastElementsInString)) {
            stringToReturn = resultString;
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