package com.dlz.converter;

/**
 * 整数类型转换
 *
 * @author dlz
 */
public class IntegerTypeConverter implements TypeConverter<Integer> {
    @Override
    public Boolean isCanConverter(Class<?> clazz) {
        return Integer.class == clazz;
    }

    @Override
    public Integer converter(String value) {
        return Integer.valueOf(value);
    }
}
