package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;

import java.util.List;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface PacketService {
    List<OutPacket> getPackets();
}
