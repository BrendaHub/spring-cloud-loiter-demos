package com.lambda.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * @Fun Description  @link com.javaBeans
 * @Date 2020/6/20 23:24 20
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Persion.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
            .forEach(
                    item -> {
                        Class<?> proertyType = item.getPropertyType();
                        String propertyName = proertyType.getName();
                        if("age".equals(propertyName)) {
                            item.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        }
                    }
            );
        ;
        Stream.of(beanInfo.getEventSetDescriptors())
            .forEach(System.out::println);
        ;
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
     }
}
