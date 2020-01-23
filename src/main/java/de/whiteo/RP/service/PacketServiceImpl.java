package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

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
    public List<OutPacket> getPackets() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<OutPacket> outPacketCriteria = cb.createQuery(OutPacket.class);
        Root<OutPacket> outPacketRoot = outPacketCriteria.from(OutPacket.class);
        outPacketCriteria.multiselect(outPacketRoot.get("bindId"), outPacketRoot.get("clientVerId"),
                outPacketRoot.get("objectId"), outPacketRoot.get("firstId"), outPacketRoot.get("parentId"),
                outPacketRoot.get("name"), outPacketRoot.get("comment"));
        Predicate criteria = cb.conjunction();

        Predicate p = cb.equal(outPacketRoot.get("isSent"), false);
        criteria = cb.and(criteria, p);
        outPacketCriteria.where(criteria);
        return em.createQuery(outPacketCriteria).getResultList();
    }

    @Override
    public void addPacket(OutPacket outPacket) {
        packetRepository.saveAndFlush(outPacket);
    }

    /*@Override
    public void updatePacket(OutPacket requestOutPacket) {
        OutPacket outPacket = packetRepository.findById()
    }*/
}
