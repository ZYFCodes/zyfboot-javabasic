package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description 等待支付状态处理
 * @date 2020/5/24  23:19
 */
public class WaitingForPayState extends OrderState{
    public WaitingForPayState(PDDOrder order) {
        super(order);
    }

    @Override
    public void pay() {
        System.out.println("订单已支付");
        order.setState(new PaidState(order));
    }

    @Override
    public void cancel() {
        System.out.println("订单已取消");
        order.setState(new CancelledState(order));
    }

    @Override
    public void ship() {
        System.out.println("订单未支付，不能发货");
    }

    @Override
    public void confirm() {
        System.out.println("订单未支付，不能确认收货");
    }
}
