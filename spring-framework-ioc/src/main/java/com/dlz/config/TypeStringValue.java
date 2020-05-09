package com.dlz.config;

/**
 * 存储基本数据类型
 * @author dlz
 */
public class TypeStringValue {
    private String value;
    private Class<?> classType;

    public TypeStringValue(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    public String getValue() {
        return value;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
