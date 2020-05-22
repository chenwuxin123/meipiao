package com.meipiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.meipiao.dao")
public class StudyToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyToolApplication.class, args);
    }

}
