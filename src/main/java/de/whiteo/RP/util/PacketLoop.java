package de.whiteo.rp.util;

import org.pcap4j.core.*;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketLoop implements Runnable {

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


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PacketLoop.class);
	int snapshotLength = 65536;
	int readTimeout = 10;
	String filter = "tcp dst port 1542 and ip[2:2] > 5000";

	@Override
	public void run() {

		try (final PcapHandle handle = networkInterface.openLive(
				snapshotLength,
				PcapNetworkInterface.PromiscuousMode.PROMISCUOUS
				, readTimeout)) {
			handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
			consumer.accept(handle);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}
}
