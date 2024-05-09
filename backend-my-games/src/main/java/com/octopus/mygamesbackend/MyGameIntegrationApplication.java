package com.octopus.mygamesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MyGameIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGameIntegrationApplication.class, args);
    }

}
