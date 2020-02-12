package de.whiteo.rp.service;

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
    this.sent = sent;
  }
}