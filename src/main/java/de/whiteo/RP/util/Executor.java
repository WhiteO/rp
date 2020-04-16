package de.whiteo.rp.util;

import de.whiteo.rp.config.SpringContext;
import de.whiteo.rp.controller.ApiController;
import de.whiteo.rp.service.PacketDTO;
import java.util.List;
import javafx.util.Pair;
import org.pcap4j.packet.TcpPacket;
import org.springframework.context.ApplicationContext;

/**
 * @author Ruslan Tanas {@literal <skyuser13@gmail.com>}
 */

public class Executor {

  private static final ApiController API_CONTROLLER;

  static {
    ApplicationContext context = SpringContext.getAppContext();
    API_CONTROLLER = (ApiController) context.getBean("packetController");
  }

  public static void doExecute(List<TcpPacket> packets) {
    Pair<String, String> packetPair = TcpReassembler.doReassemble(packets);
    if (null != packetPair) {
      String pairKey = packetPair.getKey();
      String pairValue = packetPair.getValue();
      if ("commit".equals(pairKey) && !pairValue.isEmpty()) {
        PacketDTO packetDTO = TcpParser.parseCommitXmlFromPacket(pairValue);
        if (null != packetDTO) {
          API_CONTROLLER.addPacket(packetDTO);
        }
      } else if ("version_change".equals(pairKey) && !pairValue.isEmpty()) {
        PacketDTO packetDTO = TcpParser.parseChangeVerXmlFromPacket(pairValue);
        if (null != packetDTO) {
          API_CONTROLLER.updatePacket(packetDTO);
        }
      }
    }
  }
}