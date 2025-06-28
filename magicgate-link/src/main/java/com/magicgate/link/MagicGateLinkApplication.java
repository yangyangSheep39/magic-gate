package com.magicgate.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.magicgate.common"})
public class MagicGateLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(MagicGateLinkApplication.class, args);
    }
}
