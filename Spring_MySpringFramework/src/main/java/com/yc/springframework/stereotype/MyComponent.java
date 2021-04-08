package com.yc.springframework.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 11:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface MyComponent {
}
