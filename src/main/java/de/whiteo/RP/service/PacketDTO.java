package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

    private Map<Long, UUID> objectIdMap;
    private Map<Long, UUID> classIdMap;
    private Map<Long, String> nameMap;
    private UUID bindID;
    private UUID clientVerId;
    private String comment;
    private Boolean isSent;

    public PacketDTO() {
        isSent = false;
    }

    public PacketDTO(Map<Long, UUID> objectIdMap, Map<Long, UUID> classIdMap, Map<Long, String> nameMap, UUID bindID, UUID clientVerId, String comment, Boolean isSent) {
        this.objectIdMap = objectIdMap;
        this.classIdMap = classIdMap;
        this.nameMap = nameMap;
        this.bindID = bindID;
        this.clientVerId = clientVerId;
        this.comment = comment;
        this.isSent = isSent;
    }

    public Map<Long, UUID> getObjectIdMap() {
        return objectIdMap;
    }

    public void setObjectIdMap(Map<Long, UUID> objectIdMap) {
        this.objectIdMap = objectIdMap;
    }

    public Map<Long, UUID> getClassIdMap() {
        return classIdMap;
    }

    public void setClassIdMap(Map<Long, UUID> classIdMap) {
        this.classIdMap = classIdMap;
    }

    public Map<Long, String> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<Long, String> nameMap) {
        this.nameMap = nameMap;
    }

    public UUID getBindID() {
        return bindID;
    }

    public void setBindID(UUID bindID) {
        this.bindID = bindID;
    }

    public UUID getClientVerId() {
        return clientVerId;
    }

    public void setClientVerId(UUID clientVerId) {
        this.clientVerId = clientVerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }

    public OutPacket convertToOutPacket() {
        OutPacket outPacket = new OutPacket();
        outPacket.setSent(this.getSent());
        outPacket.setNameMap(this.getNameMap());
        outPacket.setClassIdMap(this.getClassIdMap());
        outPacket.setBindId(this.getBindID());
        outPacket.setClientVerId(this.getClientVerId());
        outPacket.setComment(this.getComment());
        outPacket.setObjectIdMap(this.getObjectIdMap());
        return outPacket;
    }
}
