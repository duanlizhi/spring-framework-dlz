package com.dlz.config;

import java.io.InputStream;

/**
 * 资源接口
 * @author dlz
 */
public interface Resource {
    /**
     * 资源是否可读
     * @param location 资源地址
     * @return
     */
    Boolean isCanRead(String location);
    /**
     * 获取资源流文件
     * @return
     */
    InputStream getInputStream();
}
