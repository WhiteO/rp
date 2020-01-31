package de.whiteo.rp.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import de.whiteo.rp.service.PacketDTO;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

    public static void run(Packet packet, Map<Integer, TcpSession> sessions) {

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

            String str = TcpReassembler.doReassemble(session.getPackets());


try {

    String str2 =new String(str.getBytes(StandardCharsets.ISO_8859_1));
    /*File tempFile = null;
    tempFile = File.createTempFile("t_" + Math.random() * (10000 - 1) + 1, ".xml");
    BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    bw.write(str2);
    bw.close();*/

    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(str2));

    //XStream xStream = new XStream();
    //Annotations.configureAliases(xStream, PacketDTO.class);
    //PacketDTO packetDTO = (PacketDTO) xStream.fromXML(str2);

    DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = df.newDocumentBuilder();
    Document document = builder.parse(is);

    NodeList idNodesList = document.getElementsByTagName("crs:id");
    NodeList bindNodesList = document.getElementsByTagName("crs:bind");
    NodeList clientVerIDNodesList = document.getElementsByTagName("crs:clientVerID");
    NodeList commentNodesList = document.getElementsByTagName("crs:comment");
    NodeList nameNodesList = document.getElementsByTagName("crs:name");
    NodeList parentIDNodesList = document.getElementsByTagName("crs:parentID");

    for (int i = 0; i < idNodesList.getLength(); i++) {
        Element item = (Element) idNodesList.item(i);
        System.out.println(item.getAttribute("value"));
    }

    for (int i = 0; i < bindNodesList.getLength(); i++) {
        Element item = (Element) bindNodesList.item(i);
        System.out.println(item.getAttribute("bindID"));
    }

    for (int i = 0; i < clientVerIDNodesList.getLength(); i++) {
        Element item = (Element) clientVerIDNodesList.item(i);
        System.out.println(item.getAttribute("value"));
    }

    for (int i = 0; i < commentNodesList.getLength(); i++) {
        Element item = (Element) commentNodesList.item(i);
        System.out.println(item.getTextContent());
    }

    for (int i = 0; i < nameNodesList.getLength(); i++) {
        Element item = (Element) nameNodesList.item(i);
        System.out.println(item.getAttribute("value"));
    }

    for (int i = 0; i < parentIDNodesList.getLength(); i++) {
        Element item = (Element) parentIDNodesList.item(i);
        System.out.println(item.getAttribute("value"));
    }


    document.getElementsByTagName("crs:id").getLength();
    /*document.getElementsByTagName("crs:bind").getLength();
    document.getElementsByTagName("crs:clientVerID").getLength();
    document.getElementsByTagName("crs:comment").getLength();
    document.getElementsByTagName("crs:name").getLength();
    document.getElementsByTagName("crs:parentID").getLength();*/

    //tempFile.();
    //document.getElementsByTagName("crs:name").item(0).getAttributes().getNamedItem("value").getTextContent()
    //document.getElementsByTagName("crs:bind").item(0).getAttributes().getNamedItem("bindID").getTextContent()
    //document.getElementsByTagName("crs:id").item(0).getAttributes().getNamedItem("value").getTextContent()
    //document.getElementsByTagName("crs:clientVerID").item(0).getAttributes().getNamedItem("value").getTextContent()
    //document.getElementsByTagName("crs:comment").item(0).getTextContent()
    //document.getElementsByTagName("crs:parentID").item(1).getAttributes().getNamedItem("value").getTextContent()


    } catch (IOException | ParserConfigurationException | SAXException e) {
    e.printStackTrace();
}

        }

        /*
            File tempFile = null;
    tempFile = File.createTempFile("t_" + Math.random() * (10000 - 1) + 1, ".xml");
    BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    bw.write(str);
    bw.close();
    tempFile.deleteOnExit();

    // create an instance of the xml file
    File file = new File("myfile.xml");
// create a binary input stream
    FileInputStream fis = new FileInputStream(file);
// buffering for efficiency
    BufferedInputStream in = new BufferedInputStream(fis);
// get an instance of the parser
         */

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


}
