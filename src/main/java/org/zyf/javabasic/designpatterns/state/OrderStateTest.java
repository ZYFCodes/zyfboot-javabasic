package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:35
 */
public class OrderStateTest {

    public static void main(String[] args) {
        PDDOrder order = new PDDOrder();

        // 初始状态为WaitingForPayState
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
