package com.lock.alinode;

import java.time.LocalDate;

/**
 * @Fun Description // Java开发手册代码
 * @Date 2020/7/5 20:32 05
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class day001 {

    public static void main(String[] args) {
        int dayOfYear = LocalDate.now().getDayOfYear();
        System.out.println("一年中的天数： " + dayOfYear);
        System.out.println(LocalDate.now().getYear());
        System.out.println(checkLeap(LocalDate.now().getYear()));
        // 获取指定某年的天数
        int lengthOfYear = LocalDate.of(2020, 7, 5).lengthOfYear();
        System.out.println("lengthOfYear is " + lengthOfYear);
    }

    private static boolean checkLeap(int year) {
        // check Leap function
        return year > 1900 && year < 99999 ?
                ( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0) : false;
    }
}
