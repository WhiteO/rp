package de.whiteo.rp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Entity
@Table(name = "PACKETS")
public class OutPacket implements Serializable {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "USER", nullable = true, columnDefinition = "VARCHAR(255)")
  private String user;
  @Column(name = "ALIAS", nullable = true, columnDefinition = "VARCHAR(255)")
  private String alias;
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid2")
  @Column(name = "BIND_ID", nullable = true, columnDefinition = "UUID")
  private UUID bindId;
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid2")
  @Column(name = "CLIENT_VER_ID", nullable = false, columnDefinition = "UUID")
  private UUID clientVerId;
  @ElementCollection(fetch = FetchType.EAGER)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid2")
  @CollectionTable(name = "T_OBJECT", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
      nullable = true, columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
  @MapKeyColumn(name = "KEY_COLUMN", nullable = true, columnDefinition = "CHAR(40)")
  @Column(name = "VALUE_COLUMN", nullable = true, columnDefinition = "UUID")
  private Map<String, UUID> objectIdMap;
  @ElementCollection(fetch = FetchType.EAGER)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid2")
  @CollectionTable(name = "T_NAME", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
      nullable = true, columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
  @MapKeyColumn(name = "KEY_COLUMN", nullable = true, columnDefinition = "CHAR(40)")
  @Column(name = "VALUE_COLUMN", nullable = true, columnDefinition = "VARCHAR(255)")
  private Map<String, String> nameMap;
  @ElementCollection(fetch = FetchType.EAGER)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid2")
  @CollectionTable(name = "T_CLASS", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
      nullable = true, columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
  @MapKeyColumn(name = "KEY_COLUMN", nullable = true, columnDefinition = "CHAR(40)")
  @Column(name = "VALUE_COLUMN", nullable = true, columnDefinition = "UUID")
  private Map<String, UUID> classIdMap;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "T_ACTION", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
      nullable = true, columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
  @MapKeyColumn(name = "KEY_COLUMN", nullable = true, columnDefinition = "CHAR(40)")
  @Column(name = "VALUE_COLUMN", nullable = true, columnDefinition = "INTEGER")
  private Map<String, Integer> actionMap;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "T_REMOVED", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
      nullable = true, columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
  @MapKeyColumn(name = "KEY_COLUMN", nullable = true, columnDefinition = "CHAR(40)")
  @Column(name = "VALUE_COLUMN", nullable = true, columnDefinition = "BOOLEAN default false")
  private Map<String, Boolean> removedMap;
  @Column(name = "COMMENT", columnDefinition = "VARCHAR(255)")
  private String comment;
  @Column(name = "SENT", nullable = false, columnDefinition = "BOOLEAN default false")
  private Boolean sent;
  @Column(name = "SERVICE", nullable = false, columnDefinition = "BOOLEAN default true")
  private Boolean service;
  @Column(name = "DATE", nullable = true, columnDefinition = "DATETIME")
  private LocalDateTime date;
  @Column(name = "NUMBER_COMMIT", nullable = true, columnDefinition = "INTEGER")
  private Integer verNumCommit;
  @Column(name = "NAME_COMMIT", nullable = true, columnDefinition = "VARCHAR(255)")
  private String nameCommit;
  @Column(name = "COMMENT_COMMIT", nullable = true, columnDefinition = "VARCHAR(255)")
  private String commentNameCommit;
  @Column(name = "DATE_CHANGE_COMMIT", nullable = true, columnDefinition = "DATETIME")
  private LocalDateTime dateChangeNameCommit;
  @Column(name = "USER_CHANGE_COMMIT", nullable = true, columnDefinition = "VARCHAR(255)")
  private String userChangeCommit;

  public OutPacket() {
  }

  public OutPacket(Integer verNumCommit, UUID clientVerId) {
    this.service = true;
    this.verNumCommit = verNumCommit;
    this.clientVerId = clientVerId;
    this.sent = false;
  }

  public OutPacket(UUID clientVerId) {
    this.sent = false;
    this.clientVerId = clientVerId;
  }

  @JsonIgnore
  public Boolean isService() {
    return service;
  }

  public void setService(Boolean service) {
    this.service = service;
  }

  @JsonProperty("NUMBER_COMMIT")
  public Integer getVerNumCommit() {
    return verNumCommit;
  }

  public void setVerNumCommit(Integer verNumCommit) {
    this.verNumCommit = verNumCommit;
  }

  @JsonProperty("NAME_COMMIT")
  public String getNameCommit() {
    return nameCommit;
  }

  public void setNameCommit(String nameCommit) {
    this.nameCommit = nameCommit;
  }

  @JsonProperty("COMMENT_NAME_COMMIT")
  public String getCommentNameCommit() {
    return commentNameCommit;
  }

  public void setCommentNameCommit(String commentNameCommit) {
    this.commentNameCommit = commentNameCommit;
  }

  @JsonProperty("DATE_CHANGE_COMMIT")
  public LocalDateTime getDateChangeNameCommit() {
    return dateChangeNameCommit;
  }

  public void setDateChangeNameCommit(LocalDateTime dateChangeNameCommit) {
    this.dateChangeNameCommit = dateChangeNameCommit;
  }

  @JsonProperty("USER_CHANGE_COMMIT")
  public String getUserChangeCommit() {
    return userChangeCommit;
  }

  public void setUserChangeCommit(String userChangeCommit) {
    this.userChangeCommit = userChangeCommit;
  }

  @JsonProperty("DATE")
  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @JsonProperty("CLIENT_ID")
  public UUID getBindId() {
    return bindId;
  }

  public void setBindId(UUID bindId) {
    this.bindId = bindId;
  }

  @JsonProperty("PUSH_VER_ID")
  public UUID getClientVerId() {
    return clientVerId;
  }

  public void setClientVerId(UUID clientVerId) {
    this.clientVerId = clientVerId;
  }

  @JsonProperty("OBJECT_ID")
  public Map<String, UUID> getObjectIdMap() {
    return objectIdMap;
  }

  public void setObjectIdMap(Map<String, UUID> objectIdMap) {
    this.objectIdMap = objectIdMap;
  }

  @JsonProperty("OBJECT_NAME")
  public Map<String, String> getNameMap() {
    return nameMap;
  }

  public void setNameMap(Map<String, String> nameMap) {
    this.nameMap = nameMap;
  }

  @JsonProperty("CLASS_ID")
  public Map<String, UUID> getClassIdMap() {
    return classIdMap;
  }

  public void setClassIdMap(Map<String, UUID> classIdMap) {
    this.classIdMap = classIdMap;
  }

  @JsonProperty("COMMENT")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @JsonProperty("USER")
  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  @JsonProperty("ACTION")
  public Map<String, Integer> getActionMap() {
    return actionMap;
  }

  public void setActionMap(Map<String, Integer> actionMap) {
    this.actionMap = actionMap;
  }

  @JsonProperty("REMOVED")
  public Map<String, Boolean> getRemovedMap() {
    return removedMap;
  }

  public void setRemovedMap(Map<String, Boolean> removedMap) {
    this.removedMap = removedMap;
  }

  @JsonIgnore
  public Boolean isSent() {
    return sent;
  }

  public void setSent(Boolean sent) {
    this.sent = sent;
  }

  @JsonProperty("ALIAS")
  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OutPacket outPacket = (OutPacket) o;
    return Objects.equals(id, outPacket.id) &&
        Objects.equals(bindId, outPacket.bindId) &&
        Objects.equals(clientVerId, outPacket.clientVerId) &&
        Objects.equals(objectIdMap, outPacket.objectIdMap) &&
        Objects.equals(classIdMap, outPacket.classIdMap) &&
        Objects.equals(nameMap, outPacket.nameMap) &&
        Objects.equals(comment, outPacket.comment) &&
        Objects.equals(user, outPacket.user) &&
        Objects.equals(actionMap, outPacket.actionMap) &&
        Objects.equals(removedMap, outPacket.removedMap) &&
        Objects.equals(date, outPacket.date) &&
        Objects.equals(sent, outPacket.sent) &&
        Objects.equals(alias, outPacket.alias) &&
        Objects.equals(userChangeCommit, outPacket.userChangeCommit) &&
        Objects.equals(dateChangeNameCommit, outPacket.dateChangeNameCommit) &&
        Objects.equals(commentNameCommit, outPacket.commentNameCommit) &&
        Objects.equals(nameCommit, outPacket.nameCommit) &&
        Objects.equals(verNumCommit, outPacket.verNumCommit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bindId, clientVerId, objectIdMap, classIdMap,
        nameMap, comment, user, actionMap, removedMap, sent, date, alias,
        userChangeCommit, dateChangeNameCommit, commentNameCommit, nameCommit, verNumCommit);
  }
}