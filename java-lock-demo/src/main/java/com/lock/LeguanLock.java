package com.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Fun Description //TODO
 * @Date 2020/6/10 14:58 10
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class LeguanLock {

    // 线程不安全
    private static int value1 = 0;

    // 使用乐观锁
    private static AtomicInteger value2 = new AtomicInteger(0);

    // 使用悲观锁
    private static int value3 = 0;

    private static synchronized void increaseValue3() {
        value3++;
    }

    public static void main(String[] args) throws Exception {
        // 开发1000个线程，
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    value1++;
                    value2.getAndIncrement();
                    increaseValue3();
                }
            }).start();
        }
        // 打印结果
        Thread.sleep(1000);
        System.out.println("线程不安全： " + value1);
        System.out.println("乐观锁（AtomicInteger): " + value2);
        System.out.println("悲观锁（synchronized): " + value3);
    }

}
