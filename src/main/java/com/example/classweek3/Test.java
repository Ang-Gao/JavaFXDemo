package com.example.classweek3;

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
}
