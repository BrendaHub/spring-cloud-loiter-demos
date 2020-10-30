package com.loiter.functional.functionalcode.functionalinterface;

/**
 * @author loiter
 * @date 2020/10/28 12:00
 * @description
 * 存在五种类型：
 *  - 提供类型： Supplier<T>
 *  - 消费类型： Consumer<T>
 *  - 转换类型： Function<T, R>
 *  - 断定类型： Predicate<T>
 *  - 隐藏类型： Action
 */
public class Demo {


    @FunctionalInterface
    public interface fun1 {
        void exec();
    }

    public interface fun2 {
        void exec();
    }
    // 以上fun1 与 fun2 与下面的fun3 等价
    public interface fun3 {
        public abstract void exe();
    }

    @FunctionalInterface
    public interface fun4 {
        void exec();

        default String getStringDesc(String param) {
            return param.toLowerCase().trim();
        }
    }

    // 以上几个函数定义都是ok的
}
