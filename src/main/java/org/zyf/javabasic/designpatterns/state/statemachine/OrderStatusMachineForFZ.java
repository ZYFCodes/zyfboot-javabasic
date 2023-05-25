package org.zyf.javabasic.designpatterns.state.statemachine;

/**
 * @author yanfengzhang
 * @description 使用分支逻辑法实现的电商订单状态机代码
 * @date 2020/5/24  23:19
 */
public class OrderStatusMachineForFZ {
    private int state;

    public OrderStatusMachineForFZ() {
        this.state = OrderCons.State.WAITING_FOR_PAY;
    }

    public void pay() {
        if (state == OrderCons.State.WAITING_FOR_PAY) {
            System.out.println("订单已支付");
            state = OrderCons.State.PAID;
        } else if (state == OrderCons.State.PAID) {
            System.out.println("订单已支付，无需重复支付");
        } else if (state == OrderCons.State.SHIPPED) {
            System.out.println("订单已发货，不能支付");
        } else if (state == OrderCons.State.CONFIRMED) {
            System.out.println("订单已完成，不能支付");
        } else if (state == OrderCons.State.CANCELED) {
            System.out.println("订单已取消，不能支付");
        }
    }

    public void ship() {
        if (state == OrderCons.State.WAITING_FOR_PAY) {
            System.out.println("订单未支付，不能发货");
        } else if (state == OrderCons.State.PAID) {
            System.out.println("订单已发货");
            state = OrderCons.State.SHIPPED;
        } else if (state == OrderCons.State.SHIPPED) {
            System.out.println("订单已发货，不能重复发货");
        } else if (state == OrderCons.State.CONFIRMED) {
            System.out.println("订单已完成，不能发货");
        } else if (state == OrderCons.State.CANCELED) {
            System.out.println("订单已取消，不能发货");
        }
    }

    public void confirm() {
        if (state == OrderCons.State.WAITING_FOR_PAY) {
            System.out.println("订单未支付，不能确认收货");
        } else if (state == OrderCons.State.PAID) {
            System.out.println("订单已支付，不能确认收货");
        } else if (state == OrderCons.State.SHIPPED) {
            System.out.println("订单已支付，确认收货成功");
            state = OrderCons.State.CONFIRMED;
        } else if (state == OrderCons.State.CONFIRMED) {
            System.out.println("订单已完成，不能重复确认收货");
        } else if (state == OrderCons.State.CANCELED) {
            System.out.println("订单已取消，不能确认收货");
        }
    }

    public void cancel() {
        if (state == OrderCons.State.WAITING_FOR_PAY) {
            System.out.println("订单已取消");
            state = OrderCons.State.CANCELED;
        } else if (state == OrderCons.State.PAID) {
            System.out.println("订单已支付，不能取消");
        } else if (state == OrderCons.State.SHIPPED) {
            System.out.println("订单已发货，不能取消");
        } else if (state == OrderCons.State.CONFIRMED) {
            System.out.println("订单已完成，不能取消");
        } else if (state == OrderCons.State.CANCELED) {
            System.out.println("订单已取消，不能重复取消");
        }
    }
}
