package com.yc;

import com.yc.biz.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 15:17
 */
@Configuration   //表示当前类是一个配置类
@ComponentScan(basePackages = "com.yc")   //将来要托管的bean 要扫描的包及子包
public class AppConfig {

    @Bean
    public HelloWorld helloWorld(){
        return  new HelloWorld();
    }
}
