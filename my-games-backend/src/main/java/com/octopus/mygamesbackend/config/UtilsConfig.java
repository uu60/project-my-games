package com.octopus.mygamesbackend.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class UtilsConfig {

    @Bean
    public ThreadPoolExecutor pool() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    }
}
