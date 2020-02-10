package de.whiteo.rp.service;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.repository.PacketRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@Service
public class PacketServiceImpl implements PacketService {

    private final PacketRepository packetRepository;

    public PacketServiceImpl(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @Override
    public Set<OutPacket> getPackets() {
        return packetRepository.getAllPackets();
    }

    @Override
    public void addPacket(PacketDTO packetDTO) {

        List<UUID> uuidsListToDeleteFromMaps = new ArrayList<>();

        for (Map.Entry<String, UUID> entry : packetDTO.getClassIdMap().entrySet()) {
            if (0 != packetRepository.countByKeyColumn(entry.getKey())) {
                uuidsListToDeleteFromMaps.add(entry.getValue());
            }
        }

        for (UUID uuidListToDeleteFromMap : uuidsListToDeleteFromMaps) {
            packetDTO.getClassIdMap().remove(uuidListToDeleteFromMap);
            packetDTO.getObjectIdMap().remove(uuidListToDeleteFromMap);
        }

        List<String> stringsListToDeleteFromMaps = new ArrayList<>();

        for (Map.Entry<String, String> entry : packetDTO.getNameMap().entrySet()) {
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
    public Integer getPacketsCount() {
        return packetRepository.getPacketsCount();
    }

    @Override
    public void updatePackets(String uuid) {
        try {
            Long packetId = packetRepository.getIdByClientVerId(UUID.fromString(uuid));
            OutPacket outPacket = packetRepository.findById(packetId).orElseThrow(Exception::new);
            outPacket.setSent(true);
            packetRepository.save(outPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}