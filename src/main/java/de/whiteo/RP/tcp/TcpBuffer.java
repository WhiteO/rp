package de.whiteo.rp.tcp;

import static org.pcap4j.util.ByteArrays.toHexString;

import org.pcap4j.packet.TcpPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class TcpBuffer {

  public static void doBuffer(short identificationNumber, TcpPacket tcpPacket,
      TcpSession sessions) {

    if (0 == sessions.getByteMax()) {
      String payLoadString = toHexString(tcpPacket.getPayload().getRawData(), "");
      int indexContentLength = payLoadString
          .indexOf("436f6e74656e742d4c656e6774683a20");
      int indexAfterContentLength = payLoadString
          .indexOf("0d0a436f6e74656e742d547970653a206170706c69636174696f6e2f786d6c");
      if (-1 != indexContentLength || -1 != indexAfterContentLength) {
        sessions.setByteMax(Integer.parseInt(TcpReassembler.convertHexToString(
            payLoadString.substring(32 + indexContentLength, indexAfterContentLength))));
      }
    }

    sessions.setByteCount(sessions.getByteCount() + tcpPacket.getPayload().length());

    if (0L != sessions.getByteMax() && sessions.getByteCount() >= sessions.getByteMax()) {
      sessions.setComplete(true);
    }
    sessions.getPacketMap().put(identificationNumber, tcpPacket);
  }
}