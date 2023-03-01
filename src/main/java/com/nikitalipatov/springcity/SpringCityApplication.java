package com.nikitalipatov.springcity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCityApplication.class, args);
    }

}
