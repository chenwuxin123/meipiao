package com.meipiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.meipiao.dao")
public class StudyManualApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyManualApplication.class, args);
    }

}
