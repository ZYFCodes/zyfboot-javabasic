package org.zyf.javabasic.spring.beanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @program: zyfboot-javabasic
 * @description: 简单的命令行工具
 * @author: zhangyanfeng
 * @create: 2024-04-13 13:55
 **/
public class ReportToolTest {
    public static void main(String[] args) {
        // 创建 BeanFactory
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("report-config.xml"));

        // 获取 reportGenerator Bean
        ReportGenerator reportGenerator = (ReportGenerator) beanFactory.getBean("reportGenerator");

        // 使用 reportGenerator 生成报告
        reportGenerator.generateReport();
    }
}
