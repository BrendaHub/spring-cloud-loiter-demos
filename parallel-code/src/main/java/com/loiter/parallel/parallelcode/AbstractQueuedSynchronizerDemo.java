package com.loiter.parallel.parallelcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author loiter
 * @date 2020/11/4 22:37
 * @description
 */
public class AbstractQueuedSynchronizerDemo {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        Thread.onSpinWait();
        // 线程等待
        executorService.awaitTermination(2, TimeUnit.SECONDS);

        // 关闭线程池
        executorService.shutdown();

        boolean a = false;
        boolean b = true;
        System.out.printf(" a | b = %b ", b |= a);
         b &= a;
        System.out.printf(" a & b = %b ", b);
        System.out.printf(" a | b = %b ", b |= a);

    }
}
