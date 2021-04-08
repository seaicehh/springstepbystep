package com.yc.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 14:29
 */
@Repository    //bean 的id
public class StudengMybatisImpl implements StudentDao{
    public int add(String name) {
        System.out.println("mybatis  添加学生");
        return 1;
    }

    public int update(String name, String pwd) {
        System.out.println("mybatis  修改学生信息");
        return 1;
    }
}
