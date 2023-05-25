package org.zyf.javabasic.designpatterns.state.statemachine;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/24  23:58
 */
public class CoffeeVendingMachine {
    private int[][] transitionTable = {
            // IDLE
            {State.MAKING_COFFEE, State.IDLE},
            // MAKING_COFFEE
            {State.DISPENSING_COFFEE, State.MAKING_COFFEE},
            // DISPENSING_COFFEE
            {State.IDLE, State.IDLE}
    };

    private int state;

    public CoffeeVendingMachine() {
        this.state = State.IDLE;
    }

    public void insertCoin() {
        if (state == State.IDLE) {
            state = transitionTable[state][Event.INSERT_COIN];
            System.out.println("请按下咖啡按钮");
        } else {
            System.out.println("当前状态无法插入硬币");
        }
    }

    public void makeCoffee() {
        if (state == State.MAKING_COFFEE) {
            state = transitionTable[state][Event.MAKE_COFFEE];
            System.out.println("正在制作咖啡，请稍等");
        } else {
            System.out.println("当前状态无法制作咖啡");
        }
    }

    public void dispenseCoffee() {
        if (state == State.DISPENSING_COFFEE) {
            state = transitionTable[state][Event.DISPENSE_COFFEE];
            System.out.println("请取走您的咖啡");
        } else {
            System.out.println("当前状态无法取走咖啡");
        }
    }

    private static class State {
        private static final int IDLE = 0;
        private static final int MAKING_COFFEE = 1;
        private static final int DISPENSING_COFFEE = 2;
    }

    private static class Event {
        private static final int INSERT_COIN = 0;
        private static final int MAKE_COFFEE = 1;
        private static final int DISPENSE_COFFEE = 2;
    }
}
