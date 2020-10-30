package com.loiter.parallel.parallelcode.parallel;

/**
 * @author loiter
 * @date 2020/10/28 10:15
 * @description
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(ThreadDemo::helloWorld);
        thread.start();
        thread.join();
    }

    private static void helloWorld() {
        System.out.println("Hello, World");
    }
}
