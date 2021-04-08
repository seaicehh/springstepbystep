package com.yc.springframework.stereotype;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 11:35
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyRepository {
}
