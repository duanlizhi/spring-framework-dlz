package com.dlz.config;

/**
 * 存储运行时bean信息
 * @author dlz
 */
public class RuntimeBeanReference {
    private String ref;

    public RuntimeBeanReference(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }
}
