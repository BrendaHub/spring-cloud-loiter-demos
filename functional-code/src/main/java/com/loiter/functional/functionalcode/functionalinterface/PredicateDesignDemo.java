package com.loiter.functional.functionalcode.functionalinterface;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * @author loiter
 * @date 2020/10/30 11:12
 * @description Predicate Demo
 */
public class PredicateDesignDemo {

    public static void main(String[] args) {
        // Predicate 主要的使用场景就是过滤判断
    }
    // 自定义一个偶数过滤器 even
    private static <T> Collection<T> filter(Collection<T> collection, Predicate<T> p) {
        // 一般需要对参数集合进行一个浅拷贝操作

    }
}
