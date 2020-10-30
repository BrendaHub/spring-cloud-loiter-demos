package com.loiter.functional.functionalcode.runable;

/**
 * @author loiter
 * @date 2020/10/28 22:03
 * @description
 */
public class RunableDemo {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("采用匿名内部类的方式实现");
            }
        };

        // invokeDynamic 是个指令
        Runnable runnable1 = () -> {
            System.out.println("InvokeDyname 实现");
        };

        new Thread(runnable).start();
        new Thread(runnable1).start();
    }
}
