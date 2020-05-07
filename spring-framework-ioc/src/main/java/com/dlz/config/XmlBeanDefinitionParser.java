package com.dlz.config;

import com.dlz.utils.DocumentReader;
import org.dom4j.Document;

/**
 * 读取xml文件
 * @author dlz
 */
public class XmlBeanDefinitionParser {

    public void loadBeanDefinitions(Resource resource) {
        Document document = DocumentReader.createDocument(resource.getInputStream());
    }
}
