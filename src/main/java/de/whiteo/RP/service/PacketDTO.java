package de.whiteo.rp.service;

import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

    private Map<Long,UUID> objectId;
    private Map<Long,String> name;
    private Map<Long,UUID> classId;
    private UUID bindID;
    private UUID clientVerId;
    private String comment;
    private Boolean isSent;

    public PacketDTO() {
    }

    public PacketDTO(Map<Long, UUID> objectId, Map<Long, String> name, Map<Long, UUID> classId, UUID bindID, UUID clientVerId, String comment, Boolean isSent) {
        this.objectId = objectId;
        this.name = name;
        this.classId = classId;
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

    public Map<Long, String> getName() {
        return name;
    }

    public void setName(Map<Long, String> name) {
        this.name = name;
    }

    public Map<Long, UUID> getClassId() {
        return classId;
    }

    public void setClassId(Map<Long, UUID> classId) {
        this.classId = classId;
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
}
