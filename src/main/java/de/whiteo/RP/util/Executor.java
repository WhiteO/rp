package de.whiteo.rp.util;

import de.whiteo.rp.config.SpringContext;
import de.whiteo.rp.controller.PacketController;
import de.whiteo.rp.service.PacketDTO;
import java.util.List;
import javafx.util.Pair;
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
    Pair<String, String> packetPair = TcpReassembler.doReassemble(packets);
    if (null != packetPair) {
      String pairKey = packetPair.getKey();
      String pairValue = packetPair.getValue();
      if ("commit".equals(pairKey) && !pairValue.isEmpty()) {
        PacketDTO packetDTO = TcpParser.parseCommitXmlFromPacket(pairValue);
        if (null != packetDTO) {
          PACKET_CONTROLLER.addPacket(packetDTO);
        }
      } else if ("version_change".equals(pairKey) && !pairValue.isEmpty()) {
        PacketDTO packetDTO = TcpParser.parseChangeVerXmlFromPacket(pairValue);
        if (null != packetDTO) {
          PACKET_CONTROLLER.updatePacket(packetDTO);
        }
      }
    }
  }
}