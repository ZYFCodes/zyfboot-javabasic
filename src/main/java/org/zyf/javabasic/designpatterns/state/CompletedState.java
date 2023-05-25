package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:28
 */
public class CompletedState extends OrderState{
    public CompletedState(PDDOrder order) {
        super(order);
    }

    @Override
    public void pay() {
        System.out.println("订单已完成，不能支付");
    }

    @Override
    public void cancel() {
        System.out.println("订单已完成，不能取消");
    }

    @Override
    public void ship() {
        System.out.println("订单已完成，不能发货");
    }

    @Override
    public void confirm() {
        System.out.println("订单已完成，不能重复确认收货");
    }
}
