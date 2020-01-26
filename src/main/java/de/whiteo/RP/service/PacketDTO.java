package de.whiteo.rp.service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class PacketDTO {

    private Optional<UUID> bindId;
    private Optional<UUID> clientVerId;
    private Optional<UUID> objectId;
    private Optional<UUID> firstId;
    private Optional<UUID> parentId;
    private Optional<String> name;
    private Optional<String> comment;
    private Optional<Boolean> isSent;

    public PacketDTO() {

    }

    public PacketDTO(Optional<UUID> bindId, Optional<UUID> clientVerId, Optional<UUID> objectId,
                     Optional<UUID> firstId, Optional<UUID> parentId, Optional<String> name,
                     Optional<String> comment, Optional<Boolean> isSent) {
        this.bindId = bindId;
        this.clientVerId = clientVerId;
        this.objectId = objectId;
        this.firstId = firstId;
        this.parentId = parentId;
        this.name = name;
        this.comment = comment;
        this.isSent = isSent;
    }

    public Optional<UUID> getBindId() {
        return bindId;
    }

    public void setBindId(Optional<UUID> bindId) {
        this.bindId = bindId;
    }

    public Optional<UUID> getClientVerId() {
        return clientVerId;
    }

    public void setClientVerId(Optional<UUID> clientVerId) {
        this.clientVerId = clientVerId;
    }

    public Optional<UUID> getObjectId() {
        return objectId;
    }

    public void setObjectId(Optional<UUID> objectId) {
        this.objectId = objectId;
    }

    public Optional<UUID> getFirstId() {
        return firstId;
    }

    public void setFirstId(Optional<UUID> firstId) {
        this.firstId = firstId;
    }

    public Optional<UUID> getParentId() {
        return parentId;
    }

    public void setParentId(Optional<UUID> parentId) {
        this.parentId = parentId;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getComment() {
        return comment;
    }

    public void setComment(Optional<String> comment) {
        this.comment = comment;
    }

    public Optional<Boolean> getIsSent() {
        return isSent;
    }

    public void setIsSent(Optional<Boolean> isSent) {
        this.isSent = isSent;
    }
}
