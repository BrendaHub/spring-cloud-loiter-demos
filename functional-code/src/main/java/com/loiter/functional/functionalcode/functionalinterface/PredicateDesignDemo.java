package com.loiter.functional.functionalcode.functionalinterface;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author loiter
 * @date 2020/10/30 11:12
 * @description Predicate Demo
 * Predicate 类拟断言式的判断， 采用了函数式的编程模式 ；
 */
public class PredicateDesignDemo {

    public static void main(String[] args) {
        // Predicate 主要的使用场景就是过滤判断
        List<Integer> one = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Collection<Integer> filter = filter(one, item -> item % 2 == 0);/// 返回的是奇数集合
        filter.stream().forEach(System.out::println);

        Stream.of("one", "two", "three", "four")
                        .filter(e -> e.length() > 3)
                        .peek(e -> System.out.println("Filtered value: " + e))
                        .map(String::toUpperCase)
                        .peek(e -> System.out.println("Mapped value: " + e))
                        .collect(Collectors.toList());
    }
    // 自定义一个偶数过滤器 even
    private static <T> Collection<T> filter(Collection<T> collection, Predicate<T> p) {
        // 一般需要对参数集合进行一个浅拷贝操作
        List<T> copy = new ArrayList<T>(collection);
        Iterator<T> iterator = copy.iterator();
        while (iterator.hasNext()) {
            if (p.test(iterator.next())) {
                iterator.remove();
            }
        }

        return Collections.unmodifiableCollection(copy);
    }
}
