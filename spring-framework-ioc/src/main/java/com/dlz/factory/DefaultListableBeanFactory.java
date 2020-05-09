package com.dlz.factory;

import com.dlz.config.*;
import com.dlz.converter.IntegerTypeConverter;
import com.dlz.converter.StringTypeConverter;
import com.dlz.converter.TypeConverter;
import com.dlz.utils.ReflectUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
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
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    /**
     * 存储单例bean信息
     */
    private Map<String, Object> singleBeans = new HashMap<>();
    /**
     * 注册的资源读取类
     */
    private static List<Resource> resources = new ArrayList<>();
    /**
     * 注册的类型转换器
     */
    private static List<TypeConverter> converters = new ArrayList<>();

    static {
        // 注册资源类
        registerResource(new ClassPathResource());
        registerConverter();
    }

    private static void registerConverter() {
        converters.add(new IntegerTypeConverter());
        converters.add(new StringTypeConverter());
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
        xmlBeanDefinitionParser.loadBeanDefinitions(this, first.get());
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
        // b)根据BeanDefinition中封装的属性信息集合去挨个给对象赋值。        // 类型转换
        // 反射赋值
        // c)根据initMethod方法去调用对象的初始化操作

        // 解析xml文件，文件路径存在多种
        Object instance = singleBeans.get(beanName);
        if (Objects.nonNull(instance)) {
            return instance;
        }
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        String beanClassName = beanDefinition.getBeanClassName();
        instance = ReflectUtils.createObject(beanClassName, null);
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        setProperty(instance, propertyValues);
        // 执行初始化方法
        ReflectUtils.invokeInitMethod(instance, beanDefinition.getInitMethod());

        return instance;
    }

    /**
     * 设置property参数
     *
     * @param beanInstance
     * @param propertyValues
     */
    private void setProperty(Object beanInstance, List<PropertyValue> propertyValues) {
        if (CollectionUtils.isEmpty(propertyValues)) {
            return;
        }
        propertyValues.forEach(propertyValue -> {
            // 属性名
            String name = propertyValue.getName();
            // 属性类型
            Object value = propertyValue.getValue();
            if (value instanceof TypeStringValue) {
                // 那么此处是基本类型，进行基本类型值的转换
                TypeStringValue value1 = (TypeStringValue) value;
                String fieldValue = value1.getValue();
                Class<?> classType = value1.getClassType();
                Optional<TypeConverter> first = converters.stream().filter(typeConverter -> typeConverter.isCanConverter(classType)).findFirst();
                Object converter = first.get().converter(fieldValue);
                // 给目标对象设置值
                ReflectUtils.setProperty(beanInstance, name, converter);
            } else if (value instanceof RuntimeBeanReference) {
                // 此处为引用类型
                RuntimeBeanReference beanReference = (RuntimeBeanReference) value;
                String ref = beanReference.getRef();
                // 递归调用getBean方法进行赋值
                Object bean = getBean(ref);
                // 给目标对象设置值
                ReflectUtils.setProperty(beanInstance, name, bean);
            }
        });
    }

    /**
     * 根据bean的类型查找bean
     *
     * @param clazz
     * @return java.lang.Object1
     * @author lizhi
     * @date 2020/4/28 9:14 下午
     */
    @Override
    public Object getBean(Class<?> clazz) {
        return super.getBean(clazz);
    }

    private static void registerResource(ClassPathResource classPathResource) {
        resources.add(classPathResource);
    }

    /**
     * 注册beanDefinition对象
     *
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }
}
