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

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            if (Thread.interrupted()) {
                System.out.println("当前线程被interrupted " + Thread.interrupted());
                System.out.println("Hello, Interrupted ");
            }
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            thread1.interrupt();
            if(thread1.isInterrupted()) {
                System.out.println("thread1 is interrupted");
            }
            try {
                thread1.join(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

    }

    private static void helloWorld() {
        System.out.println("Hello, World");
    }
}
