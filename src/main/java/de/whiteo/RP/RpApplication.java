package de.whiteo.rp;

import de.whiteo.rp.util.PacketLoop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpApplication.class, args);
        new PacketLoop().run();
    }
}
