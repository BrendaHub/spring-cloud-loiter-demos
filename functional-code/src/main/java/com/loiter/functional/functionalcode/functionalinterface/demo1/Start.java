package com.loiter.functional.functionalcode.functionalinterface.demo1;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author loiter
 * @date 2020/10/29 9:53
 * @description
 */
public class Start {

    private int length;
    private String name;
    private Date findDate;

    public Start() {
        this.length = 1;
        this.name = "hello";
        this.findDate = Date.from(Instant.now());
    }

    public Start(int length, String name, Date firstDate) {
        this.length = length;
        this.name = name;
        this.findDate = firstDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFindDate() {
        return findDate;
    }

    public void setFindDate(Date findDate) {
        this.findDate = findDate;
    }

    public String show(Function<Start, String> func) {
        System.out.println("======I'm Function.apply======" + func.apply(this));
        return "显示信息： length=" + this.length + ";name=" + this.name + ";Date=" + findDate;
    }

    public void xiaoFei(Consumer<String> consumer, String msg) {
        consumer.accept(msg);
    }

    public void xiaoFei1(Consumer<Start> consumer, Start start) {
        consumer.accept(start);
    }
}
