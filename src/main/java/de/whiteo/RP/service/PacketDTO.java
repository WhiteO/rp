package de.whiteo.rp.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

  private Long id;
  private Map<String, UUID> objectIdMap;
  private Map<String, UUID> classIdMap;
  private Map<String, String> nameMap;
  private Map<String, Integer> actionMap;
  private Map<String, Boolean> removedMap;
  private String comment;
  private Boolean sent;
  private String user;
  private UUID bindID;
  private UUID clientVerId;
  private String alias;
  private LocalDateTime date;
  private Integer verNumCommit;
  private String nameCommit;
  private String commentNameCommit;
  private LocalDateTime dateChangeNameCommit;
  private String userChangeCommit;

  public PacketDTO() {
    date = LocalDateTime.now();
    sent = false;
    objectIdMap = new HashMap<>();
    classIdMap = new HashMap<>();
    nameMap = new HashMap<>();
    actionMap = new HashMap<>();
    removedMap = new HashMap<>();
  }

  public PacketDTO(Long id, Map<String, UUID> objectIdMap,
      Map<String, UUID> classIdMap, Map<String, String> nameMap,
      Map<String, Integer> actionMap, Map<String, Boolean> removedMap, String comment,
      Boolean sent, String user, UUID bindID, UUID clientVerId, String alias,
      LocalDateTime date) {
    this.id = id;
    this.objectIdMap = objectIdMap;
    this.classIdMap = classIdMap;
    this.nameMap = nameMap;
    this.actionMap = actionMap;
    this.removedMap = removedMap;
    this.comment = comment;
    this.sent = sent;
    this.user = user;
    this.bindID = bindID;
    this.clientVerId = clientVerId;
    this.alias = alias;
    this.date = date;
    this.dateChangeNameCommit = LocalDateTime.now();
  }

  public String getUserChangeCommit() {
    return userChangeCommit;
  }

  public void setUserChangeCommit(String userChangeCommit) {
    this.userChangeCommit = userChangeCommit;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
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

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Integer getVerNumCommit() {
    return verNumCommit;
  }

  public void setVerNumCommit(Integer verNumCommit) {
    this.verNumCommit = verNumCommit;
  }

  public String getNameCommit() {
    return nameCommit;
  }

  public void setNameCommit(String nameCommit) {
    this.nameCommit = nameCommit;
  }

  public String getCommentNameCommit() {
    return commentNameCommit;
  }

  public void setCommentNameCommit(String commentNameCommit) {
    this.commentNameCommit = commentNameCommit;
  }

  public LocalDateTime getDateChangeNameCommit() {
    return dateChangeNameCommit;
  }

  public void setDateChangeNameCommit(LocalDateTime dateChangeNameCommit) {
    this.dateChangeNameCommit = dateChangeNameCommit;
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
}