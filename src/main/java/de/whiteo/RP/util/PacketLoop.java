package de.whiteo.rp.util;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.slf4j.LoggerFactory;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketLoop implements Runnable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PacketLoop.class);
    int snapshotLength = 65536;
    int readTimeout = 10;
    String filter = "tcp dst port 1542 and ip[2:2] > 5000";

    @Override
    public void run() {
        PcapNetworkInterface loopbackNetworkDevice = getLoopbackNetworkDevice();
        if (loopbackNetworkDevice == null) {
            logger.error("No device chosen.");
            System.exit(1);
        }

        try (final PcapHandle handle = loopbackNetworkDevice.openLive(snapshotLength,
                PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, readTimeout)) {
            handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);

            PacketListener listener = new PacketListener() {
                @Override
                public void gotPacket(Packet packet) {
                    System.out.println(packet);
                }
            };

            int maxPackets = 2000;
            handle.loop(maxPackets, listener);

        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    static PcapNetworkInterface getLoopbackNetworkDevice() {
        PcapNetworkInterface device = null;
        try {
            device = Pcaps.getDevByName("\\Device\\NPF_Loopback");
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return device;
    }
}
