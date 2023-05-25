package org.zyf.javabasic.designpatterns.state.statemachine;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:22
 */
public class OrderStatusMachineForFZTest {
    public static void main(String[] args) {
        OrderStatusMachineForFZ order = new OrderStatusMachineForFZ();

        // 初始状态为WAITING_FOR_PAY
        order.ship(); // 输出："订单未支付，不能发货"
        order.confirm(); // 输出："订单未支付，不能确认收货"
        order.cancel(); // 输出："订单已取消"

        order.pay(); // 输出："订单已支付"
        order.pay(); // 输出："订单已支付，无需重复支付"
        order.cancel(); // 输出："订单已取消"

        order.pay(); // 输出："订单已支付"
        order.ship(); // 输出："订单已发货"
        order.confirm(); // 输出："订单已支付，确认收货成功"
        order.confirm(); // 输出："订单已完成，不能重复确认收货"
        order.ship(); // 输出："订单已完成，不能发货"
        order.cancel(); // 输出："订单已完成，不能取消"
    }
}
