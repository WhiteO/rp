package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.PacketRepository;
import de.whiteo.rp.util.PacketConverter;
import org.springframework.stereotype.Service;

import java.util.*;

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
  public void updatePackets(String uuid) {
    try {
      Long packetId = packetRepository.getIdByClientVerId(UUID.fromString(uuid));
      OutPacket outPacket = packetRepository.findById(packetId).orElseThrow(Exception::new);
      outPacket.setSent(true);
      packetRepository.save(outPacket);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}