package com.dlz.factory;

/**
 * 抽象工厂
 *
 * @author duan_lizhi
 * @date 2020/4/29 09:18
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    /**
     * 通过单例bean的名称查找bean名称
     *
     * @param beanName
     * @return java.lang.Object
     * @author lizhi
     * @date 2020/4/28 9:09 下午
     */
    public Object getBean(String beanName) {
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
    public Object getBean(Class<?> clazz) {
        return null;
    }
}
