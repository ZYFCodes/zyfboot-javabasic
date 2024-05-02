package org.zyf.javabasic.spring.failureanalyzer.checker;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.zyf.javabasic.spring.failureanalyzer.exception.ConfigFileFormatException;
import org.zyf.javabasic.spring.failureanalyzer.model.ConfigFileFormatErrorInfo;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

import static org.zyf.javabasic.spring.failureanalyzer.model.ConfigFileFormatErrorInfo.ErrorType.*;

/**
 * @program: zyfboot-javabasic
 * @description: 指定配置文件验证逻辑
 * @author: zhangyanfeng
 * @create: 2024-05-02 20:12
 **/
@Component
public class ConfigFileFormatChecker {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void checkConfigFileFormat() {
        String fileName = "report-config.xml";
        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        if (!resource.exists()) {
            throw new ConfigFileFormatException(new ConfigFileFormatErrorInfo(true, null, fileName));
        }

        Element root = null;
        try (InputStream inputStream = resource.getInputStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(inputStream));

            // 获取根元素
            root = document.getDocumentElement();

        } catch (Exception e) {
            throw new ConfigFileFormatException(new ConfigFileFormatErrorInfo(true, OTHER, fileName));
        }

        // 检查 dataSource Bean 定义
        checkDataSourceDefinition(root, fileName);

        // 检查报告生成器定义
        checkReportGeneratorDefinition(root, fileName);
    }

    private void checkDataSourceDefinition(Element root, String fileName) {
        // 获取 dataSource 元素
        NodeList dataSourceList = root.getElementsByTagName("bean");
        for (int i = 0; i < dataSourceList.getLength(); i++) {
            Element dataSourceElement = (Element) dataSourceList.item(i);
            String id = dataSourceElement.getAttribute("id");
            if ("dataSource".equals(id)) {
                // 获取 driverClassName 属性
                String driverClassName = dataSourceElement.getElementsByTagName("property")
                        .item(0)
                        .getAttributes()
                        .getNamedItem("value")
                        .getNodeValue();

                // 获取 url 属性
                String url = dataSourceElement.getElementsByTagName("property")
                        .item(1)
                        .getAttributes()
                        .getNamedItem("value")
                        .getNodeValue();

                // 获取 username 属性
                String username = dataSourceElement.getElementsByTagName("property")
                        .item(2)
                        .getAttributes()
                        .getNamedItem("value")
                        .getNodeValue();

                // 获取 password 属性
                String password = dataSourceElement.getElementsByTagName("property")
                        .item(3)
                        .getAttributes()
                        .getNamedItem("value")
                        .getNodeValue();

                if (StringUtils.isAnyBlank(driverClassName, url, username, password)) {
                    throw new ConfigFileFormatException(new ConfigFileFormatErrorInfo(false, MISSING_PROPERTY, fileName));
                }

                if (!isPasswordEncrypted(password)) {
                    throw new ConfigFileFormatException(new ConfigFileFormatErrorInfo(false, INVALID_VALUE, fileName));
                }
            }
        }

    }

    private void checkReportGeneratorDefinition(Element root, String fileName) {
        // 获取 reportGenerator 元素
        NodeList reportGeneratorList = root.getElementsByTagName("bean");
        for (int i = 0; i < reportGeneratorList.getLength(); i++) {
            Element reportGeneratorElement = (Element) reportGeneratorList.item(i);
            String id = reportGeneratorElement.getAttribute("id");
            if ("reportGenerator".equals(id)) {
                // 获取 dataSource 属性的引用
                String dataSourceRef = reportGeneratorElement.getElementsByTagName("property")
                        .item(0)
                        .getAttributes()
                        .getNamedItem("ref")
                        .getNodeValue();
                if (StringUtils.isAnyBlank(dataSourceRef)) {
                    throw new ConfigFileFormatException(new ConfigFileFormatErrorInfo(false, MISSING_PROPERTY, fileName));
                }
            }
        }
    }

    private boolean isPasswordEncrypted(String password) {
        // 检查密码是否已加密，这里可以根据具体加密方式进行验证
        return password.startsWith("Zyf");
    }
}
