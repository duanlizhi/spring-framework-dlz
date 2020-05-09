package com.dlz.converter;

/**
 * 类型转换
 *
 * @author dlz
 */
public interface TypeConverter<T> {
    /**
     * 是否可以转换
     * @param clazz
     * @return
     */
    Boolean isCanConverter(Class<?> clazz);

    /**
     * 转换类型
     * @param value
     * @return
     */
    T converter(String value);

}
