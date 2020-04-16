package de.whiteo.rp.service;

import de.whiteo.rp.model.Logger;
import de.whiteo.rp.model.OutPacket;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface ApiService {

  Set<OutPacket> getPackets();

  void addPacket(PacketDTO packetDTO);

  Integer getPacketsCount();

  PacketDTO getPacketId(UUID clientVerId);

  void updatePacket(PacketDTO packetDTO);

  void addLogger(Logger logger);

  Set<Logger> getLoggers();

  Integer getLoggersCount();
}