package com.wx.bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Fun Description //TODO
 * @Date 2020/5/28 15:24 28
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Data
public class User {
    private String userName;
    private Integer age;
    private LocalDateTime birthday;
}
