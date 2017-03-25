package io.crowdcode.flaschenlager.stock;

import io.crowdcode.commons.jpa.annotations.EnableCommonsJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan // due to enable commons jpa
@EnableCommonsJpa
@SpringBootApplication
public class StockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }
}
