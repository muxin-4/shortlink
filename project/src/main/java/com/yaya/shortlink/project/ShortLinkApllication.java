package com.yaya.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yaya.shortlink.project.dao.mapper")
public class ShortLinkApllication {

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApllication.class, args);
    }
}
