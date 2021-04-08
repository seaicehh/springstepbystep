package com.yc.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @program: testSpring
 * @description:
 * @author: Dasiy
 * @create: 2021-04-05 11:32
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBean {
    String value() default "";
}
