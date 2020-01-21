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
    public OutPacket createPacket(OutPacket outPacket) {
        packetRepository.saveAndFlush(outPacket);
        return outPacket;
    }

    @Override
    public OutPacket updatePacket(OutPacket outPacket) {
        return null;
    }

    @Override
    public OutPacket getPacket(OutPacket outPacket) {
        return null;
    }

    @Override
    public OutPacket deletePacket(OutPacket outPacket) {
        return null;
    }
}
