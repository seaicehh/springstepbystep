package com.yc.dao;

import org.springframework.stereotype.Repository;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 14:40
 */
@Repository
public class StudentDaoPajmpl implements StudentDao{
    @Override
    public int add(String name) {
        System.out.println("paj   添加学生");
        return 1;
    }

    @Override
    public int update(String name, String pwd) {
        System.out.println("paj    修改学生信息");
        return 1;
    }
}
