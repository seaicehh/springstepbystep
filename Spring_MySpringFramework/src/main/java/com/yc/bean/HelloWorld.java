package com.yc.bean;

import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestroy;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 11:46
 */
//@MyComponent
public class HelloWorld {

    @MyPostConstruct
    public void setUp(){
        System.out.println("MyPostConstruct");
    }
    @MyPreDestroy
    public void destroy(){
        System.out.println("MyPreDestroy");
    }

    public HelloWorld(){
        System.out.println("hello world 构造");
    }
    public void show(){
        System.out.println("show");
    }
}
