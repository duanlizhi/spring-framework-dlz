package com.dlz.factory;

import com.dlz.config.BeanDefinition;
import com.dlz.config.ClassPathResource;
import com.dlz.config.Resource;
import com.dlz.config.XmlBeanDefinitionParser;
import com.dlz.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;
import java.util.*;

/**
 * 默认bean工厂实现
 *
 * @author duan_lizhi
 * @date 2020/4/29 09:22
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory {
    /**
     * 存储beanDefinition信息
     */
    Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    /**
     * 存储单例bean信息
     */
    Map<String, Object> singleBeans = new HashMap<>();
    /**
     * 注册的资源读取类
     */
    List<Resource> resources = new ArrayList<>();

    static {
        // 注册资源类
        registerResource(new ClassPathResource());
    }

//    1.读取配置文件的bean信息
//		2.将bean信息封装到BeanDefinition对象中
//            对bean标签解析解析
//    class信息
//            id信息
//    init-method信息
//    property标签信息----PropertyValue对象（name和value）
//    name信息
//            value信息
//    value属性---属性值、属性类型（属性赋值的时候，需要进行类型转换）TypedStringValue
//    ref属性---RuntimeBeanReference(bean的名称)---根据bean的名称获取bean的实例，将获取到的实例赋值该对象
//		3.再将BeanDefinition放入集合对象中

    public DefaultListableBeanFactory(String location) {
        Optional<Resource> first = resources.stream().filter(resource -> resource.isCanRead(location)).findFirst();
        // 构造函数中初始化bean信息，解析资源文件
        XmlBeanDefinitionParser xmlBeanDefinitionParser = new XmlBeanDefinitionParser();
        xmlBeanDefinitionParser.loadBeanDefinitions(first.get());
    }

    /**
     * 通过单例bean的名称查找bean名称
     *
     * @param beanName
     * @return java.lang.Object
     * @author lizhi
     * @date 2020/4/28 9:09 下午
     */
    @Override
    public Object getBean(String beanName) {
        // 优化方案
        // 给对象起个名，在xml配置文件中，建立名称和对象的映射关系
        // 1.如果singletonObjects中已经包含了我们要找的对象，就不需要再创建了。
        // 2.如果singletonObjects中已经没有包含我们要找的对象，那么根据传递过来的beanName参数去BeanDefinition集合中查找对应的BeanDefinition信息
        // 3.根据BeanDefinition中的信息去创建Bean的实例。
        // a)根据class信息包括里面的constructor-arg通过反射进行实例化
        // b)根据BeanDefinition中封装的属性信息集合去挨个给对象赋值。
        // 类型转换
        // 反射赋值
        // c)根据initMethod方法去调用对象的初始化操作

        // 解析xml文件，文件路径存在多种
        String location = "";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        Document document = DocumentReader.createDocument(inputStream);

        return null;
    }

    /**
     * 根据bean的类型查找bean
     *
     * @param clazz
     * @return java.lang.Object
     * @author lizhi
     * @date 2020/4/28 9:14 下午
     */
    @Override
    public Object getBean(Class<?> clazz) {
        return super.getBean(clazz);
    }

    private static void registerResource(ClassPathResource classPathResource) {
    }

    /**
     * 注册beanDefinition对象
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }
}
