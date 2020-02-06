package de.whiteo.rp.util;

import de.whiteo.rp.config.SpringContext;
import de.whiteo.rp.controller.PacketController;
import de.whiteo.rp.service.PacketDTO;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.springframework.context.ApplicationContext;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

    private PacketController packetController;

    public InitPacketMon() {
        ApplicationContext context = SpringContext.getAppContext();
        packetController = (PacketController)context.getBean("packetController");
    }

    public void run(Packet packet, Map<Integer, TcpSession> sessions) {
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

                packetController.addPacket(packetDTO);

                sessions.clear();
            }
        }
    }

    private Document stringXmlToDocumentConvert(String stringXml) {
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

    private String prepareXmlString(String stringXml) {
        return new String(stringXml.getBytes(StandardCharsets.ISO_8859_1));
    }

    private void processData(PacketDTO packetDTO, NodeList nodesList) {
        for (int i = 0; i < nodesList.getLength(); i++) {
            Element item = (Element) nodesList.item(i);
            Long verNumItem = 0L;


            if (nodesList.item(0).getNodeName().equals("crs:clientVerID")) {
                packetDTO.setClientVerId(UUID.fromString(item.getAttributes()
                        .getNamedItem("value").getTextContent()));
            }
            if (item.getNodeName().equals("crs:comment")) {
                packetDTO.setComment(item.getTextContent());
            }
            if (item.getNextSibling().getNodeName().equals("crs:name")) {
                Map<Long, String> nameVerNumMap = new HashMap<>();

                String nameItem = item.getNextSibling().getAttributes().getNamedItem("value").getTextContent();
                if (item.getNextSibling().getNextSibling().getNextSibling().getNodeName().equals("crs:verNum")) {
                    verNumItem = Long.parseLong(item.getNextSibling().getNextSibling()
                            .getNextSibling().getTextContent());
                }
                nameVerNumMap.put(verNumItem, nameItem);
                packetDTO.setName(nameVerNumMap);
            }

            if (item.getNodeName().equals("crs:bind")) {
                packetDTO.setBindID(UUID.fromString(item.getAttributes().getNamedItem("bindID").getTextContent()));
            }

            NodeList itemsChildNodes = item.getChildNodes();
            for (int j = 0; j < itemsChildNodes.getLength(); j++) {
                Node childNode = itemsChildNodes.item(j);

                if (childNode.getNodeName().equals("crs:id")) {
                    Map<Long, UUID> objIdVerNumMap = new HashMap<>();
                    UUID objectId = UUID.fromString(childNode.getAttributes().getNamedItem("value").getTextContent());
                    objIdVerNumMap.put(verNumItem, objectId);
                    packetDTO.setObjectId(objIdVerNumMap);
                }
                if (childNode.getNodeName().equals("crs:classID")) {
                    Map<Long, UUID> classIdVerNumMap = new HashMap<>();
                    UUID classId = UUID.fromString(childNode.getAttributes().getNamedItem("value").getTextContent());
                    classIdVerNumMap.put(verNumItem, classId);
                    packetDTO.setClassId(classIdVerNumMap);
                }
            }
        }
    }
}
