package com.dlz.converter;

/**
 * 字符串类型转换
 *
 * @author dlz
 */
public class StringTypeConverter implements TypeConverter<String> {
    @Override
    public Boolean isCanConverter(Class<?> clazz) {
        return String.class == clazz;
    }

    @Override
    public String converter(String value) {
        return value;
    }
}
