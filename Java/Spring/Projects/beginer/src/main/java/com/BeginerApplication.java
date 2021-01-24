package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mybatis.mybatis.mapper")
public class BeginerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeginerApplication.class, args);
    }

}
