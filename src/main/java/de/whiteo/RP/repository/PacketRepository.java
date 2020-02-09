package de.whiteo.rp.repository;

import de.whiteo.rp.model.OutPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Repository
public interface PacketRepository extends JpaRepository<OutPacket, Long> {
    @Query(value = "select COUNT(*) from T_CLASS where KEY_COLUMN= :key", nativeQuery = true)
    Integer countByKeyColumn(@Param("key") String key);

    @Query(value = "select PACKETS.ID, PACKETS.BIND_ID as CLIENT_ID, PACKETS.CLIENT_VER_ID as PUSH_VER_ID," +
            "PACKETS.COMMENT as COMMENT, TC.VALUE_COLUMN as CLASS_ID, TN.VALUE_COLUMN as NAME_ID," +
            " T.VALUE_COLUMN as OBJECT_ID from PACKETS " +
            "inner join T_CLASS TC on PACKETS.CLIENT_VER_ID = TC.CLIENT_VER_ID " +
            "inner join T_NAME TN on PACKETS.CLIENT_VER_ID = TN.CLIENT_VER_ID " +
            "inner join T_OBJECT T on PACKETS.CLIENT_VER_ID = T.CLIENT_VER_ID " +
            "where PACKETS.IS_SENT=FALSE", nativeQuery = true)
    List<OutPacket> getAllPackets();

}
