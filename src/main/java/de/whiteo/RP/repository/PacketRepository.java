package de.whiteo.rp.repository;

import de.whiteo.rp.model.OutPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Repository
@Component
public interface PacketRepository extends JpaRepository<OutPacket, Long> {
    @Query(value = "select COUNT(*) from T_CLASS where KEY_COLUMN= :key", nativeQuery = true)
    public boolean existsByKeyColumn(@Param("key") Long key);
}
