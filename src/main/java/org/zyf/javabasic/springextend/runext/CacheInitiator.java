package org.zyf.javabasic.springextend.runext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 演示如何使用ApplicationRunner在应用程序启动时加载缓存
 * @date 2021/2/23  23:13
 */
@Component
public class CacheInitiator implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("CacheInitiator cache deal!");
    }
}
