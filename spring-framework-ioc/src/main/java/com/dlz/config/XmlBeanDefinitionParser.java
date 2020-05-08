package com.dlz.config;

import com.dlz.factory.DefaultListableBeanFactory;
import com.dlz.utils.DocumentReader;
import org.dom4j.Document;

/**
 * 读取xml文件
 *
 * @author dlz
 */
public class XmlBeanDefinitionParser {
    public XmlBeanDefinitionParser() {
    }

    public void loadBeanDefinitions(DefaultListableBeanFactory defaultListableBeanFactory, Resource resource) {
        Document document = DocumentReader.createDocument(resource.getInputStream());
        XmlBeanDefinitionDocumentParser xmlBeanDefinitionDocumentParser = new XmlBeanDefinitionDocumentParser(defaultListableBeanFactory);
        // 解析子集标签
        xmlBeanDefinitionDocumentParser.loadBeanDefinitions(document.getRootElement());
    }
}
