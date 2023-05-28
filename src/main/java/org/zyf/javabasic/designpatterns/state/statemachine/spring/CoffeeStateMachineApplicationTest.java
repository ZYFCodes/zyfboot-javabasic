package org.zyf.javabasic.designpatterns.state.statemachine.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;


/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/26  23:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CoffeeStateMachineApplicationTest {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Test
    public void testStateMachine() {
        stateMachine.start();

        System.out.println(stateMachine.getState().getId());

        stateMachine.sendEvent(Events.INSERT_COIN);

        System.out.println(stateMachine.getState().getId());

        stateMachine.sendEvent(Events.MAKE_COFFEE);

        System.out.println(stateMachine.getState().getId());

        stateMachine.sendEvent(Events.DISPENSE_COFFEE);

        System.out.println(stateMachine.getState().getId());
    }
}
