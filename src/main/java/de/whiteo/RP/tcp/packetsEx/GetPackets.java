package de.whiteo.rp.tcp.packetsEx;

import de.whiteo.rp.tcp.TcpSession;
import java.util.HashMap;
import java.util.Map;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.slf4j.LoggerFactory;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class GetPackets {

  private static final Map<Long, TcpSession> sessions = new HashMap<>();

  public static void run(String deviceName) throws PcapNativeException {
    final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GetPackets.class);
    final PcapNetworkInterface DEVICE = Pcaps.getDevByName(deviceName);
    final PacketListener listener = packet -> new InitPacketMon().run(packet, sessions);

    new PacketLoop(DEVICE, listener, handle -> {
      try {
        handle.loop(-1, listener);
      } catch (Exception e) {
        LOGGER.error(e.toString());
      }
    }).run();
  }
}