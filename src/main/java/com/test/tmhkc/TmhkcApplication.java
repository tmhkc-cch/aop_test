package com.test.tmhkc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TmhkcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmhkcApplication.class, args);
    }

    @Autowired
    private Environment env;


}
