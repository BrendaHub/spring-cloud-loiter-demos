package com.loiter.guava.guavademo.retryer;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author loiter
 * @date 2020/10/30 12:00
 * @description 采用guava实现重复信誉度机制
 * {@see RetryerBuilder}
 */
public class RetryDemo {

    public static void main(String[] args) throws Exception {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder().retryIfResult(Predicates.isNull()) // 设置自定义段无重试源
                .retryIfExceptionOfType(Exception.class)// 设置异常重试源
                .retryIfRuntimeException() // 设置异常重试源
                .withStopStrategy(StopStrategies.stopAfterAttempt(5)) // 设置重试次数， 如何设置每一次的超时时间
                .withWaitStrategy(WaitStrategies.fixedWait(5L, TimeUnit.SECONDS))// 设置重试的间隔时间
                .build();

        Callable<Boolean> task = new Callable<Boolean>() {
            int i = 0;
            @Override
            public Boolean call() throws Exception {
                i++;
                System.out.println(String.format("第%d次执行", i));
                if (i < 3) {
                    System.out.println("模拟执行失败");
                    throw new IOException("异常");
                }
                return true;
            }
        };

        try{
            retryer.call(task);
        }catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }

        Boolean call = task.call();
        System.out.println(String.format("成功输出结果：%b", call));
    }
}
