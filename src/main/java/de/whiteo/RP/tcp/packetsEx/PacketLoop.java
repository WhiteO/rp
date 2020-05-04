package de.whiteo.rp.tcp.packetsEx;

import java.util.function.Consumer;
import org.pcap4j.core.BpfProgram;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.slf4j.LoggerFactory;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketLoop implements Runnable {

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PacketLoop.class);
  final int SNAPSHOT_LENGTH = 65536;
  final int READ_TIMEOUT = 10;
  final String FILTER = "tcp dst port 1542 || tcp src port 1542";
  final PcapNetworkInterface NETWORK_INTERFACE;
  final PacketListener LISTENER;
  final Consumer<PcapHandle> CONSUMER;

  public PacketLoop(PcapNetworkInterface networkInterface,
      PacketListener listener,
      Consumer<PcapHandle> consumer) {
    assert (null != networkInterface);
    assert (null != listener);
    assert (null != consumer);
    this.NETWORK_INTERFACE = networkInterface;
    this.LISTENER = listener;
    this.CONSUMER = consumer;
  }

  @Override
  public void run() {
    try (final PcapHandle handle = NETWORK_INTERFACE.openLive(
        SNAPSHOT_LENGTH,
        PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
        READ_TIMEOUT)) {
      handle.setFilter(FILTER, BpfProgram.BpfCompileMode.OPTIMIZE);
      CONSUMER.accept(handle);
    } catch (Exception e) {
      LOGGER.error(e.toString());
    }
  }
}