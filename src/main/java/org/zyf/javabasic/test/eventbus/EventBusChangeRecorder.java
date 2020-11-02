package org.zyf.javabasic.test.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import javax.swing.event.ChangeEvent;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/8/21  11:19
 */
public class EventBusChangeRecorder {

    @Subscribe
    public void recordCustomerChange(ChangeEvent e) {
        //事件发生时做一些事
        System.out.println("触发事件");
    }


    @AllowConcurrentEvents
    @Subscribe
    public void lister(Integer integer) {
        System.out.printf("%d from int%n", integer);
    }
}
