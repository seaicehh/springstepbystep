package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.biz.StudentBizImpl;
import com.yc.springframework.context.MyAnnotationConfigApplication;
import com.yc.springframework.context.MyApplicationContext;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 18:25
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MyApplicationContext ac=new MyAnnotationConfigApplication(MyAppConfig.class);
//        HelloWorld hw=(HelloWorld) ac.getBean("helloWorld");
//        hw.show();

        StudentBizImpl studentBiz= (StudentBizImpl) ac.getBean("studentBizImpl");
        studentBiz.add("撒三");
        studentBiz.update("撒三","111");

    }
}
