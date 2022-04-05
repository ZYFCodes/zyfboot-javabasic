package org.zyf.javabasic.designpatterns.strategy.combination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 实际业务订单处理服务
 * @date 2022/3/8  23:53
 */
@Service
public class OrderService {

    private Map<OrderHandlerType, OrderHandler> orderHandleMap;

    @Autowired
    public void setOrderHandleMap(List<OrderHandler> orderHandlers) {
        // 注入各种类型的订单处理类
        orderHandleMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class),
                        v -> v, (v1, v2) -> v1));
    }

    /**
     * 只做测试，不进行前置校验或其他订单逻辑，主要就直接按策略看最后的结果
     *
     * @param order 当前订单信息
     * @return 订单处理结果
     */
    public OrderConsumerResult orderService(Order order) {
        // 通过订单来源、支付方式和会员等级获取对应的handler
        OrderHandlerType orderHandlerType = new OrderHandlerTypeImpl(order.getSource(), order.getPayMethod(), order.getPayMember());
        OrderHandler orderHandler = orderHandleMap.get(orderHandlerType);
        if (Objects.isNull(orderHandler)) {
            /*没有匹配的策略直接原价处理并不参与权益处理*/
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(order.getAmount())
                    .right("该会员不参与其他权益履约适宜").build();
        }
        return orderHandler.handle(order);
    }
}
