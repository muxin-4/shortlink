package com.yaya.shorlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yaya.shortlink.admin.dao.mapper")
public class ShortLinkAdminApllication {

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkAdminApllication.class, args);
    }
}
