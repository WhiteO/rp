package de.whiteo.rp.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Entity
@Table(name = "PACKETS")
public class OutPacket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "BIND_ID", columnDefinition = "UUID")
    private UUID bindId;
    @Column(name = "CLIENT_VER_ID", columnDefinition = "UUID")
    private UUID clientVerId;
    @ElementCollection
    @CollectionTable(name = "T_OBJECT", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
            columnDefinition = "UUID", referencedColumnName = "ID"))
    @MapKeyColumn(name = "KEY_COLUMN", columnDefinition = "LONG")
    @Column(name = "VALUE_COLUMN", columnDefinition = "UUID")
    private Map<Long, UUID> objectId;
    @ElementCollection
    @CollectionTable(name = "T_NAME", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
            columnDefinition = "UUID", referencedColumnName = "ID"))
    @MapKeyColumn(name = "KEY_COLUMN", columnDefinition = "LONG")
    @Column(name = "VALUE_COLUMN", columnDefinition = "UUID")
    private Map<Long, String> name;
    @ElementCollection
    @CollectionTable(name = "T_CLASS", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
            columnDefinition = "UUID", referencedColumnName = "ID"))
    @MapKeyColumn(name = "KEY_COLUMN", columnDefinition = "LONG")
    @Column(name = "VALUE_COLUMN", columnDefinition = "UUID")
    private Map<Long, UUID> classId;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "IS_SENT", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean isSent;

    public OutPacket() {
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