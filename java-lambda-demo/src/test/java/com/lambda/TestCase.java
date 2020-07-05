package com.lambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * @Fun Description //TODO
 * @Date 2020/6/17 16:33 17
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class TestCase {
    public static void main(String[] args) throws JsonProcessingException {
        List<Student> studentList = Stream.of(new Student("路飞", 23,123),
                new Student("红发", 40, 180),
                new Student("白胡子", 50, 185))
                .collect(Collectors.toList());
        //对象映射
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(studentList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // filter 过滤，内部就是Predicate接口，惰性求值
        List<Student> collect = studentList.stream().filter(item -> item.getAge() > 30).collect(Collectors.toList());
        System.out.println("collect filter is : " + collect);

        // map 转换功能， 内部就是Function 接口， 惰性求值
        List<String> collect1 = studentList.stream().map(item -> item.getName()).collect(Collectors.toList());
        System.out.println("map list is : " + collect1);

        List<Integer> collect2 = studentList.stream().map(item -> item.getAge()).collect(Collectors.toList());
        System.out.println(collect2);


        // flatmap 将多个stream合并为一个stream， 惰性求值
        List<Student> collect3 = Stream.of(studentList, asList(new Student("艾斯", 25, 183),
                new Student("雷利", 48, 176)))
                .flatMap(item -> item.parallelStream())//.filter(i -> i.getAge() > 30))
                .filter(item -> item.getAge() > 35)
                .collect(Collectors.toList());
        try {
            System.out.println(objectMapper.writeValueAsString(collect3));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // max min

        Optional<Student> max = studentList.stream().max(Comparator.comparing(item -> item.getAge()));
        try {
            System.out.println("max = " + objectMapper.writeValueAsString(max.get()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 这里在获得一个年龄最的的stutend对象
        Optional<Student> min = studentList.stream()
                .min(Comparator.comparing(item -> item.getAge()));
        try {
            System.out.println("Min = " + (max.isPresent()?objectMapper.writeValueAsString(min.get()):objectMapper.writeValueAsString(new Student("默认", 0, 0 ))));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // count
        long count = studentList.stream().filter(i -> i.getAge() < 45).count();
        System.out.println("年龄小于45年的student = " + count);

        // reduce 可以实现从一组值 中生成一个值， 在以上count, min, max 都是属于reduce操作
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6).reduce(0, (total, i) -> total + i);
        System.out.println("reduce is " + reduce);
        Integer reduce1 = Stream.of(23, 45, 22, 1).reduce(1, (i, j) -> i * j);
        System.out.println(reduce1 + ":" + 23*45*22*1);

        // 数据进行分块， 分块完后就是boolean: [] 这种结构
        Map<Boolean, List<Student>> 雷利 = studentList.stream().collect(Collectors.partitioningBy(item -> item.getName()
                .contains("红发")));
        System.out.println(objectMapper.writeValueAsString(雷利));

        Map<Boolean, List<Student>> hongfa = studentList.stream().collect(Collectors.partitioningBy(item ->
                item.getName().contentEquals("红发")));
        System.out.println(objectMapper.writeValueAsString(hongfa));

        Map<Boolean, List<Student>> collect4 = studentList.stream().collect(Collectors.partitioningBy(item ->
                item.getName().matches("^红.")));
        System.out.println(objectMapper.writeValueAsString(collect4));

        Map<Boolean, List<Student>> collect5 = studentList.stream().collect(Collectors.partitioningBy(item ->
                item.getName().matches(".胡.")));
        System.out.println(objectMapper.writeValueAsString(collect5));

        // 数据分组
        Map<String, List<Student>> collect6 = studentList.stream().collect(Collectors.groupingBy(item ->
                item.getName()));

        System.out.println(objectMapper.writeValueAsString(collect6));

        Double collect7 = studentList.stream().collect(Collectors.averagingInt(item -> item.getAge()));
        System.out.println(Math.round(collect7));
        System.out.println(collect7);

        // 字符串拼接 如果将所有学生的名字拼接起来， 怎么做呢。 通常只能创建一个StringBuilder 循环拼接， 使用Stream 使用Collectors.joining() 简单容易

        String collect8 = studentList.stream().map(item -> item.getName()).collect(Collectors.joining("#"));
        System.out.println(collect8);
        List<Integer> collect9 = studentList.stream().map(i -> i.getAge()).filter(item -> item.intValue() > 10)
                .collect(Collectors.toList());
        System.out.println(collect9);

        Integer reduce2 = studentList.parallelStream().map(item -> item.getLen())
                .reduce(0, (x, y) -> x + y);
        System.out.println("身高总和为： " + reduce2);
        //  求出气 有身高的平均
        double avgLen = studentList.parallelStream().mapToInt(item -> item.getLen())
                .average().getAsDouble();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        System.out.println("身高平均值为： " + decimalFormat.format(avgLen));

    }
}
