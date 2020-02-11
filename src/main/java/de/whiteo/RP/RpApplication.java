package de.whiteo.rp;

import de.whiteo.rp.util.PacketLoop;
import org.pcap4j.core.PcapNativeException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpApplication {

    public static void main(String[] args) throws PcapNativeException {
        SpringApplication.run(RpApplication.class, args);

        new PacketLoop().run();
    }
}