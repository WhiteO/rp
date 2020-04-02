package de.whiteo.rp.util;

import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpBuffer {

  public static void doBuffer(short identificationNumber, TcpPacket tcpPacket,
      TcpSession sessions) {
    boolean psh = tcpPacket.getHeader().getPsh();

    if (psh) {
      sessions.setHalfComplete(true);
    }

    boolean endElements = tcpPacket.getPayload().toString().trim().endsWith("66 53 b2 a6");
    if (endElements && sessions.isHalfComplete()) {
      sessions.setComplete(true);
    }

    sessions.getPacketMap().put(identificationNumber, tcpPacket);
  }
}