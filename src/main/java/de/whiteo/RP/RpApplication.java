package de.whiteo.rp;

import de.whiteo.rp.util.DebugSingleton;
import de.whiteo.rp.util.GetPackets;
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
      GetPackets.run(args[0]);
    } else {
      LOGGER.warn("to get device name, use 'getmac /fo csv /v' and replace Tcpip with NPF");
      GetPackets.run("\\Device\\NPF_Loopback");
    }
  }
}