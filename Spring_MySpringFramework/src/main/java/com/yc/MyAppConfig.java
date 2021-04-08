package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.biz.StudentBizImpl;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 11:47
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.biz","com.yc.dao"})
public class MyAppConfig {

//    @MyBean
//    public HelloWorld helloWorld(){
//        return new HelloWorld();
//    }
//
//    @MyBean
//    public HelloWorld helloWorld2(){
//        return new HelloWorld();
//    }
    @MyBean
    public StudentBizImpl studentBizImpl(){
        return new  StudentBizImpl();
    }
}
