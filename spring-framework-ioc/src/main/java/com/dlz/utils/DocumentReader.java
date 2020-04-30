package com.dlz.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * 解析xml文件
 *
 * @author duan_lizhi
 * @date 2020/4/29 09:37
 */
public class DocumentReader {
    /**
     * 解析xml文件
     *
     * @param inputStream
     * @return org.dom4j.Document
     * @author lizhi
     * @date 2020/4/29 9:46 上午
     */
    public static Document createDocument(InputStream inputStream) {
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
