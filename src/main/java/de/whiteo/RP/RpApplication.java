package de.whiteo.rp;

import de.whiteo.rp.util.GetPackets;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpApplication {

  private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RpApplication.class);

  public static void main(String[] args) throws PcapNativeException, NotOpenException {
    SpringApplication.run(RpApplication.class, args);

    if (0 != args.length) {
      GetPackets.run(args[0]);
    } else {
      LOGGER.info("to get device name, use 'getmac /fo csv /v'");
      GetPackets.run("\\Device\\NPF_Loopback");
    }
  }
}