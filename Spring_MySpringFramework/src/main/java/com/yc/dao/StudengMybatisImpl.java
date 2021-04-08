package com.yc.dao;

import com.yc.springframework.stereotype.MyRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 14:29
 */
@MyRepository    //bean 的id
public class StudengMybatisImpl implements StudentDao{
    @Override
    public int add(String name) {
        System.out.println("mybatis  添加学生");
        return 1;
    }

    @Override
    public int update(String name, String pwd) {
        System.out.println("mybatis  修改学生信息");
        return 1;
    }
}
