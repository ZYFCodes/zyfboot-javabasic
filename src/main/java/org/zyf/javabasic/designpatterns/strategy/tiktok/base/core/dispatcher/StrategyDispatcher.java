package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.dispatcher;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.registry.StrategyRegistry;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.Strategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.annotation.StrategyFor;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 策略分派器（Strategy Dispatcher）
 *
 * 【职责说明】
 * 该组件是整个策略系统的“大脑”，负责根据输入的组合（A/B/C/D）信息，
 * 从注册中心中查找最匹配的策略实例，并将其分派执行。
 *
 * 【核心流程】
 * 1️⃣ 从注册中心获取所有已注册策略；
 * 2️⃣ 根据 @StrategyFor 注解规则过滤出匹配的策略；
 * 3️⃣ 若存在多个匹配，按“具体度”（specificity）降序排序；
 * 4️⃣ 返回最具体的策略；若无匹配，则回退到默认策略。
 *
 * 【特点】
 * - 注解驱动、动态匹配
 * - 自动优先选择“最具体”策略（即匹配条件最多者）
 * - 可检测策略冲突（防止多个策略规则重复）
 * - 支持默认策略兜底
 *
 * @author:
 * @create: 2025-11-02 18:39
 **/
@Component
public class StrategyDispatcher {

    /** 策略注册中心，由 Spring 注入 */
    private final StrategyRegistry registry;

    public StrategyDispatcher(StrategyRegistry registry) {
        this.registry = registry;
    }

    /**
     * 分派策略：根据组合（Combination）匹配最合适的策略实现
     */
    public Strategy dispatch(Combination combination) {
        // 1️⃣ 获取所有匹配当前组合的策略
        List<StrategyRegistry.RegisteredStrategy> matched = registry.getStrategies().stream()
                .filter(rs -> matches(rs.getAnnotation(), combination))
                .collect(Collectors.toList());

        System.out.println("[StrategyDispatcher] 组合 " + combination + " 匹配策略数量: " + matched.size());

        // 2️⃣ 无匹配策略则尝试默认策略
        if (matched.isEmpty()) {
            return findDefaultStrategy().orElseThrow(
                    () -> new RuntimeException("No matching strategy and no default strategy found for: " + combination)
            );
        }

        // 打印匹配详情
        matched.forEach(s -> System.out.println(" -> 匹配到: " + s.getInstance().getClass().getSimpleName()
                + " specificity=" + specificity(s.getAnnotation())));

        // 3️⃣ 检查是否存在多个“等价”策略（即规则冲突）
        if (matched.size() > 1) {
            matched.sort(Comparator.comparingInt(
                    (StrategyRegistry.RegisteredStrategy rs) -> specificity(rs.getAnnotation())).reversed()
            );

            int top = specificity(matched.get(0).getAnnotation());
            int next = specificity(matched.get(1).getAnnotation());
            if (top == next) {
                throw new IllegalStateException("存在策略冲突！匹配到多个等价策略: "
                        + matched.stream()
                        .map(s -> s.getInstance().getClass().getSimpleName())
                        .collect(Collectors.joining(", ")));
            }
        }

        // 4️⃣ 返回“最具体”的策略（规则约束最多者优先）
        return matched.get(0).getInstance();
    }

    /**
     * 判断注解定义的规则是否与当前组合匹配
     * - 若注解未指定该维度（数组为空），视为“通配”
     * - 若注解指定了，则必须完全匹配
     */
    private boolean matches(StrategyFor anno, Combination c) {
        if (anno == null || c == null) return false;
        if (anno.a().length > 0 && Arrays.stream(anno.a()).noneMatch(x -> x == c.getA())) return false;
        if (anno.b().length > 0 && Arrays.stream(anno.b()).noneMatch(x -> x == c.getB())) return false;
        if (anno.c().length > 0 && Arrays.stream(anno.c()).noneMatch(x -> x == c.getC())) return false;
        if (anno.d().length > 0 && Arrays.stream(anno.d()).noneMatch(x -> x == c.getD())) return false;
        return true;
    }

    /**
     * 计算策略“具体度”：
     * 指定枚举值越多，代表策略越具体（优先级越高）
     */
    private int specificity(StrategyFor anno) {
        if (anno == null) return 0;
        return (anno.a() == null ? 0 : anno.a().length)
                + (anno.b() == null ? 0 : anno.b().length)
                + (anno.c() == null ? 0 : anno.c().length)
                + (anno.d() == null ? 0 : anno.d().length);
    }

    /**
     * 从注册中心中查找默认策略（DefaultStrategy）
     * 实际项目中可改为通过注解或配置文件标识默认策略
     */
    private Optional<Strategy> findDefaultStrategy() {
        return registry.getStrategies().stream()
                .map(StrategyRegistry.RegisteredStrategy::getInstance)
                .filter(s -> s.getClass().getSimpleName().equals("DefaultStrategy"))
                .findFirst();
    }
}
