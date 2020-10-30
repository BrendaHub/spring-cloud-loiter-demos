package com.loiter.guava.guavademo.tool;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;
import sun.rmi.server.UnicastServerRef;

import java.util.List;

/**
 * @author loiter
 * @date 2020/10/30 11:39
 * @description
 */
public class GuavaUtilDemo {

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        UserInfo userInfo1 = Preconditions.checkNotNull(userInfo, "UserInfo 不能为 NULL");
        System.out.println(userInfo1);
        Optional<UserInfo> userInfoOptional = Optional.fromNullable(userInfo);
        Assert.notNull(userInfoOptional.get(), "Option 不能为 null");
        // 判断T是否为Null如果为Null就不拦截， 否则就会通过断言进行拦截掉。 简单讲就是这是userInfoOptional 对象必须要是为null，才可以。
//        Assert.isNull(userInfoOptional, "userInfoOptional is null");
        System.out.println(userInfoOptional.isPresent());
        System.out.println(userInfoOptional.get());


        /**
         * List对象判空处理
         */
        List<String> list = Lists.newArrayList();
        Preconditions.checkNotNull(list, "传入的list不能为null");

        /**
         * 数值类型判断处理
         */
        Long projectId = -12L;
        Preconditions.checkNotNull(projectId, "projectId不能为null");
        try {
            Preconditions.checkArgument(projectId > 0, "输入projectId必须大于0", projectId);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        }
    }

    static class UserInfo {
        private String name;
    }
}
