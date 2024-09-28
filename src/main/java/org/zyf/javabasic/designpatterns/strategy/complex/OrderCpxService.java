package org.zyf.javabasic.designpatterns.strategy.complex;

import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.designpatterns.strategy.combination.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 实际业务订单处理服务
 * @date 2022/3/12  00:00
 */
@Service
@Log4j2
public class OrderCpxService {
    /**
     * 策略表
     */
    private static Map<StrategyInfo, Class> processorRepository = Maps.newHashMap();

    static {
        Reflections reflections = new Reflections("org.zyf.javabasic.designpatterns.strategy.complex");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(OrderProcessorType.class);
        for (Class<?> cl : classSet) {
            Annotation[] annotations = cl.getAnnotations();
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof OrderProcessorType) {
                        OrderProcessorType dispatch = (OrderProcessorType) annotation;
                        OrderSourceEnum source = dispatch.source();
                        OrderPayMethodEnum[] payMethods = dispatch.payMethod();
                        MemberTypeEnum memberType = dispatch.memberType();
                        for (OrderPayMethodEnum payMethod : payMethods) {
                            processorRepository.put(StrategyInfo.builder().source(source).payMethod(payMethod).memberType(memberType).build(), cl);
                        }
                    }
                }
            }
        }
    }

    /**
     * 只做测试，不进行前置校验或其他订单逻辑，主要就直接按策略看最后的结果
     *
     * @param order 当前订单信息
     * @return 订单处理结果
     */
    public OrderConsumerResult orderService(Order order) throws Exception {
        StrategyInfo strategyInfo = StrategyInfo.builder()
                .source(order.getSource())
                .payMethod(order.getPayMethod())
                .memberType(order.getPayMember()).build();

        try {
            Constructor constructor = processorRepository.get(strategyInfo).getConstructor();
            OrderHandler orderHandler = (OrderHandler) constructor.newInstance();
            if (Objects.isNull(orderHandler)) {
                /*没有匹配的策略直接原价处理并不参与权益处理*/
                return OrderConsumerResult.builder()
                        .code(order.getCode())
                        .amountSpent(order.getAmount())
                        .right("该会员不参与其他权益履约适宜").build();
            }
            return orderHandler.handle(order);
        } catch (Exception e) {
            log.error("OrderCpxService orderService error,order={}", order);
            throw new Exception("依旧当前订单信息处理营销信息失败！");
        }
    }
}
