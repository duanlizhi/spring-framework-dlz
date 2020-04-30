package com.dlz.config;

import java.util.ArrayList;
import java.util.List;

/**
 * bean定义对象
 *
 * @author duan_lizhi
 * @date 2020/4/29 09:55
 */
public class BeanDefinition {
    private String beanName;
    private String beanClassName;
    /**
     * bean定义中的初始化方法（注意：非构造函数）
     */
    private String initMethod;
    /**
     * bean定义中的属性信息
     */
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public BeanDefinition(String beanName, String beanClassName) {
        this.beanName = beanName;
        this.beanClassName = beanClassName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    /**
     * 提供注入bean的属性信息集合
     *
     * @param propertyValue
     * @return void
     * @author lizhi
     * @date 2020/4/30 9:52 上午
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }
}
