package de.whiteo.rp.controller;

import de.whiteo.rp.model.Logger;
import de.whiteo.rp.model.OutPacket;
import de.whiteo.rp.service.ApiService;
import de.whiteo.rp.dto.PacketDTO;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

@RestController
public class ApiController {

  @Autowired
  private ApiService apiService;

  @GetMapping("/api/packets/get")
  public Set<OutPacket> getPackets() {
    return apiService.getPackets();
  }

  public void updateOrAddPacket(PacketDTO packetDTO) {
    apiService.updateOrAddPacket(packetDTO);
  }

  @GetMapping("/api/packets/count")
  public Integer getPacketsCount() {
    return apiService.getPacketsCount();
  }

  public void addLog(Logger logger) {
    apiService.addLogger(logger);
  }

  @GetMapping("/api/errors/get")
  public Set<Logger> getLoggers() {
    return apiService.getLoggers();
  }

  @GetMapping("/api/errors/count")
  public Integer getLoggerCount() {
    return apiService.getLoggersCount();
  }
}