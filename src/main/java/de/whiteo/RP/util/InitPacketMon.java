package de.whiteo.rp.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class InitPacketMon {

  public void run(Packet packet, Map<Long, TcpSession> sessions) {
    TcpPacket tcpPacket = packet.get(TcpPacket.class);

    if (null != tcpPacket.getPayload() && 4 < tcpPacket.getPayload().length()) {
      long acknowledgmentNumber = tcpPacket.getHeader().getAcknowledgmentNumberAsLong();
      short identificationNumber = packet.get(IpV4Packet.class).getHeader().getIdentification();

      TcpSession tcpSession = sessions.get(acknowledgmentNumber);
      if (null == tcpSession) {
        tcpSession = new TcpSession(identificationNumber, tcpPacket);
        sessions.put(acknowledgmentNumber, tcpSession);
      }
      TcpBuffer.doBuffer(identificationNumber, tcpPacket, tcpSession);

      if (tcpSession.isComplete()) {
        Map<Short, TcpPacket> sortedMap = new LinkedHashMap<>();
        tcpSession.getPacketMap().entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        Executor.doExecute(new ArrayList<>(sortedMap.values()));
        sessions.remove(acknowledgmentNumber);
      }
    }
  }
}