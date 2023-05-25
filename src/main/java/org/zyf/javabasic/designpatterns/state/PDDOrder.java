package org.zyf.javabasic.designpatterns.state;

/**
 * @author yanfengzhang
 * @description 定义上下文类
 * @date 2020/5/24  23:17
 */
public class PDDOrder {
    private OrderState state;

    public PDDOrder() {
        state = new WaitingForPayState(this);
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void pay() {
        state.pay();
    }

    public void cancel() {
        state.cancel();
    }

    public void ship() {
        state.ship();
    }

    public void confirm() {
        state.confirm();
    }
}
