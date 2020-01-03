package com.example.demo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author yef
 * @date 2019/8/8
 */
public class TestMain {
    private static ExecutorService executorService=Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ExecutionException, InterruptedException {
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return 1000;
        });
        Integer integer = future.get();
        System.out.println(integer);
    }
}
