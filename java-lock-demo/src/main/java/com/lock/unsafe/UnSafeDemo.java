package com.lock.unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Fun Description //TODO
 * @Date 2020/7/5 16:31 05
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class UnSafeDemo {

    private static Integer values = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {

        for (int i = 0 ; i < 100000; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    values ++;
                    atomicInteger.incrementAndGet();
                }
            }).start();
        }

        System.out.println(" values is " + values);
        System.out.println(" atomicInteger is " +  atomicInteger);
    }
}
