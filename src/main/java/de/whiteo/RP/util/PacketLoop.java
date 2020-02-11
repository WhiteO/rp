package de.whiteo.rp.util;

import org.pcap4j.core.BpfProgram;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketLoop implements Runnable {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PacketLoop.class);
    final int SNAPSHOT_LENGTH = 65536;
    final int READ_TIMEOUT = 10;
    final String FILTER = "tcp dst port 1542 and ip[2:2] > 600";

    final PcapNetworkInterface networkInterface;
    final PacketListener listener;
    final Consumer<PcapHandle> consumer;

    public PacketLoop(PcapNetworkInterface networkInterface,
                      PacketListener listener,
                      Consumer<PcapHandle> consumer) {
        assert (networkInterface != null);
        assert (listener != null);
        assert (consumer != null);
        this.networkInterface = networkInterface;
        this.listener = listener;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try (final PcapHandle handle = networkInterface.openLive(
                SNAPSHOT_LENGTH,
                PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
                READ_TIMEOUT)) {
            handle.setFilter(FILTER, BpfProgram.BpfCompileMode.OPTIMIZE);
            consumer.accept(handle);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }
}