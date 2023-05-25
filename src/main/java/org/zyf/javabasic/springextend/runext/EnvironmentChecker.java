package org.zyf.javabasic.springextend.runext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 演示如何使用ApplicationRunner在应用程序启动时执行环境检查
 * @date 2021/2/23  23:17
 */
@Component
public class EnvironmentChecker implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 检查数据库是否可用
        System.out.println("EnvironmentChecker DatabaseConnection checkConnection! ");

        // 检查文件系统是否可写
        System.out.println("EnvironmentChecker FileStorage checkWriteAccess! ");
    }
}
