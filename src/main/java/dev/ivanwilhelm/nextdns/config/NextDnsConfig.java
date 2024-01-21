package dev.ivanwilhelm.nextdns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.REST_TEMPLATE_NAME;

@Configuration
public class NextDnsConfig {
    @Bean(value = REST_TEMPLATE_NAME)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
