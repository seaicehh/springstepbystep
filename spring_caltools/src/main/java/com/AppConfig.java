package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 10:47
 */
@Configuration
@ComponentScan("com")
public class AppConfig {
    @Bean
    public Random r(){
        return new Random();
    }
}
