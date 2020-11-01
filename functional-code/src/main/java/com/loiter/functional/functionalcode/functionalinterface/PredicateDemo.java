package com.loiter.functional.functionalcode.functionalinterface;

import java.awt.*;
import java.io.File;
import java.util.function.Predicate;

/**
 * @author loiter
 * @date 2020/10/29 9:37
 * @description {@see Predicate}
 * {@link java.util.function.Predicate}
 */
public class PredicateDemo {

    public static void main(String[] args) {
        Predicate<File> _file = file -> true;

        System.out.println(_file.test(new File("d:/a.txt")));
    }
}
