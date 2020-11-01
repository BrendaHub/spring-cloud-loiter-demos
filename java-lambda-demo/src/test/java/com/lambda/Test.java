package com.lambda;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.tools.jconsole.JConsole;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.function.*;

/**
 * @Fun Description //TODO
 * @Date 2020/6/17 15:30 17
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class Test {
    public static void main(String[] args) {
        Predicate<Integer> predicate = x -> x > 185;
        Predicate<Integer> predicate1 = y -> y > 23;
        Student student = new Student("9龙", 23, 175);
        System.out.println("9龙的身高高于185吗？：" + predicate.test(student.getLen()));
        Predicate<Integer> temp = predicate.and(predicate1);
        System.out.println(temp.test(186));
        System.out.println(predicate.equals(new Integer(185)));


        Consumer<String> consumer = System.out::println;
//        consumer = String::toString ;
        consumer.accept("命运由我不由天！");
        Consumer<String> consumer1 = consumer.andThen(System.out::println);
        consumer1.accept("最后一次显示");

        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        Function<Student, Integer> function1 = Student::getLen;
        int len = function1.apply(student);
        System.out.println(len);

        Function<Student, Integer> function2 = Student::getAge;
        int age = function2.apply(student);
        System.out.println(age);

        Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        Supplier<String> supplier1 = () -> "Hello, Functioniterface";
        System.out.println(supplier1.get());

        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        BinaryOperator<Integer> binaryOperator = (x, y) -> x * y;
        Integer integer = binaryOperator.apply(4, 5);
        System.out.println(integer);

        test(() -> "我是一个演示函数式 接口");
    }

    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    @FunctionalInterface
    public interface Worker {
        String work();
    }
}

class Student {
    private String name;
    private int age;
    private int len;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public Student(String name, int age, int len) {
        this.name = name;
        this.age = age;
        this.len = len;
    }
}