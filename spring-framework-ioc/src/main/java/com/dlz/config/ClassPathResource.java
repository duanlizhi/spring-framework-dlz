package com.dlz.config;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

/**
 * 读取classpath下的资源
 *
 * @author dlz
 */
public class ClassPathResource implements Resource {
    private String location;

    @Override
    public Boolean isCanRead(String location) {
        if (StringUtils.isEmpty(location)) {
            return Boolean.FALSE;
        }
        if (location.startsWith("classpath:")) {
            this.location = location.replace("classpath:", "");
            return Boolean.TRUE;

        }
        return Boolean.FALSE;
    }

    @Override
    public InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
