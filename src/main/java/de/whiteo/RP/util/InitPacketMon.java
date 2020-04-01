package de.whiteo.rp.util;

import java.util.ArrayList;
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

    if (4 < tcpPacket.getPayload().length()) {
      long acknowledgmentNumber = tcpPacket.getHeader().getAcknowledgmentNumberAsLong();
      short identificationNumber = packet.get(IpV4Packet.class).getHeader().getIdentification();

      TcpSession tcpSession = sessions.get(acknowledgmentNumber);
      if (null != tcpSession) {
        TcpBuffer.doBuffer(identificationNumber, tcpPacket, tcpSession);
      } else {
        sessions.put(acknowledgmentNumber, new TcpSession(identificationNumber, tcpPacket));
      }

      assert tcpSession != null;
      if (tcpSession.isComplete()) {
        Executor.doExecute(new ArrayList<>(tcpSession.getPacketMap().values()));
        sessions.remove(acknowledgmentNumber);
      }
    }
  }
}