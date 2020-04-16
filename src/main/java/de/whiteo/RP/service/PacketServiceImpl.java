package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.PacketRepository;
import de.whiteo.rp.util.PacketConverter;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Service
public class PacketServiceImpl implements PacketService {

  private final PacketRepository packetRepository;

  public PacketServiceImpl(PacketRepository packetRepository) {
    this.packetRepository = packetRepository;
  }

  @Override
  public Set<OutPacket> getPackets() {
    return packetRepository.getAllPackets();
  }

  @Override
  public void addPacket(PacketDTO packetDTO) {
    packetRepository.saveAndFlush(PacketConverter.convertToOutPacket(packetDTO));
  }

  @Override
  public Integer getPacketsCount() {
    return packetRepository.getPacketsCount();
  }

  @Override
  public PacketDTO getPacketId(UUID clientVerId) {
    Long packetId = packetRepository.getIdByClientVerId(clientVerId);
    PacketDTO packetDTO = null;
    if (0L != packetId) {
      packetDTO = PacketConverter.converterToPacketDTO(packetRepository.getOne(packetId));
    }
    return packetDTO;
  }

  @Override
  public void updatePacket(PacketDTO packetDTO) {
    packetRepository.saveAndFlush(PacketConverter.convertToOutPacket(packetDTO));
  }
}