package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;

import java.util.Set;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface PacketService {
    Set<OutPacket> getPackets();

    void addPacket(PacketDTO packetDTO);

    void updatePackets(Set<OutPacket> outPacketList);
}
