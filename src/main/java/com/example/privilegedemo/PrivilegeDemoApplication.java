package com.example.privilegedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.example.privilegedemo.mapper")
public class PrivilegeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivilegeDemoApplication.class, args);
    }

}
