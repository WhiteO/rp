package de.whiteo.rp.util;

import java.util.Map;
import java.util.TreeMap;
import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public final class TcpSession {

  private final Map<Short, TcpPacket> packetMap = new TreeMap<>();
  private boolean complete;
  private boolean halfComplete;

  public TcpSession(Short identification, TcpPacket tcpPacket) {
    this.complete = false;
    this.packetMap.put(identification, tcpPacket);
  }

  public boolean isHalfComplete() {
    return halfComplete;
  }

  public void setHalfComplete(boolean halfComplete) {
    this.halfComplete = halfComplete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  public boolean isComplete() {
    return complete;
  }

  public Map<Short, TcpPacket> getPacketMap() {
    return packetMap;
  }
}