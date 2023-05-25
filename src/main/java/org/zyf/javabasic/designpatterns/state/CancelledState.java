package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:29
 */
public class CancelledState extends OrderState {
    public CancelledState(PDDOrder order) {
        super(order);
    }

    @Override
    public void pay() {
        System.out.println("订单已取消，不能支付");
    }

    @Override
    public void cancel() {
        System.out.println("订单已取消，不能重复取消");
    }

    @Override
    public void ship() {
        System.out.println("订单已取消，不能发货");
    }

    @Override
    public void confirm() {
        System.out.println("订单已取消，不能确认收货");
    }
}
