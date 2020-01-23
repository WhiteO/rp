package de.whiteo.rp.util;

import org.pcap4j.packet.Packet;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.concurrent.ConcurrentHashMap;

import static org.pcap4j.util.ByteArrays.toHexString;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

    public static void run(Packet packet, ConcurrentHashMap<Integer, String> map) {

        String hexString = toHexString(packet.getRawData(), "");
        String string = convertHexToString(hexString);

        System.out.println(packet.getHeader().toString());


        if (map.isEmpty()) {
            map.put(1, string);
        } else {
            String oldString = map.get(1);
            String newString = oldString.concat(string);
            map.replace(1,oldString,newString);
        }

        //System.out.println(map.get(1));

       /* int bind = string.indexOf("<crs:bind bindID=");
        int clientVerId = string.indexOf("<crs:clientVerID value=");
        int firstId = string.indexOf("<crs:first value=");
        int classId = string.indexOf("<crs:classID value=");
        int parentId = string.indexOf("<crs:parentID value=");
        int nameBegin = string.indexOf("<crs:name value=");
        int nameLast = string.indexOf("><crs:pos>");
        int commentBegin = string.indexOf("<crs:comment>");
        int commentLast = string.indexOf("</crs:comment>");

        System.out.println(string);

        String ss1 = string.substring(bind+18, bind+54);
        String ss2 = string.substring(clientVerId+24, clientVerId+60);
        String ss3 = string.substring(firstId+18, firstId+54);
        String ss4 = string.substring(classId+20, classId+56);
        String ss5 = string.substring(parentId+21, parentId+57);
        String ss6 = string.substring(nameBegin+17, nameLast-2);*/


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
