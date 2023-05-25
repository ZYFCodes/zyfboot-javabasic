package org.zyf.javabasic.springextend.runext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 演示如何使用CommandLineRunner来执行一些自定义启动任务
 * @date 2021/2/23  23:31
 */
@Component
public class StartupTasks implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // 加载应用程序配置
        System.out.println("StartupTasks configService loadConfig");

        // 初始化缓存
        System.out.println("StartupTasks cacheService initialize");
    }
}
