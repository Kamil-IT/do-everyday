package com.doeveryday.doeverydayweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.doeveryday.*"})
@EntityScan(basePackages = {"com.doeveryday.*"})
@EnableJpaRepositories(basePackages = {"com.doeveryday.*"})
public class DoEverydayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoEverydayWebApplication.class, args);

    }

}
