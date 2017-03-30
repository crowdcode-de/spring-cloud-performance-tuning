package io.crowdcode.flaschenlager.accounting;


import io.crowdcode.commons.jpa.annotations.EnableCommonsJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EntityScan // due to enable commons jpa
@EnableCommonsJpa
@EnableDiscoveryClient
@SpringBootApplication
public class AccountingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountingServiceApplication.class, args);
    }
}
