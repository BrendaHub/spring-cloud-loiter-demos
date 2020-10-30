package com.loiter.functional.functionalcode.functionalinterface;

/**
 * @author loiter
 * @date 2020/10/28 22:17
 * @description
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {

        Functioninterface1 functioninterface1 = () -> {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        };

        functioninterface1.exec();

        Runnable r = () -> {
            System.out.println("run started ");
        };

        new Thread(r).start();
    }

    @FunctionalInterface
    public interface Functioninterface1 {
        void exec();
    }
}
