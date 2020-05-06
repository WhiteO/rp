package de.whiteo.rp.service;

import de.whiteo.rp.dto.PacketDTO;
import de.whiteo.rp.model.Logger;
import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.LoggerRepository;
import de.whiteo.rp.repository.PacketRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Service
public class ApiServiceImpl implements ApiService {

  private final PacketRepository packetRepository;
  private final LoggerRepository loggerRepository;

  public ApiServiceImpl(PacketRepository packetRepository,
      LoggerRepository loggerRepository) {
    this.packetRepository = packetRepository;
    this.loggerRepository = loggerRepository;
  }

  @Override
  public Set<OutPacket> getPackets() {
    return packetRepository.getAllPackets();
  }

  @Override
  public void updateOrAddPacket(PacketDTO packetDTO) {
    if (packetDTO.isService()) {
      Long packetId = packetRepository.getIdByClientVerId(packetDTO.getClientVerId());
      if (null == packetId) {
        packetRepository
            .saveAndFlush(new OutPacket(packetDTO.getVerNumCommit(), packetDTO.getClientVerId()));
      }
    } else {
      Long packetId = packetRepository.getIdByClientVerId(packetDTO.getClientVerId());
      OutPacket foundPacket;
      if (null != packetId) {
        foundPacket = packetRepository.getById(packetId);
      } else {
        foundPacket = new OutPacket(false);
      }
      if (!foundPacket.isSent()) {
        if (packetDTO.isNews()) {
          foundPacket.setService(false);
          foundPacket.setUser(packetDTO.getUser());
          foundPacket.setDate(packetDTO.getDate());
          foundPacket.setComment(packetDTO.getComment());
          foundPacket.setAlias(packetDTO.getAlias());
          foundPacket.setBindId(packetDTO.getBindID());
          foundPacket.setActionMap(packetDTO.getActionMap());
          foundPacket.setClassIdMap(packetDTO.getClassIdMap());
          foundPacket.setNameMap(packetDTO.getNameMap());
          foundPacket.setObjectIdMap(packetDTO.getObjectIdMap());
          foundPacket.setRemovedMap(packetDTO.getRemovedMap());
        } else if (packetDTO.isUpdate()) {
          foundPacket.setUserChangeCommit(packetDTO.getUserChangeCommit());
          foundPacket.setDateChangeNameCommit(packetDTO.getDateChangeNameCommit());
          foundPacket.setCommentNameCommit(packetDTO.getCommentNameCommit());
          foundPacket.setNameCommit(packetDTO.getNameCommit());
        }
      }
      packetRepository.saveAndFlush(foundPacket);
    }
  }

  @Override
  public void updatePackets(Set<PacketDTO> packetDTOSet) {
    //Todo
  }

  @Override
  public Integer getPacketsCount() {
    return packetRepository.getPacketsCount();
  }

  @Override
  public void addLogger(Logger logger) {
    loggerRepository.saveAndFlush(logger);
  }

  @Override
  public Set<Logger> getLoggers() {
    return loggerRepository.getAllLoggers();
  }

  @Override
  public Integer getLoggersCount() {
    return loggerRepository.getLoggersCount();
  }
}