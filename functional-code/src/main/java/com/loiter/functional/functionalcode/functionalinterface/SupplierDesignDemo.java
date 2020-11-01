package com.loiter.functional.functionalcode.functionalinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author loiter
 * @date 2020/10/29 14:15
 * {@link java.util.function.Supplier} 设计
 * Supplier 编程范式是， 只进不出，
 * @description
 */
public class SupplierDesignDemo {
    private static Logger logger = LoggerFactory.getLogger(SupplierDesignDemo.class);

    public static void main(String[] args) {
        echo("Hello println message ");
        echo(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, Supplier";
        });

        Supplier<String> stringSupplier = SupplierDesignDemo.supplyMessage();
        String s = stringSupplier.get();
        System.out.println(">>>" + s);
    }

    public static void echo(String message) {
        logger.info("原始的echo打印： {}", message);
    }

    public static String getMessage() {
        return "Hello, Getmessage";
    }

    public static Supplier<String> supplyMessage() {
        return SupplierDesignDemo::getMessage;
    }

    public static void echo(Supplier<String> message) {
        logger.info("采用supplier进行打印， {}", message.get());
    }
}
