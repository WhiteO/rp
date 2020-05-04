package de.whiteo.rp.service;

import de.whiteo.rp.dto.PacketDTO;
import de.whiteo.rp.model.Logger;
import de.whiteo.rp.model.OutPacket;
import java.util.Set;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public interface ApiService {

  void updateOrAddPacket(PacketDTO packetDTO);

  void updatePackets(Set<PacketDTO> packetDTOSet);

  Set<OutPacket> getPackets();

  Integer getPacketsCount();

  void addLogger(Logger logger);

  Set<Logger> getLoggers();

  Integer getLoggersCount();
}