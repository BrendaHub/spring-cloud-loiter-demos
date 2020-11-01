package com.lock.list;

import com.google.common.collect.ImmutableList;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Fun Description // Arrays.asList, SubLIst
 * @Date 2020/7/5 18:04 05
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class ArrayListDemo {

    public static void main(String[] args) {
//        String[] array = {"a", "b", "c"};
//        List<String> list = Arrays.asList(array);
//        list.parallelStream().forEach(System.out::println);
//
////        list.forEach(item -> {
////            if(item.equals("a")) {
////                list.remove(item);
////            }
////        });
//
////        for (String s : list) {
////            if (s.equals("a")) {
////                list.remove(s);
////            }
////            System.out.println(s);
////        }
//        list.parallelStream().filter("a"::equals).forEach(System.out::println);

        String[] arrays = new String[5];
        arrays[0] = "1";
        arrays[1] = "2";
        arrays[2] = "3";
        String[] ar1 = {"4", "5"};
        arrays[3] = "44";
        List<String> list = new ArrayList<>(Arrays.asList(arrays));

//        for (String s : list) {
//            if (s.equals("2")) {
////                list.remove(s);
//                // 不能在foreach里进行remove
//                list.removeIf("1"::equals);
//            }
//        }
//
//        list.removeIf("2"::equals);
        list.forEach(System.out::println);

        // http://ifeve.com/google-guava/
        // 不可变集合
        List<String> _list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        // 需要JDK9 支持
//        List<String> unmodifiableList = List.of(_list.toArray(new String[]{}));

        List<String> unmodifiableList1 = ImmutableList.copyOf(_list);
        unmodifiableList1.forEach(System.out::println);

    }
}
