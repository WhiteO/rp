package de.whiteo.rp.util;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.service.PacketDTO;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketConverter {

  public static OutPacket convertToOutPacket(PacketDTO packetDTO) {
    OutPacket outPacket = new OutPacket();
    outPacket.setId(packetDTO.getId());
    outPacket.setSent(packetDTO.isSent());
    outPacket.setNameMap(packetDTO.getNameMap());
    outPacket.setClassIdMap(packetDTO.getClassIdMap());
    outPacket.setBindId(packetDTO.getBindID());
    outPacket.setClientVerId(packetDTO.getClientVerId());
    outPacket.setComment(packetDTO.getComment());
    outPacket.setObjectIdMap(packetDTO.getObjectIdMap());
    outPacket.setUser(packetDTO.getUser());
    outPacket.setActionMap(packetDTO.getActionMap());
    outPacket.setRemovedMap(packetDTO.getRemovedMap());
    outPacket.setDate(packetDTO.getDate());
    outPacket.setAlias(packetDTO.getAlias());
    outPacket.setVerNumCommit(packetDTO.getVerNumCommit());
    outPacket.setNameCommit(packetDTO.getNameCommit());
    outPacket.setCommentNameCommit(outPacket.getCommentNameCommit());
    outPacket.setDateChangeNameCommit(packetDTO.getDateChangeNameCommit());
    outPacket.setUserChangeCommit(packetDTO.getUserChangeCommit());
    return outPacket;
  }

  public static PacketDTO converterToPacketDTO(OutPacket outPacket) {
    return new PacketDTO(outPacket.getId(), outPacket.getObjectIdMap(), outPacket.getClassIdMap(),
        outPacket.getNameMap(), outPacket.getActionMap(), outPacket.getRemovedMap(),
        outPacket.getComment(), outPacket.isSent(), outPacket.getUser(), outPacket.getBindId(),
        outPacket.getClientVerId(), outPacket.getAlias(), outPacket.getDate());
  }
}