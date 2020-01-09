package com.bhcloud.jeefast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.bhcloud.jeefast")
@SpringBootApplication
@EnableAutoConfiguration
public class SpringbootJeefastApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJeefastApplication.class, args);
    }

}
