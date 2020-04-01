package de.whiteo.rp;

import de.whiteo.rp.util.InitPacketMon;
import de.whiteo.rp.util.PacketLoop;
import de.whiteo.rp.util.TcpSession;
import java.util.HashMap;
import java.util.Map;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpApplication {

  private static Map<Long, TcpSession> sessions = new HashMap<>();
  private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RpApplication.class);

  @SuppressWarnings("InfiniteLoopStatement")
  public static void main(String[] args) throws PcapNativeException {
    SpringApplication.run(RpApplication.class, args);

    String deviceName;
    if (0 != args.length) {
      deviceName = args[0];
    } else {
      LOGGER.info("to get device name, use 'getmac /fo csv /v'");
      deviceName = "\\Device\\NPF_Loopback";
    }

    final int MAX_PACKETS = 2000;

    final PcapNetworkInterface device = Pcaps.getDevByName(deviceName);
    final PacketListener listener = packet -> new InitPacketMon().run(packet, sessions);

    while (true) {
      new PacketLoop(device, listener, handle -> {
        try {
          handle.loop(MAX_PACKETS, listener);
        } catch (Exception e) {
          LOGGER.error(e.toString());
        }
      }).run();
    }
  }
}