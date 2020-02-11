package de.whiteo.rp.controller;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.service.PacketDTO;
import de.whiteo.rp.service.PacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@RestController
public class PacketController {

    @Autowired
    private PacketService packetService;

    @GetMapping("/api/packets/get")
    public Set<OutPacket> getPackets() {
        return packetService.getPackets();
    }

    public void addPacket(PacketDTO packetDTO) {
        packetService.addPacket(packetDTO);
    }

    @GetMapping("/api/packets/count")
    public Integer getPacketsCount() {
        return packetService.getPacketsCount();
    }

    @PostMapping("/api/packet/update/{id}")
    public void updatePackets(@PathVariable String id) {
        packetService.updatePackets(id);
    }
}