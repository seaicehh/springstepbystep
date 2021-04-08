package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class HelloWorldTest {
    private ApplicationContext ac;//spring 容器

    @Before
    public void setUp() throws Exception {
        //AnnotationConfigApplicationContext  基于注解的容器配置类
        ac=new AnnotationConfigApplicationContext(AppConfig.class);
        //读取  AppConfig.class  -->  basePackage="com.yc"   ->得到要扫描的luj
        //检查这些包中是否存在@conponent注解  如果有  则实例化
        //存到一个Map<String,Object>      ===ac
    }

    @Test
    public void hello() {
        HelloWorld hw=(HelloWorld) ac.getBean("helloWorld");
        hw.hello();

        HelloWorld hw2=(HelloWorld) ac.getBean("helloWorld");
        hw.hello();


    }

}