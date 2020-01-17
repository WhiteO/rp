package de.whiteo.rp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class OutPacket {

    private UUID bindID;
    private UUID clientVerID;
    private UUID objectID;
    private UUID firstID;
    private UUID parentID;
    private String name;
    private String comment;
    private Boolean isAccepted;

    public OutPacket() {
    }

    public OutPacket(UUID bindID, UUID clientVerID, UUID objectID, UUID firstID, UUID parentID,
                     String name, String comment, Boolean isAccepted) {
        this.bindID = bindID;
        this.clientVerID = clientVerID;
        this.objectID = objectID;
        this.firstID = firstID;
        this.parentID = parentID;
        this.name = name;
        this.comment = comment;
        this.isAccepted = isAccepted;
    }

    public UUID getBindID() {
        return bindID;
    }

    public void setBindID(UUID bindID) {
        this.bindID = bindID;
    }

    public UUID getClientVerID() {
        return clientVerID;
    }

    public void setClientVerID(UUID clientVerID) {
        this.clientVerID = clientVerID;
    }

    public UUID getObjectID() {
        return objectID;
    }

    public void setObjectID(UUID objectID) {
        this.objectID = objectID;
    }

    public UUID getFirstID() {
        return firstID;
    }

    public void setFirstID(UUID firstID) {
        this.firstID = firstID;
    }

    public UUID getParentID() {
        return parentID;
    }

    public void setParentID(UUID parentID) {
        this.parentID = parentID;
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

    public Boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        this.isAccepted = accepted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bindID, clientVerID, objectID, firstID, parentID, name, comment, isAccepted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutPacket outPacket = (OutPacket) o;
        return Objects.equals(bindID, outPacket.bindID) &&
                Objects.equals(clientVerID, outPacket.clientVerID) &&
                Objects.equals(objectID, outPacket.objectID) &&
                Objects.equals(firstID, outPacket.firstID) &&
                Objects.equals(parentID, outPacket.parentID) &&
                Objects.equals(name, outPacket.name) &&
                Objects.equals(comment, outPacket.comment) &&
                Objects.equals(isAccepted, outPacket.isAccepted);
    }
}