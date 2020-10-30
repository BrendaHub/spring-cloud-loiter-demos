package com.loiter.functional.functionalcode.functionalinterface;

import java.util.function.Consumer;

/**
 * @author loiter
 * @date 2020/10/29 0:23
 * @description
 *
 * {@link Consumer} 只进不出的函数编程函数， 一定是返回值为void的方法，
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        Consumer<String> fun1 = System.out::println;
//        fun1.accept("hello world");

        Consumer<String> fun2 = ConsumerDemo::echo;

//        fun2.andThen(fun1).accept("ant-loiter-consumer");

        ConsumerDemo.display(fun1, "Hello Consumer Fun");
        ConsumerDemo.display(fun2, "Hello Consumer Fun2");

    }

    public static void display(Consumer<String> consumer, String message) {
        consumer.accept(message);
    }

    public static void echo (String message) {
        System.out.println("echo > " + message);
    }

}
