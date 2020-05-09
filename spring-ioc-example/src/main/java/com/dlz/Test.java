package com.dlz;

import com.dlz.factory.DefaultListableBeanFactory;

public class Test {
    public static void main(String[] args) {
        String location = "classpath:beans.xml";
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory(location);
        Object student = defaultListableBeanFactory.getBean("student");
        System.out.println(student);
    }
}
