package com.yc.biz;

import org.springframework.stereotype.Component;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 15:16
 */
@Component    //此注解代表这个类可以被spring容器托管
public class HelloWorld {
    public HelloWorld(){
        System.out.println("无参构造方法");
    }

    public void hello(){
        System.out.println("hello world");
    }
}
