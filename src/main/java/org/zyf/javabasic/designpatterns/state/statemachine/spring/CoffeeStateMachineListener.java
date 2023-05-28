package org.zyf.javabasic.designpatterns.state.statemachine.spring;

import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 * @author yanfengzhang
 * @description 定义状态机的动作
 *              定义了一个状态机监听器，它可以在状态机状态发生变化或者事件不被接受时输出一些信息
 * @date 2020/5/26  23:17
 */
public class CoffeeStateMachineListener extends StateMachineListenerAdapter<States, Events> {
    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        System.out.println("状态从 " + from + " 变为 " + to);
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
        System.out.println("事件 " + event.getPayload() + " 不被接受");
    }
}
