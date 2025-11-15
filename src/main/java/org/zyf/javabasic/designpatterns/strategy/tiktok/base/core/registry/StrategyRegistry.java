package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.registry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.Strategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.annotation.StrategyFor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 策略注册器（核心组件）
 *
 * 【职责说明】
 * 该组件在系统启动阶段自动扫描 Spring 容器中所有实现了 Strategy 接口的 Bean，
 * 读取它们的 @StrategyFor 注解配置，并将“策略实例 + 匹配规则”注册到策略列表中。
 * 后续由策略分派器（StrategyDispatcher）进行策略匹配与执行。
 *
 * 【设计目标】
 * - 自动注册：免手工配置
 * - 解耦扩展：策略定义与业务分离
 * - 统一管理：集中维护策略元信息
 *
 * @author:
 * @create: 2025-11-02 18:37
 **/
@Component
public class StrategyRegistry implements ApplicationContextAware {

    /** Spring 上下文，用于获取所有 Strategy Bean */
    private ApplicationContext applicationContext;

    /** 策略注册表，存放系统内所有策略信息 */
    private final List<RegisteredStrategy> strategies = new ArrayList<>();

    /**
     * 内部静态类，用于封装单个策略的注册信息：
     * 包含策略对应的注解（匹配规则）与策略实例（执行对象）
     */
    public static class RegisteredStrategy {
        private final StrategyFor annotation;
        private final Strategy instance;

        public RegisteredStrategy(StrategyFor annotation, Strategy instance) {
            this.annotation = annotation;
            this.instance = instance;
        }

        public StrategyFor getAnnotation() {
            return annotation;
        }

        public Strategy getInstance() {
            return instance;
        }
    }

    /**
     * 容器启动后自动执行
     *
     * 1. 从 Spring 容器中获取所有实现 Strategy 接口的 Bean；
     * 2. 判断是否标注了 @StrategyFor；
     * 3. 如果是，则解析注解并注册到策略列表；
     * 4. 最终打印所有已注册的策略信息。
     */
    @PostConstruct
    public void init() {
        // 获取容器中所有 Strategy 类型的 Bean
        Map<String, Strategy> beans = applicationContext.getBeansOfType(Strategy.class);

        // 遍历所有策略 Bean，解析注解并注册
        beans.values().forEach(strategy -> {
            Class<?> clazz = strategy.getClass();
            if (clazz.isAnnotationPresent(StrategyFor.class)) {
                StrategyFor annotation = clazz.getAnnotation(StrategyFor.class);
                strategies.add(new RegisteredStrategy(annotation, strategy));
                System.out.println("[StrategyRegistry] 注册策略: " + clazz.getSimpleName());
            }
        });

        // 打印统计结果
        System.out.println("[StrategyRegistry] 共注册策略数量: " + strategies.size());
    }

    /**
     * 对外暴露所有注册策略
     * 供分派器（StrategyDispatcher）使用
     */
    public List<RegisteredStrategy> getStrategies() {
        return strategies;
    }

    /**
     * 由 Spring 注入 ApplicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
