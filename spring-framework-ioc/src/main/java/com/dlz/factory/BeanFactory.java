package com.dlz.factory;

/**
 * ioc容器父接口
 *
 * @author duan_lizhi
 * @date 2020/4/28 21:08
 */
public interface BeanFactory {
    /**
     * 通过单例bean的名称查找bean名称
     *
     * @param beanName
     * @return java.lang.Object
     * @author lizhi
     * @date 2020/4/28 9:09 下午
     */
    Object getBean(String beanName);

    /**
     * 根据bean的类型查找bean
     *
     * @param clazz
     * @return java.lang.Object
     * @author lizhi
     * @date 2020/4/28 9:14 下午
     */
    Object getBean(Class<?> clazz);
}
