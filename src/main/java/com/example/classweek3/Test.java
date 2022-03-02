package com.example.classweek3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {

    @org.junit.Test
    public void t1(){

    }

    public static void main(String[] args) {
        Runnable t1 = ()-> { for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread());
        }};


        new Thread(t1).start();
        System.out.println(Thread.currentThread());
    }
    @org.junit.Test
    public void threadPoolTest(){
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(()->{
            System.out.println("hello world");
        });
    }
}
