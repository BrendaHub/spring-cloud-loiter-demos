package com.loiter.functional.functionalcode.functionalinterface.demo1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author loiter
 * @date 2020/10/29 10:00
 * @description
 */
public class LambdaDemo1 {

    public static void main(String[] args) {
        // 标准的函数式编程，断言判断类的
        Predicate<Start> manPredicate = item -> item.getLength() > 20 && "male".equalsIgnoreCase(item.getName());
        Predicate<Start> fremanpredicate = item -> item.getLength() > 15 && "female".equalsIgnoreCase(item.getName());

        Function<Start, String> funman = item -> "Hi, You are male and length " + item.getLength();
        Function<Start, String> femal = item -> "Hi, You are female and length " + item.getLength();

        ObjectMapper objectMapper = new ObjectMapper();
        Function<String, Start> toStart = item -> {
            Start start = null;
            try {
                start = objectMapper.readValue(item, Start.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return start;
        };

        Consumer<String> consumer = mesg -> {
            File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "a.txt");
            Predicate<File> isFile = File::isFile;
            if (isFile.test(file)) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
                    outputStreamWriter.write(mesg);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        };

        Consumer<Start> startConsumer = item -> {
            File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "a.txt");
            Predicate<File> isFile = File::isFile;
            if (isFile.test(file)) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
                    outputStreamWriter.write(item.show(funman));
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Start s1 = new Start(21, "male", null);
        Start s2 = new Start(21, "female", null);

        if (manPredicate.test(s1)) {
//            System.out.println(s1.show(funman));
            System.out.println(funman.apply(s1));
            s1.xiaoFei(consumer, funman.apply(s1));
            s1.xiaoFei1(startConsumer, toStart.apply("{\"length\": 23, \"name\": \"male\", \"findDate\": \"null\"}"));
        } else {
//            System.out.println(s1.show(femal));
            System.out.println(femal.apply(s1));
        }


    }
}
