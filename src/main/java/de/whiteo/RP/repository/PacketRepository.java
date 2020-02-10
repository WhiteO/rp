package de.whiteo.rp.repository;

import de.whiteo.rp.model.OutPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Repository
public interface PacketRepository extends JpaRepository<OutPacket, Long> {
    @Query(value = "select COUNT(*) from T_CLASS where KEY_COLUMN= :key", nativeQuery = true)
    Integer countByKeyColumn(@Param("key") String key);

    @Query(value = "select distinct P.ID, P.BIND_ID as CLIENT_ID, " +
            "P.CLIENT_VER_ID as PUSH_VER_ID, P.COMMENT as COMMENT, TC.VALUE_COLUMN as CLASS_ID, " +
            "TN.VALUE_COLUMN as OBJECT_NAME, P.IS_SENT as SENT from PACKETS P " +
            "inner join T_CLASS TC on P.CLIENT_VER_ID = TC.CLIENT_VER_ID " +
            "inner join T_NAME TN on TC.CLIENT_VER_ID = TN.CLIENT_VER_ID and TC.KEY_COLUMN = TN.KEY_COLUMN " +
            "inner join T_OBJECT T on TN.CLIENT_VER_ID = T.CLIENT_VER_ID and TN.KEY_COLUMN = T.KEY_COLUMN " +
            "where P.IS_SENT=FALSE", nativeQuery = true)
    Set<OutPacket> getAllPackets();

}
