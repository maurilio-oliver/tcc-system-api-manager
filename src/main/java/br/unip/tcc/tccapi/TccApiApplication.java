package br.unip.tcc.tccapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.unip.tcc.tccapi.repository")
public class TccApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TccApiApplication.class, args);
    }

}
