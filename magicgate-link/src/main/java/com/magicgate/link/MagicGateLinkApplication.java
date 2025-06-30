package com.magicgate.link;

import com.magicgate.link.domain.client.LLMProviderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.magicgate.common","com.magicgate.link"})
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class MagicGateLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicGateLinkApplication.class, args);
    }
}
