package de.whiteo.rp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Entity
@Table(name = "PACKETS")
public class OutPacket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "BIND_ID")
    private UUID bindId;
    @Column(name = "CLIENT_VER_ID")
    private UUID clientVerId;
    @Column(name = "OBJECT_ID")
    private UUID objectId;
    @Column(name = "PARENT_ID")
    private UUID parentId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "IS_SENT", nullable = false, columnDefinition = "boolean default false")
    private Boolean isSent;

    public OutPacket() {
    }

    public OutPacket(UUID bindId, UUID clientVerId, UUID objectId, UUID firstId, UUID parentId, String name,
                     String comment) {
        this.bindId = bindId;
        this.clientVerId = clientVerId;
        this.objectId = objectId;
        this.parentId = parentId;
        this.name = name;
        this.comment = comment;
    }

    public OutPacket(Long id, UUID bindId, UUID clientVerId, UUID objectId, UUID firstId, UUID parentId, String name,
                     String comment, Boolean isAccepted, Boolean isSent) {
        this.id = id;
        this.bindId = bindId;
        this.clientVerId = clientVerId;
        this.objectId = objectId;
        this.parentId = parentId;
        this.name = name;
        this.comment = comment;
        this.isSent = isSent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getBindId() {
        return bindId;
    }

    public void setBindId(UUID bindId) {
        this.bindId = bindId;
    }

    public UUID getClientVerId() {
        return clientVerId;
    }

    public void setClientVerId(UUID clientVerId) {
        this.clientVerId = clientVerId;
    }

    public UUID getObjectId() {
        return objectId;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, bindId, clientVerId, objectId, parentId, name, comment, isSent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutPacket outPacket = (OutPacket) o;
        return Objects.equals(id, outPacket.id) &&
                Objects.equals(bindId, outPacket.bindId) &&
                Objects.equals(clientVerId, outPacket.clientVerId) &&
                Objects.equals(objectId, outPacket.objectId) &&
                Objects.equals(parentId, outPacket.parentId) &&
                Objects.equals(name, outPacket.name) &&
                Objects.equals(comment, outPacket.comment) &&
                Objects.equals(isSent, outPacket.isSent);
    }
}