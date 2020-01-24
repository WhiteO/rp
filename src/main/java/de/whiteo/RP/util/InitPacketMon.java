package de.whiteo.rp.util;

import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Map;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

    public static void run(Packet packet, Map<Integer,
            TcpSession> sessions) {

        //String hexString = toHexString(packet.get(TcpPacket.class).getPayload().getRawData(), "");
        //String string = convertHexToString(hexString);

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
        } else {
            Document document = convertXMLFileToXMLDocument(TcpReassembler.doReassemble(session.getPackets()));
            int ina = 1;
        }

        /*session = sessions.get(port);
        long seq = tcp.getHeader().getSequenceNumberAsLong();
        session.setSeqNumOffset(seq + 1L);
        session.getPackets(isToServer).add(tcp);*/

        /*byte[] reassembledPayload
                = TcpReassembler.doReassemble(
                session.getPackets(),
                session.getSeqNumOffset(),
                tcp.getHeader().getSequenceNumberAsLong(),
                tcp.getPayload().length()
        );*/

        //System.out.println(packet.get(TcpPacket.class).getPayload());

        //аSystem.out.println(packet.get(TcpPacket.class).getHeader().toString());

        /*if (map.isEmpty()) {
            map.put(1, string);
        } else {
            String oldString = map.get(1);
            String newString = oldString.concat(string);
            map.replace(1,oldString,newString);
        }

        //System.out.println(map.get(1));

        int bind = string.indexOf("<crs:bind bindID=");
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

    private static Document convertXMLFileToXMLDocument(String string) {
        //Parser that produces DOM object trees from XML content
        //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance

        try {

            Document dom;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Create DocumentBuilder with default configuration
            //builder = factory.newDocumentBuilder();


            File tempFile = File.createTempFile("t_"+Math.random() * (10000 - 1) + 1, ".xml");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            bw.write(string);
            bw.close();

            //Parse the content to Document object
            dom = db.parse(tempFile);

            tempFile.deleteOnExit();

            return dom;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
