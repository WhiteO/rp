package de.whiteo.rp.controller;

import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.service.PacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@RestController
public class PacketController {

    @Autowired
    private PacketService packetService;

    @GetMapping("/api")
    public OutPacket getPacket(@PathVariable String getPacket) {
        return packetService.getPacket(getPacket);
    }

}
