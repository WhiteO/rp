package de.whiteo.rp.controller;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.service.PacketDTO;
import de.whiteo.rp.service.PacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@RestController
public class PacketController {

    @Autowired
    private PacketService packetService;

    @GetMapping("/api/getPackets")
    public Set<OutPacket> getPacket() {
        Set<OutPacket> outPacketList = packetService.getPackets();
        packetService.updatePackets(outPacketList);
        return outPacketList;
    }

    public void addPacket(PacketDTO packetDTO) {
        packetService.addPacket(packetDTO);
    }
}
