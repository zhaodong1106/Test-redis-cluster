package com.example.demo.config;

import com.example.demo.annotation.LoggerTest;
import com.example.demo.interceptor.MyInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.lang.annotation.Annotation;

/**
 * @author yef
 * @date 2019/8/7
 */
@Configuration
public class MyConfig extends WebMvcConfigurationSupport {
    private Class<? extends Annotation> classAnnotationType=LoggerTest.class;
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//       registry.addInterceptor(new MyInterceptor());
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        DefaultPointcutAdvisor defaultPointcutAdvisor=new DefaultPointcutAdvisor();
        AnnotationMatchingPointcut matchingPointcut=new AnnotationMatchingPointcut(classAnnotationType,true);
        defaultPointcutAdvisor.setPointcut(matchingPointcut);
        defaultPointcutAdvisor.setAdvice(testAdvice);
        return defaultPointcutAdvisor;
    }
    @Autowired
    private TestAdvice testAdvice;

}
