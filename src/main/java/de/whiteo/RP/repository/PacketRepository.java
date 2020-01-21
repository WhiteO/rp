package de.whiteo.rp.repository;

import de.whiteo.rp.model.OutPacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Repository
public interface PacketRepository extends JpaRepository<OutPacket, Long> {
}
