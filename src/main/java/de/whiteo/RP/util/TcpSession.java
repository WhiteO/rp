package de.whiteo.rp.util;

import org.pcap4j.packet.TcpPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public final class TcpSession {

    private final List<TcpPacket> packetsList = new ArrayList<TcpPacket>();
    private long SeqNumOffset;
    private int window;

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public List<TcpPacket> getPackets() {
        return packetsList;
    }

    public void setSeqNumOffset(long seqNumOffset) {
        this.SeqNumOffset = seqNumOffset;
    }
}
