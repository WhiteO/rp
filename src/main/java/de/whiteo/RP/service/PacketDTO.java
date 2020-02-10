package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

    private Map<String, UUID> objectIdMap;
    private Map<String, UUID> classIdMap;
    private Map<String, String> nameMap;
    private UUID bindID;
    private UUID clientVerId;
    private String comment;
    private Boolean sent;
    private String user;
    private Map<String, Integer> actionMap;
    private Map<String, Boolean> removedMap;
    private LocalDateTime date;

    public PacketDTO() {
        sent = false;
        objectIdMap = new HashMap<>();
        classIdMap = new HashMap<>();
        nameMap = new HashMap<>();
        actionMap = new HashMap<>();
        removedMap = new HashMap<>();
        date = LocalDateTime.now();
    }

    public PacketDTO(Map<String, UUID> objectIdMap, Map<String, UUID> classIdMap,
                     Map<String, String> nameMap, UUID bindID, UUID clientVerId, String comment, LocalDateTime date,
                     Boolean sent, String user, Map<String, Integer> actionMap, Map<String, Boolean> removedMap) {
        this.objectIdMap = objectIdMap;
        this.classIdMap = classIdMap;
        this.nameMap = nameMap;
        this.bindID = bindID;
        this.clientVerId = clientVerId;
        this.comment = comment;
        this.sent = sent;
        this.removedMap = removedMap;
        this.user = user;
        this.actionMap = actionMap;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, Integer> getActionMap() {
        return actionMap;
    }

    public void setActionMap(Map<String, Integer> actionMap) {
        this.actionMap = actionMap;
    }

    public Map<String, Boolean> getRemovedMap() {
        return removedMap;
    }

    public void setRemovedMap(Map<String, Boolean> removedMap) {
        this.removedMap = removedMap;
    }

    public Map<String, UUID> getObjectIdMap() {
        return objectIdMap;
    }

    public void setObjectIdMap(Map<String, UUID> objectIdMap) {
        this.objectIdMap = objectIdMap;
    }

    public Map<String, UUID> getClassIdMap() {
        return classIdMap;
    }

    public void setClassIdMap(Map<String, UUID> classIdMap) {
        this.classIdMap = classIdMap;
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<String, String> nameMap) {
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

    public Boolean isSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        sent = sent;
    }

    public OutPacket convertToOutPacket() {
        OutPacket outPacket = new OutPacket();
        outPacket.setSent(this.isSent());
        outPacket.setNameMap(this.getNameMap());
        outPacket.setClassIdMap(this.getClassIdMap());
        outPacket.setBindId(this.getBindID());
        outPacket.setClientVerId(this.getClientVerId());
        outPacket.setComment(this.getComment());
        outPacket.setObjectIdMap(this.getObjectIdMap());
        outPacket.setUser(this.getUser());
        outPacket.setActionMap(this.getActionMap());
        outPacket.setRemovedMap(this.getRemovedMap());
        outPacket.setDate(this.date);
        return outPacket;
    }
}
