package com.lock.entiry;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Fun Description //TODO
 * @Date 2020/6/10 19:11 10
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Data
public class Employee {

    private Long id;

    private int age;

    private String name;

    private LocalDateTime createTime;


}
