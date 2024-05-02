package org.zyf.javabasic.test;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: zyfboot-javabasic
 * @description: XMLParserExample
 * @author: zhangyanfeng
 * @create: 2024-05-02 21:40
 **/
public class XMLParserExample {
    public static void main(String[] args) {
        try {
            // 加载 XML 文件
            Resource resource = new ClassPathResource("report-config.xml");
            InputStream inputStream = resource.getInputStream();

            // 解析 XML 文件
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            // 获取根元素
            Element root = document.getDocumentElement();

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

                    // 输出属性值
                    System.out.println("driverClassName: " + driverClassName);
                    System.out.println("url: " + url);
                    System.out.println("username: " + username);
                    System.out.println("password: " + password);
                }
            }

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

                    // 输出 dataSource 引用
                    System.out.println("dataSourceRef: " + dataSourceRef);
                }
            }
        } catch (IOException | ParserConfigurationException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }
}
