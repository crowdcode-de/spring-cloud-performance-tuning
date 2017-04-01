package io.crowdcode.flaschenhals.stock;

import io.crowdcode.commons.jpa.annotations.EnableCommonsJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EntityScan // due to enable commons jpa
@EnableCommonsJpa
@EnableDiscoveryClient
@SpringBootApplication
public class StockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }
}
