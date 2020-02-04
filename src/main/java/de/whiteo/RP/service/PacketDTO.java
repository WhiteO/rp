package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import java.util.Map;
import java.util.UUID;


/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

    private Map<Long, UUID> objectId;
    private Map<Long, UUID> classId;
    private Map<Long, String> name;
    private UUID bindID;
    private UUID clientVerId;
    private String comment;
    private Boolean isSent;

    public PacketDTO() {
        isSent = false;
    }

    public PacketDTO(Map<Long, UUID> objectId, Map<Long, UUID> classId, Map<Long, String> name, UUID bindID, UUID clientVerId, String comment, Boolean isSent) {
        this.objectId = objectId;
        this.classId = classId;
        this.name = name;
        this.bindID = bindID;
        this.clientVerId = clientVerId;
        this.comment = comment;
        this.isSent = isSent;
    }

    public Map<Long, UUID> getObjectId() {
        return objectId;
    }

    public void setObjectId(Map<Long, UUID> objectId) {
        this.objectId = objectId;
    }

    public Map<Long, UUID> getClassId() {
        return classId;
    }

    public void setClassId(Map<Long, UUID> classId) {
        this.classId = classId;
    }

    public Map<Long, String> getName() {
        return name;
    }

    public void setName(Map<Long, String> name) {
        this.name = name;
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
        outPacket.setName(this.getName());
        outPacket.setClassId(this.getClassId());
        outPacket.setBindId(this.getBindID());
        outPacket.setClientVerId(this.getClientVerId());
        outPacket.setComment(this.getComment());
        outPacket.setObjectId(this.getObjectId());
        return outPacket;
    }
}
