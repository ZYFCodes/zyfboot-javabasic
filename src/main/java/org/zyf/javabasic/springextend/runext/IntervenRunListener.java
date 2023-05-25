package org.zyf.javabasic.springextend.runext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author yanfengzhang
 * @description SpringApplicationRunListener是Spring Boot的一个事件监听器，用于在应用程序启动和停止时执行一些操作。
 * 可能需要自定义SpringApplicationRunListener来执行某些特定操作。
 * 下面是一个示例，演示如何扩展SpringApplicationRunListener以添加自定义操作
 * @date 2021/2/23  23:44
 */
public class IntervenRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    public IntervenRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        System.out.println("IntervenRunListener starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("IntervenRunListener environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("IntervenRunListener contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("IntervenRunListener contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("IntervenRunListener started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("IntervenRunListener running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("IntervenRunListener failed");
    }
}
