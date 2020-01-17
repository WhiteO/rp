package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface PacketService {
    OutPacket createPacket(OutPacket outPacket);
    OutPacket updatePacket(OutPacket outPacket);
    OutPacket getPacket(OutPacket outPacket);
    OutPacket deletePacket(OutPacket outPacket);
}
