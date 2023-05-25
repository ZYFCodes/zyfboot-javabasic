package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description 定义一个抽象状态类OrderState，它包含了订单状态的基本行为和状态转移方法
 * @date 2020/5/24  23:14
 */
public abstract class OrderState {
    protected PDDOrder order;

    public OrderState(PDDOrder order) {
        this.order = order;
    }

    public abstract void pay();

    public abstract void cancel();

    public abstract void ship();

    public abstract void confirm();
}
