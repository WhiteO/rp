package de.whiteo.rp.tcp;

import java.util.HashMap;
import java.util.Map;
import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public final class TcpSession {

  private final Map<Short, TcpPacket> packetMap;
  private int ByteCount;
  private int ByteMax;
  private boolean complete;

  public TcpSession(Short identification, TcpPacket tcpPacket) {
    this.complete = false;
    this.packetMap = new HashMap<>();
    this.packetMap.put(identification, tcpPacket);
  }

  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  public int getByteCount() {
    return ByteCount;
  }

  public void setByteCount(int byteCount) {
    ByteCount = byteCount;
  }

  public int getByteMax() {
    return ByteMax;
  }

  public void setByteMax(int byteMax) {
    ByteMax = byteMax;
  }

  public Map<Short, TcpPacket> getPacketMap() {
    return packetMap;
  }
}