package com.yc.dao;

import org.springframework.stereotype.Component;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-04 14:24
 */

public interface StudentDao {
    public int add(String name);
    public int update(String name,String pwd);
}
