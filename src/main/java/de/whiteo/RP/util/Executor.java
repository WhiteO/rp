package de.whiteo.rp.util;

import de.whiteo.rp.config.SpringContext;
import de.whiteo.rp.controller.PacketController;
import de.whiteo.rp.service.PacketDTO;
import java.util.List;
import org.pcap4j.packet.TcpPacket;
import org.springframework.context.ApplicationContext;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class Executor {

  private static final PacketController PACKET_CONTROLLER;

  static {
    ApplicationContext context = SpringContext.getAppContext();
    PACKET_CONTROLLER = (PacketController) context.getBean("packetController");
  }

  public static void doExecute(List<TcpPacket> packets) {
    String packetText = TcpReassembler.doReassemble(packets);
    if (!packetText.isEmpty()) {
      PacketDTO packetDTO = TcpParser.parseXmlFromPacket(packetText);
      if (null != packetDTO.getClientVerId()) {
        PACKET_CONTROLLER.addPacket(packetDTO);
      }
    }
  }
}