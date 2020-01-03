package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yef
 * @date 2019/8/8
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerTest {
    Class<?>[] value() default {};
}
