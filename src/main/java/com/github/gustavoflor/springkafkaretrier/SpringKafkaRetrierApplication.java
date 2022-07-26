package com.github.gustavoflor.springkafkaretrier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class SpringKafkaRetrierApplication {
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaRetrierApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        TimeZone.setDefault(UTC);
    }
}
