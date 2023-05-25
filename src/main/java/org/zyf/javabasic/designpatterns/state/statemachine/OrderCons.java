package org.zyf.javabasic.designpatterns.state.statemachine;

/**
 * @author yanfengzhang
 * @description 订单相关常量处理
 * @date 2020/5/24  23:46
 */
public class OrderCons {
    public static final class State {
        public static final int WAITING_FOR_PAY = 1;
        public static final int PAID = 2;
        public static final int SHIPPED = 3;
        public static final int CONFIRMED = 4;
        public static final int CANCELED = 5;
    }

    public static final class Event {
        public static final int PAY = 1;
        public static final int SHIP = 2;
        public static final int CONFIRM = 3;
        public static final int CANCEL = 4;
    }
}
