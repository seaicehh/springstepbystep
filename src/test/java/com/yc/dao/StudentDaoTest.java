package com.yc.dao;


import com.yc.AppConfig;
import com.yc.biz.StudentBizImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private StudentDao studentDao;
    private StudentBizImpl studentBizImpl;
    private ApplicationContext ac;//spring 容器

    @Before
    public void setUp() throws Exception {
        ac=new AnnotationConfigApplicationContext(AppConfig.class);

        //1.能否自动完成  实例化对象   -》  ioc 控制反转   -》 由容器实例化对象  ，由容器完成
//        studentDao=new StudengMybatisImpl();
//        studentBizIml=new StudentBizImpl();

         studentDao=(StudentDao) ac.getBean("studengMybatisImpl");
        //StudengMybatisImpl studengMybatisImpl=(StudengMybatisImpl) ac.getBean("studengMybatisImpl");
        //studentDao =new StudengMybatisImpl();
        //2.能否自动完成  装配过程 -》DI  依赖注入   -》由容器装配对象
//        studentBizIml.setStudentDao(studentDao);

         studentBizImpl=ac.getBean("studentBizImpl",StudentBizImpl.class);
         studentBizImpl.setStudentDao(studentDao);

    }

    @Test
    public void add() {

        studentBizImpl.add("张三");


    }

    @Test
    public void update() {

        studentBizImpl.update("张三","111");
    }

}