package org.zyf.javabasic.designpatterns.state.statemachine.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author yanfengzhang
 * @description 状态机配置类
 * @date 2020/5/26  23:15
 */
@Configuration
@EnableStateMachine
public class CoffeeStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                .initial(States.IDLE)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.IDLE).target(States.MAKING_COFFEE)
                .event(Events.INSERT_COIN)
                .and()
                .withExternal()
                .source(States.MAKING_COFFEE).target(States.DISPENSING_COFFEE)
                .event(Events.MAKE_COFFEE)
                .and()
                .withExternal()
                .source(States.DISPENSING_COFFEE).target(States.IDLE)
                .event(Events.DISPENSE_COFFEE);
    }
}
