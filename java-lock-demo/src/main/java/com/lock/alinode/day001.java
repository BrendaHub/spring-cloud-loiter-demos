package com.lock.alinode;

import javafx.util.Pair;
import org.apache.ibatis.jdbc.Null;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Fun Description // Java开发手册代码
 * @Date 2020/7/5 20:32 05
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class day001 {

    public static void main(String[] args) {
//        int dayOfYear = LocalDate.now().getDayOfYear();
//        System.out.println("一年中的天数： " + dayOfYear);
//        System.out.println(LocalDate.now().getYear());
//        System.out.println(checkLeap(LocalDate.now().getYear()));
//        // 获取指定某年的天数
//        int lengthOfYear = LocalDate.of(2020, 7, 5).lengthOfYear();
//        System.out.println("lengthOfYear is " + lengthOfYear);

//        threeItem();
//        String _hashCode = "abc";
//        String _hashCode1 = new String("def");
//        System.out.println(_hashCode.hashCode());
//        System.out.println(_hashCode);
//        System.out.println("hasCode is " + _hashCode1.hashCode() + "  " + _hashCode1);

//        boolean result = checkCollectionsIsEmpty();
//        System.out.println("result is " + result);

//        int leftMove30 = 1 << 31;
//        System.out.println(leftMove30);
//        System.out.println(Integer.toBinaryString(leftMove30));
//        System.out.println(Integer.toOctalString(leftMove30));
//        System.out.println(Integer.toHexString(leftMove30));

        collectsToMap();
    }

    private static boolean checkLeap(int year) {
        // check Leap function
        return year > 1900 && year < 99999 ?
                ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) : false;
    }

    private static void collectsToMap() {
        List<Pair<String, Double>> pairList = new ArrayList<>(3);
        pairList.add(new Pair<>("version", 6.19));
        pairList.add(new Pair<>("version", 10.24));
        pairList.add(new Pair<>("version", 13.14));
        Map<String, Double> map = pairList.stream().collect(
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1 + v2)
        );
        System.out.println(map);

        List<Pair<String, String>> pairs = new ArrayList<>(10);
        pairs.add(new Pair<>("beijing", "OK"));
        pairs.add(new Pair<>("jiujiang", "success"));
        pairs.add(new Pair<>("wuhan", "fail"));
        Map<String, String> newMap = pairs.stream().filter(item -> !item.getKey().equals("")).collect(
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1 + v2)
        );
        System.out.println("?>>" + newMap);


        List<Pair<String, String>> pairs1 = new ArrayList<>(3);
        pairs1.add(new Pair<>("beijing", "OK"));
        pairs1.add(new Pair<>("jiujiang", "success"));
        pairs1.add(new Pair<>("heli", "fail"));
        Map<String, Integer> map2 = pairs1.stream().collect(
                Collectors.toMap(Pair::getKey, item -> item.getValue().hashCode(), (v1, v2) -> v1.hashCode() + v2.hashCode())
        );
        System.out.println(map2);
    }

    /**
     * @return boolean
     * site: https://www.ant-loiter.com
     * @Author chenhj(brenda)
     * @Description // 集合的size()方法的时间复杂度比isEmpty()要高；
     * @Date 22:52 2020/7/5
     * @Param []
     **/
    private static boolean checkCollectionsIsEmpty() {
        Map<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            hashMap.put(String.valueOf(i), "value_" + i);
        }
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int len = hashMap.size();
            System.out.println("len is " + len);
        }
        long end = System.currentTimeMillis();
        long firstTimes = end - begin;

        begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            boolean rel = hashMap.isEmpty();
            System.out.println(" result is " + rel);
        }
        end = System.currentTimeMillis();

        System.out.println(" size() method use time is " + firstTimes);
        System.out.println(" isEmpty method use time is " + (end - begin));
        return hashMap.isEmpty();
    }

    /**
     * @return void
     * site: https://www.ant-loiter.com
     * @Author chenhj(brenda)
     * @Description //【强制】三目运算符 condition? 表达式 1 : 表达式 2 中，高度注意表达式 1 和 2 在类型对齐
     * 时，可能抛出因自动拆箱导致的 NPE 异常。 {@link Integer } 三目运算操作
     * @Date 20:54 2020/7/5
     * @Param []
     **/
    private static void threeItem() {
        Integer a = 2;
        Integer b = 4;
        Integer c = 0;
        boolean flag = false;
        int reult = flag ? a * b : c;
        // 这个例子里， 事比会做 c.intValue() 这个的操作， 但是C 为null ；
        // 这其实就是因为装箱和拆箱导致的NPE的问题。
        //
        System.out.println("result is " + reult);
    }
}
