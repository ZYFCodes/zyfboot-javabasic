package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:25
 */
public class PaidState extends OrderState {
    public PaidState(PDDOrder order) {
        super(order);
    }

    @Override
    public void pay() {
        System.out.println("订单已支付，无需重复支付");
    }

    @Override
    public void cancel() {
        System.out.println("订单已取消");
        order.setState(new CancelledState(order));
    }

    @Override
    public void ship() {
        System.out.println("订单已发货");
        order.setState(new ShippedState(order));
    }

    @Override
    public void confirm() {
        System.out.println("订单已支付，确认收货成功");
        order.setState(new CompletedState(order));
    }
}
