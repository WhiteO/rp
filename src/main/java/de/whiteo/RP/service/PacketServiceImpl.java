package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public void addPacket(PacketDTO packetDTO) {

        List<UUID> listToDeleteFromMaps = new ArrayList<>();

        for (Map.Entry<Long, UUID> entry : packetDTO.getClassId().entrySet()) {
            if (packetRepository.existsByKeyColumn(entry.getKey())) {
                listToDeleteFromMaps.add(entry.getValue());
            }
        }

        for (int i = 0; i < listToDeleteFromMaps.size(); i++) {
            packetDTO.getClassId().remove(listToDeleteFromMaps.get(i));
            packetDTO.getName().remove(listToDeleteFromMaps.get(i));
            packetDTO.getObjectId().remove(listToDeleteFromMaps.get(i));
        }

        OutPacket outPacket = packetDTO.convertToOutPacket();

        if (outPacket != null) {
            packetRepository.saveAndFlush(outPacket);
        } else {
            System.out.println("Err");
        }
    }

    @Override
    public void updatePackets(List<OutPacket> outPacketList) {
        for (OutPacket op : outPacketList) {
            try {
                OutPacket outPacket = packetRepository.findById(op.getId()).orElseThrow(() -> new Exception());
                outPacket.setSent(true);
                packetRepository.save(outPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
