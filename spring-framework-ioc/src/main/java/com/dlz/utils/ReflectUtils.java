package com.dlz.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 反射工具类
 *
 * @author dlz
 */
public class ReflectUtils {
    /**
     * 根据类路径和构造参数
     *
     * @param clazz 类路径
     * @param args  参数
     * @return
     */
    public static Object createObject(String clazz, Object... args) {
        try {
            Class<?> aClass = Class.forName(clazz);
            // 获取构造方法
            Constructor<?> constructor = aClass.getConstructor();
            // 根据构造参数创建实例对象
            return constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过bean的fieldName获取属性类型
     *
     * @param beanClassName
     * @param fieldName
     * @return
     */
    public static Class<?> getTypeByFieldName(String beanClassName, String fieldName) {
        try {
            Class<?> aClass = Class.forName(beanClassName);
            Field declaredField = aClass.getDeclaredField(fieldName);
            return declaredField.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用反射给bean实例属性赋值
     *
     * @param beanInstance bean实例
     * @param fieldName    属性名称
     * @param fieldValue   属性值
     */
    public static void setProperty(Object beanInstance, String fieldName, Object fieldValue) {
        try {
            Class<?> aClass = beanInstance.getClass();
            Field declaredField = aClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(beanInstance, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行初始化方法
     *
     * @param beanInstance
     * @param initMethod
     */
    public static void invokeInitMethod(Object beanInstance, String initMethod) {
        if (Objects.isNull(initMethod)) {
            return;
        }
        try {
            Class<?> beanInstanceClass = beanInstance.getClass();
            Method declaredMethod = beanInstanceClass.getDeclaredMethod(initMethod);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(beanInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
