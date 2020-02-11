package de.whiteo.rp.util;

import org.pcap4j.core.*;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketLoop implements Runnable {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PacketLoop.class);
    static Map<Integer, TcpSession> sessions = new HashMap<>();
    final int SNAPSHOT_LENGTH = 65536;
    final int READ_TIMEOUT = 10;
    final String FILTER = "tcp dst port 1542 and ip[2:2] > 620";
    final String DEVICE_NAME = "\\Device\\NPF_Loopback";
    final PcapNetworkInterface networkInterface;
    final Consumer<PcapHandle> consumer = null;

    public PacketLoop() throws PcapNativeException {
        this.networkInterface = Pcaps.getDevByName(DEVICE_NAME);
        assert this.consumer != null;
    }

    @Override
    public void run() {
        try (final PcapHandle handle = networkInterface.openLive(
                SNAPSHOT_LENGTH,
                PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
                READ_TIMEOUT)) {
            handle.setFilter(FILTER, BpfCompileMode.OPTIMIZE);
            consumer.accept(handle);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }
}