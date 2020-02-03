package de.whiteo.rp.util;

import de.whiteo.rp.service.PacketDTO;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

    public static void run(Packet packet, Map<Integer, TcpSession> sessions) {
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
        }

        String packetText = TcpReassembler.doReassemble(session.getPackets());

        if (!packetText.isEmpty()) {

            Document documentXml = stringXmlToDocumentConvert(packetText);

            if (documentXml != null) {

                PacketDTO packetDTO = new PacketDTO();

                processData(packetDTO, documentXml.getElementsByTagName("crs:_super"));
                processData(packetDTO, documentXml.getElementsByTagName("crs:bind"));
                processData(packetDTO, documentXml.getElementsByTagName("crs:clientVerID"));
                processData(packetDTO, documentXml.getElementsByTagName("crs:comment"));

                //document.getElementsByTagName("crs:name").item(0).getAttributes().getNamedItem("value").getTextContent()
                //document.getElementsByTagName("crs:bind").item(0).getAttributes().getNamedItem("bindID").getTextContent()
                //document.getElementsByTagName("crs:id").item(0).getAttributes().getNamedItem("value").getTextContent()
                //document.getElementsByTagName("crs:clientVerID").item(0).getAttributes().getNamedItem("value").getTextContent()
                //document.getElementsByTagName("crs:comment").item(0).getTextContent()
            }
        }
    }

    private static Document stringXmlToDocumentConvert(String stringXml) {
        Document document = null;
        try {
            String preparedString = prepareXmlString(stringXml);

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(preparedString));

            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = df.newDocumentBuilder();
            document = builder.parse(is);

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return document;
    }

    private static String prepareXmlString(String stringXml) {
        return new String(stringXml.getBytes(StandardCharsets.ISO_8859_1));
    }

    private static void processData(PacketDTO packetDTO, NodeList nodesList) {
        for (int i = 0; i < nodesList.getLength(); i++) {
            Element item = (Element) nodesList.item(i);

            if (item.getNextSibling().getNodeName().equals("crs:name")) {
                String nameItem = item.getNextSibling().getAttributes().getNamedItem("value").getTextContent();
            } else if (item.getNextSibling().getNextSibling().getNextSibling().getNodeName().equals("crs:verNum")) {
                Long verNumItem = Long.parseLong(item.getNextSibling().getNextSibling().getNextSibling().getTextContent());
            }

            //UUID bindId = UUID.fromString(item.getNextSibling().getAttributes().getNamedItem("bindID").getTextContent());


            NodeList itemsChildNodes = item.getChildNodes();
            for (int j = 0; j < itemsChildNodes.getLength(); j++) {
                Node childNode = itemsChildNodes.item(j);

                if (childNode.getNodeName().equals("crs:id")) {
                    UUID objectId = UUID.fromString(childNode.getAttributes().getNamedItem("value").getTextContent());
                } else if (childNode.getNodeName().equals("crs:classID")) {
                    UUID classId = UUID.fromString(childNode.getAttributes().getNamedItem("value").getTextContent());
                }
            }
        }
    }
}
