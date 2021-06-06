package org.winker.winweb.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(value = {"org.winker.*"})
@MapperScan({"org.winker.winweb.dao.mysql","mysql.mapper.*.xml"})
//@ConfigurationProperties(prefix="application")


public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
