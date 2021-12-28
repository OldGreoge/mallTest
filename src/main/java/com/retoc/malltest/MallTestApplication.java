package com.retoc.malltest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com/retoc/malltest/model/dao")
public class MallTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallTestApplication.class, args);
    }
}
