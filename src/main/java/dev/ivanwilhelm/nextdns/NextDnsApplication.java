package dev.ivanwilhelm.nextdns;

import dev.ivanwilhelm.nextdns.properties.NextDnsProperties;
import dev.ivanwilhelm.nextdns.properties.SchedulerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({NextDnsProperties.class, SchedulerProperties.class})
public class NextDnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NextDnsApplication.class, args);
    }
}
