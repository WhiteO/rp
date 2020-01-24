package de.whiteo.rp.util;

import org.pcap4j.packet.TcpPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public final class TcpSession {

    private final List<TcpPacket> packetsToServer = new ArrayList<TcpPacket>();
    //private final List<TcpPacket> packetsToClient = new ArrayList<TcpPacket>();
    //private long serverSeqNumOffset;
    private long clientSeqNumOffset;
    private int window;

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public List<TcpPacket> getPackets() {
        return packetsToServer;
    }

    public long getSeqNumOffset() {
        return clientSeqNumOffset;
    }

    public void setSeqNumOffset(long seqNumOffset) {
        this.clientSeqNumOffset = seqNumOffset;
    }

    /*public List<TcpPacket> getPackets(boolean toServer) {
        if (toServer) {
            return packetsToServer;
        } else {
            return packetsToClient;
        }
    }

    public long getSeqNumOffset(boolean toServer) {
        if (toServer) {
            return clientSeqNumOffset;
        } else {
            return serverSeqNumOffset;
        }
    }

    public void setSeqNumOffset(boolean toServer, long seqNumOffset) {
        if (toServer) {
            this.clientSeqNumOffset = seqNumOffset;
        } else {
            this.serverSeqNumOffset = seqNumOffset;
        }
    }*/
}
