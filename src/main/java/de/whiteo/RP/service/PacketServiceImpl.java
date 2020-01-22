package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Service
public class PacketServiceImpl implements PacketService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PacketRepository packetRepository;

    @Override
    public OutPacket getPacket(String getPacket) {
        return new OutPacket();
    }
}
