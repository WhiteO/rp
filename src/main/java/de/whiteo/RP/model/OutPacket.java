package de.whiteo.rp.model;

import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "BIND_ID", columnDefinition = "UUID")
    private UUID bindId;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "CLIENT_VER_ID", columnDefinition = "UUID")
    private UUID clientVerId;
    @ElementCollection
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @CollectionTable(name = "T_OBJECT", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
            columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
    @MapKeyColumn(name = "KEY_COLUMN", columnDefinition = "CHAR(40)")
    @Column(name = "VALUE_COLUMN", columnDefinition = "UUID")
    private Map<String, UUID> objectIdMap;
    @ElementCollection
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @CollectionTable(name = "T_NAME", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
            columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
    @MapKeyColumn(name = "KEY_COLUMN", columnDefinition = "CHAR(40)")
    @Column(name = "VALUE_COLUMN", columnDefinition = "VARCHAR(255)")
    private Map<String, String> nameMap;
    @ElementCollection
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @CollectionTable(name = "T_CLASS", joinColumns = @JoinColumn(name = "CLIENT_VER_ID",
            columnDefinition = "UUID", referencedColumnName = "CLIENT_VER_ID"))
    @MapKeyColumn(name = "KEY_COLUMN", columnDefinition = "CHAR(40)")
    @Column(name = "VALUE_COLUMN", columnDefinition = "UUID")
    private Map<String, UUID> classIdMap;
    @Column(name = "COMMENT", columnDefinition = "VARCHAR(255)")
    private String comment;
    @Column(name = "IS_SENT", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean isSent;

    public OutPacket() {
    }

    public OutPacket(Long id, UUID bindId, UUID clientVerId, Map<String, UUID> objectIdMap, Map<String,
            UUID> classIdMap, Map<String, String> nameMap, String comment) {
        this.id = id;
        this.bindId = bindId;
        this.clientVerId = clientVerId;
        this.objectIdMap = objectIdMap;
        this.classIdMap = classIdMap;
        this.nameMap = nameMap;
        this.comment = comment;
    }

    public Long getId() {
        return id;
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

    public Map<String, UUID> getObjectIdMap() {
        return objectIdMap;
    }

    public void setObjectIdMap(Map<String, UUID> objectIdMap) {
        this.objectIdMap = objectIdMap;
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<String, String> nameMap) {
        this.nameMap = nameMap;
    }

    public Map<String, UUID> getClassIdMap() {
        return classIdMap;
    }

    public void setClassIdMap(Map<String, UUID> classIdMap) {
        this.classIdMap = classIdMap;
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