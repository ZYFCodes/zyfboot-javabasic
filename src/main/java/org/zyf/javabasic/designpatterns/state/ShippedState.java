package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:27
 */
public class ShippedState extends OrderState {
    public ShippedState(PDDOrder order) {
        super(order);
    }

    @Override
    public void pay() {
        System.out.println("订单已发货，不能支付");
    }

    @Override
    public void cancel() {
        System.out.println("订单已发货，不能取消");
    }

    @Override
    public void ship() {
        System.out.println("订单已发货，不能重复发货");
    }

    @Override
    public void confirm() {
        System.out.println("订单已发货，确认收货成功");
        order.setState(new CompletedState(order));
    }
}
