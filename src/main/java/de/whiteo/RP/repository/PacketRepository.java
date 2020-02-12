package de.whiteo.rp.repository;

import de.whiteo.rp.model.OutPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Repository
public interface PacketRepository extends JpaRepository<OutPacket, Long> {

  @Query(value =
      "select distinct P.ID, P.USER USER, P.BIND_ID CLIENT_ID, P.CLIENT_VER_ID PUSH_VER_ID,"
          +
          " P.COMMENT COMMENT, TC.VALUE_COLUMN CLASS_ID, TN.VALUE_COLUMN OBJECT_NAME,"
          +
          "P.SENT SENT, P.DATE PUSH_DATE, T.VALUE_COLUMN OBJECT_ID,"
          +
          "TA.VALUE_COLUMN ACTION, TR.VALUE_COLUMN REMOVED"
          +
          " from PACKETS P inner join T_CLASS TC on P.CLIENT_VER_ID = TC.CLIENT_VER_ID "
          +
          "inner join T_NAME TN on TC.CLIENT_VER_ID = TN.CLIENT_VER_ID "
          +
          "and TC.KEY_COLUMN = TN.KEY_COLUMN "
          +
          "inner join T_OBJECT T on TN.CLIENT_VER_ID = T.CLIENT_VER_ID "
          +
          "and TN.KEY_COLUMN = T.KEY_COLUMN "
          +
          "inner join T_ACTION TA on T.CLIENT_VER_ID = TA.CLIENT_VER_ID "
          +
          "and T.KEY_COLUMN = TA.KEY_COLUMN "
          +
          "inner join T_REMOVED TR on TA.CLIENT_VER_ID = TR.CLIENT_VER_ID "
          +
          "and TA.KEY_COLUMN = TR.KEY_COLUMN "
          +
          "where P.SENT=FALSE", nativeQuery = true)
  Set<OutPacket> getAllPackets();

  @Query(value = "select COUNT(*) from PACKETS where SENT = false", nativeQuery = true)
  Integer getPacketsCount();

  @Query(value = "select P.id from PACKETS P where P.CLIENT_VER_ID = :key", nativeQuery = true)
  Long getIdByClientVerId(@Param("key") UUID key);
}