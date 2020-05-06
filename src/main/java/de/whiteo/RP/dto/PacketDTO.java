package de.whiteo.rp.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

  private Long id;
  private final Map<String, UUID> objectIdMap;
  private final Map<String, UUID> classIdMap;
  private final Map<String, String> nameMap;
  private final Map<String, Integer> actionMap;
  private final Map<String, Boolean> removedMap;
  private String comment;
  private Boolean sent;
  private Boolean service;
  private Boolean update;
  private Boolean news;
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
    this.objectIdMap = new HashMap<>();
    this.classIdMap = new HashMap<>();
    this.nameMap = new HashMap<>();
    this.actionMap = new HashMap<>();
    this.removedMap = new HashMap<>();
  }

  public PacketDTO(Long id, Map<String, UUID> objectIdMap,
      Map<String, UUID> classIdMap, Map<String, String> nameMap,
      Map<String, Integer> actionMap, Map<String, Boolean> removedMap, String comment,
      Boolean sent, String user, UUID bindID, UUID clientVerId, String alias,
      LocalDateTime date, LocalDateTime dateChangeNameCommit) {
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
    this.dateChangeNameCommit = dateChangeNameCommit;
  }

  public Boolean isUpdate() {
    return update;
  }

  public void setUpdate(Boolean update) {
    this.update = update;
  }

  public Boolean isNews() {
    return news;
  }

  public void setNews(Boolean news) {
    this.news = news;
  }

  public Boolean isService() {
    return service;
  }

  public void setService(Boolean service) {
    this.service = service;
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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Boolean isSent() {
    return sent;
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

  public void setVerNumCommit(String verNumCommit) {
    if (null != verNumCommit && !verNumCommit.equals("")) {
      this.verNumCommit = Integer.parseInt(verNumCommit);
    }
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

  public Map<String, Boolean> getRemovedMap() {
    return removedMap;
  }

  public Map<String, UUID> getObjectIdMap() {
    return objectIdMap;
  }

  public Map<String, UUID> getClassIdMap() {
    return classIdMap;
  }

  public Map<String, String> getNameMap() {
    return nameMap;
  }
}