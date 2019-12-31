package org.zyf.javabasic.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import javax.swing.event.ChangeEvent;

/**
 * 描述：Guava事件总线------内部的订阅通知模型,无需使用事件+事件listener模型 只有一个事件类
 *http://ifeve.com/google-guava-eventbus/
 * @author yanfengzhang
 * @date 2019-12-31 09:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventBusTest {
    /*给予事件类中方法以@Subscribe注解*/
    class EventBusChangeRecorder {
        @Subscribe
        public void recordCustomerChange(ChangeEvent e) {
            //事件发生时做一些事
            System.out.println("触发事件");
        }
    }

    public void changeCustomer() {
        /*在程序的某处创建事件总线并注册事件*/
        EventBus eventBus=new EventBus();
        eventBus.register(new EventBusChangeRecorder());

        /*在之后的程序中 提交发生的事件*/
        ChangeEvent event=new ChangeEvent(new EventBusChangeRecorder());
        eventBus.post(event);
    }


    @Test
    public void testEventBus() {
        changeCustomer();
        changeCustomer();
        changeCustomer();
    }
}
