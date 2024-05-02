package org.zyf.javabasic.spring.failureanalyzer.checker;

import com.google.common.collect.Lists;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.spring.failureanalyzer.exception.ConfigFileNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 系统必要配置文件检查
 * @author: zhangyanfeng
 * @create: 2024-05-02 18:14
 **/
@Component
public class ConfigFileNotFoundChecker {

    private final ResourceLoader resourceLoader;

    public ConfigFileNotFoundChecker(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public boolean exists(String fileName) {
        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        return resource.exists();
    }

    @PostConstruct
    public void checkConfigFiles() throws ConfigFileNotFoundException {
        // 要检查的文件列表
        List<String> filesToCheck = Lists.newArrayList();
        filesToCheck.add("application.yml");
        filesToCheck.add("zyf_application_context.xml");
        filesToCheck.add("report-config.xml");
        filesToCheck.add("urlzyf.properties");

        // 存储不存在的文件名
        List<String> notFoundFiles = Lists.newArrayList();

        // 检查每个文件是否存在
        for (String fileName : filesToCheck) {
            if (!exists(fileName)) {
                notFoundFiles.add(fileName);
            }
        }

        // 如果存在未找到的文件，则抛出异常
        if (!notFoundFiles.isEmpty()) {
            throw new ConfigFileNotFoundException(notFoundFiles.toString());
        }
    }
}
