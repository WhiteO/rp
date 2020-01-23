package de.whiteo.rp;

import de.whiteo.rp.util.InitPacketMon;
import de.whiteo.rp.util.PacketLoop;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class RpApplication {

    static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();

    public static void main(String[] args) throws PcapNativeException {
        SpringApplication.run(RpApplication.class, args);

        final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RpApplication.class);
        final String DEVICE_NAME = "\\Device\\NPF_Loopback";
        final int MAX_PACKETS = 2000;

        final PcapNetworkInterface device = Pcaps.getDevByName(DEVICE_NAME);
        final PacketListener listener = packet -> InitPacketMon.run(packet, map);

        new PacketLoop(device, listener, handle -> {
            try {
                handle.loop(MAX_PACKETS, listener);
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }).run();
    }
}
