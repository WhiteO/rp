package de.whiteo.rp;

import de.whiteo.rp.util.InitPacketMon;
import de.whiteo.rp.util.PacketLoop;
import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpApplication {

    public static void main(String[] args) throws PcapNativeException {
        SpringApplication.run(RpApplication.class, args);
        final String DEVICE_NAME = "\\Device\\NPF_Loopback";
        final int MAX_PACKETS = 2000;

        final PcapNetworkInterface device = Pcaps.getDevByName(DEVICE_NAME);
        final PacketListener listener = System.out::println

        new PacketLoop(device, listener, handle -> {
            try {
                handle.loop(MAX_PACKETS, listener);
            } catch (PcapNativeException | InterruptedException | NotOpenException e) {
                e.printStackTrace();
            }
        }).run();
    }
}
