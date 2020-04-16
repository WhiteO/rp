package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface PacketService {

  Set<OutPacket> getPackets();

  void addPacket(PacketDTO packetDTO);

  Integer getPacketsCount();

  PacketDTO getPacketId(UUID clientVerId);

  void updatePacket(PacketDTO packetDTO);
}