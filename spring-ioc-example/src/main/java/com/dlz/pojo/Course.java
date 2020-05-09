package com.dlz.pojo;

public class Course {
    private String name;
    private Integer age;

    public Course() {
    }

    public void init() {
        System.out.println("执行初始化方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
