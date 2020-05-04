package de.whiteo.rp.util;

import de.whiteo.rp.config.SpringContext;
import de.whiteo.rp.controller.ApiController;
import de.whiteo.rp.model.Logger;
import de.whiteo.rp.dto.PacketDTO;
import de.whiteo.rp.tcp.TcpReassembler;
import de.whiteo.rp.tcp.parser.TcpParser;
import java.util.List;
import java.util.Set;
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
    API_CONTROLLER = (ApiController) context.getBean("apiController");
  }

  public static void doExecute(List<TcpPacket> packets) {
    Pair<String, String> packetPair = TcpReassembler.doReassemble(packets);
    if (null != packetPair) {
      String pairKey = packetPair.getKey();
      String pairValue = packetPair.getValue();
      if (!pairValue.isEmpty()) {
        if ("commit".equals(pairKey)) {
          PacketDTO packetDTO = TcpParser.parseCommitXmlFromPacket(pairValue);
          if (null != packetDTO) {
            API_CONTROLLER.updateOrAddPacket(packetDTO);
          }
        } else if ("version_change".equals(pairKey)) {
          PacketDTO packetDTO = TcpParser.parseVersionChangeXmlFromPacket(pairValue);
          if (null != packetDTO) {
            API_CONTROLLER.updateOrAddPacket(packetDTO);
          }
        } else if ("versions".equals(pairKey)) {
          Set<PacketDTO> packetDTOSet = TcpParser.parseVersionsXmlFromPacket(pairValue);
          if (null != packetDTOSet) {
            for (PacketDTO packetDTO : packetDTOSet) {
              if (null != packetDTO) {
                API_CONTROLLER.updateOrAddPacket(packetDTO);
              }
            }
          }
        }
      }
    }
  }

  public static void doLogger(Logger logger) {
    API_CONTROLLER.addLog(logger);
  }
}