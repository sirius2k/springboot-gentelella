package kr.co.redbrush.webapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"kr.co.redbrush.webapp.domain"})
@EnableScheduling
@SpringBootApplication
@Slf4j
public class SpringBootWebApplication {

    public static void main(String args[]) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class);
    }

}
