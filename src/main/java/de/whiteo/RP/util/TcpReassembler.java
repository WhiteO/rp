package de.whiteo.rp.util;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

import java.util.List;

import org.pcap4j.packet.TcpPacket;

import static org.pcap4j.util.ByteArrays.toHexString;

public class TcpReassembler {

    /*public static void main(String[] args) throws PcapNativeException, NotOpenException {

        //Map<TcpPort, TcpSession> sessions = new HashMap<TcpPort, TcpSession>();

        /*while (true) {
            try {
                Packet packet = handle.getNextPacketEx();
                TcpPacket tcp = packet.get(TcpPacket.class);
                if (tcp == null) {
                    continue;
                }*/

                /*boolean syn = tcp.getHeader().getSyn();
                boolean fin = tcp.getHeader().getFin();

                if (syn) {
                    TcpSession session;
                    if (isToServer) {
                        session = new TcpSession();
                        sessions.put(port, session);
                    } else {
                        session = sessions.get(port);
                    }

                    long seq = tcp.getHeader().getSequenceNumberAsLong();
                    session.setSeqNumOffset(isToServer, seq + 1L);

                } else if (fin) {
                    TcpSession session = sessions.get(port);
                    session.getPackets(isToServer).add(tcp);

                    byte[] reassembledPayload
                            = doReassemble(
                            session.getPackets(isToServer),
                            session.getSeqNumOffset(isToServer),
                            tcp.getHeader().getSequenceNumberAsLong(),
                            tcp.getPayload().length()
                    );

                    int len = reassembledPayload.length;*/
                    /*for (int i = 0; i < len; ) {
                        try {
                            TlsPacket tls = TlsPacket.newPacket(reassembledPayload, i, len - i);
                            System.out.println(tls);
                            i += tls.length();
                        } catch (IllegalRawDataException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (tcp.getPayload() != null && tcp.getPayload().length() != 0) {
                        TcpSession session = sessions.get(port);
                        session.getPackets(isToServer).add(tcp);
                    }
                }*/
           /* } catch (TimeoutException e) {
                continue;
            } catch (EOFException e) {
                break;
            }*/

    public static String doReassemble(List<TcpPacket> packets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TcpPacket p : packets) {
            String hexString = toHexString(p.getPayload().getRawData(), "");
            String string = convertHexToString(hexString);
            stringBuilder.append(string);
        }

        return stringBuilder.toString().substring(100,stringBuilder.toString().length()-4);
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