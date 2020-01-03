package com.example.demo.config;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yef
 * @date 2019/8/8
 */
@Component
public class TestAdvice implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("11111111111111111111111");
        Object proceed = methodInvocation.proceed();
        return  proceed;
    }
}
