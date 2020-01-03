package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author yef
 * @date 2019/8/7
 */
@Service
public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext _applicationContext) throws BeansException {
        applicationContext=_applicationContext;
    }
    public static <T> T getBean(Class<T> clazz){
        return  applicationContext.getBean(clazz);
    }
}
