package com.dlz.config;

import com.dlz.factory.DefaultListableBeanFactory;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;
import java.util.Objects;

/**
 * 解析document文档信息
 * @author dlz
 */
public class XmlBeanDefinitionDocumentParser {
    private DefaultListableBeanFactory defaultListableBeanFactory;

    public XmlBeanDefinitionDocumentParser(DefaultListableBeanFactory defaultListableBeanFactory) {
        this.defaultListableBeanFactory = defaultListableBeanFactory;
    }

    public void loadBeanDefinitions(Element rootElement) {
        List<Element> elements = rootElement.elements();
        elements.forEach(element -> {
            // 获取标签名称
            String name = element.getName();
            if ("bean".equals(name)) {
                parserBeanDefinition(element);
            } else {
                parserCustomDefinition(element);
            }
        });
        // 2.将bean信息封装到BeanDefinition对象中
        // 对bean标签解析解析
        // class信息
        // id信息
        // init-method信息
        // property标签信息----PropertyValue对象（name和value）
        // name信息
        // value信息
        // value属性---属性值、属性类型（属性赋值的时候，需要进行类型转换）TypedStringValue
        // ref属性---RuntimeBeanReference(bean的名称)---根据bean的名称获取bean的实例，将获取到的实例赋值该对象
        // 3.再将BeanDefinition放入beanDefinations集合对象中
    }

    public void parserBeanDefinition(Element element) {
        if (Objects.isNull(element)) {
            return;
        }
        try {
            String id = element.attributeValue("id");
            String clazz = element.attributeValue("class");
            String name = element.attributeValue("name");
            String initMethod = element.attributeValue("init-method");
            // 如果存在id那么beanName就是id，否则为name
            String beanName = StringUtils.isEmpty(id) ? name : id;
            // 使用反射获取bean实例
            Class<?> aClass = Class.forName(clazz);
            String simpleName = aClass.getClass().getSimpleName();
            beanName = StringUtils.isEmpty(beanName) ? simpleName : beanName;

            BeanDefinition beanDefinition = new BeanDefinition(beanName, clazz);
            beanDefinition.setInitMethod(initMethod);
            // todo 解析property标签
            defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parserCustomDefinition(Element element) {
    }

}
