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

    private final PacketRepository packetRepository;

    public PacketServiceImpl(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

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

        List<UUID> uuidsListToDeleteFromMaps = new ArrayList<>();

        for (Map.Entry<Long, UUID> entry : packetDTO.getClassIdMap().entrySet()) {
            if (0 != packetRepository.countByKeyColumn(entry.getKey())) {
                uuidsListToDeleteFromMaps.add(entry.getValue());
            }
        }

        for (UUID uuidListToDeleteFromMap : uuidsListToDeleteFromMaps) {
            packetDTO.getClassIdMap().remove(uuidListToDeleteFromMap);
            packetDTO.getObjectIdMap().remove(uuidListToDeleteFromMap);
        }

        List<String> stringsListToDeleteFromMaps = new ArrayList<>();

        for (Map.Entry<Long, String> entry : packetDTO.getNameMap().entrySet()) {
            if (0 != packetRepository.countByKeyColumn(entry.getKey())) {
                stringsListToDeleteFromMaps.add(entry.getValue());
            }
        }

        for (String stringsListToDeleteFromMap : stringsListToDeleteFromMaps) {
            packetDTO.getNameMap().remove(stringsListToDeleteFromMap);
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
                OutPacket outPacket = packetRepository.findById(op.getId()).orElseThrow(Exception::new);
                outPacket.setSent(true);
                packetRepository.save(outPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
