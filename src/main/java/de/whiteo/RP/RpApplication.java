package de.whiteo.rp;

import de.whiteo.rp.debug.DebugSingleton;
import de.whiteo.rp.tcp.packetsEx.GetPackets;
import org.pcap4j.core.PcapNativeException;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpApplication {

  private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RpApplication.class);

  public static void main(String[] args) throws PcapNativeException {
    SpringApplication.run(RpApplication.class, args);

    if (0 != args.length) {
      if (2 == args.length && args[1].contains("-debug")) {
        DebugSingleton.setEnableTrue();
        LOGGER.warn("debug mode is ON");
      }
      String result;
      if (args[0].contains("Tcpip")) {
        result = args[0].replace("Tcpip", "NPF");
      }
      else {
        result = args[0];
      }
      GetPackets.run(result);
    } else {
      LOGGER.warn("to get device name, use 'getmac /fo csv /v'");
      GetPackets.run("\\Device\\NPF_Loopback");
    }
  }
}