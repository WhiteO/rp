package de.whiteo.rp.service;

import com.thoughtworks.xstream.annotations.*;
import com.thoughtworks.xstream.converters.basic.UUIDConverter;

import java.util.List;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */
@XStreamAlias("crs:call")
public class PacketDTO {

    @XStreamAlias("crs:auth")
    private String autch;
    @XStreamOmitField
    private List<UUID> objectId;
    @XStreamOmitField
    private List<UUID> parentId;
    @XStreamOmitField
    private List<String> name;
    @XStreamAlias("crs:bind")
    @XStreamConverter(value= UUIDConverter.class)
    @XStreamAsAttribute
    private UUID bindID;
    @XStreamAlias("value")
    private UUID clientVerId;
    @XStreamAlias("comment")
    private String comment;
    @XStreamOmitField
    private Boolean isSent;

    public PacketDTO() {
    }

    public PacketDTO(List<UUID> objectId, List<UUID> parentId, List<String> name, UUID bindId, UUID clientVerId, String comment, Boolean isSent) {
        this.objectId = objectId;
        this.parentId = parentId;
        this.name = name;
        this.bindID = bindId;
        this.clientVerId = clientVerId;
        this.comment = comment;
        this.isSent = isSent;
    }

    public List<UUID> getObjectId() {
        return objectId;
    }

    public void setObjectId(List<UUID> objectId) {
        this.objectId = objectId;
    }

    public List<UUID> getParentId() {
        return parentId;
    }

    public void setParentId(List<UUID> parentId) {
        this.parentId = parentId;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public UUID getBindId() {
        return bindID;
    }

    public void setBindId(UUID bindId) {
        this.bindID = bindId;
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
