package org.zyf.javabasic.test.eventbus;

import com.google.common.eventbus.*;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/8/21  11:32
 */
public class EventBusTest {

    @Data
    private static class EventA {
        private String name;

        @Override
        public String toString() {
            return "A 类型事件";
        }
    }

    private static class EventB extends EventA {
        @Override
        public String toString() {
            return "B 类型事件";
        }
    }

    private static class EventC {
        @Override
        public String toString() {
            return "C 类型事件";
        }
    }

    private static class EventD {
        @Override
        public String toString() {
            return "D 类型事件";
        }
    }

    private static class EventX {
        @Override
        public String toString() {
            return "X 类型事件";
        }
    }

    private static class EventListener {
        @Subscribe
        public void onEvent(EventA e) {
            System.out.println(Thread.currentThread().getName() + "我订阅的是 A事件,接收到:" + e + ",名称为：" + e.getName());
        }

        @Subscribe
        public void onEvent(EventB e) {
            System.out.println("我订阅的是B事件,接收到:" + e);
        }

        @Subscribe
        @AllowConcurrentEvents
        public void onEvent(EventC e) throws InterruptedException {
            String name = Thread.currentThread().getName();
            System.out.format("%s sleep 一会儿%n", name);
            Thread.sleep(1000);
            System.out.println(name + "订阅的是C事件,接收到:" + e);
            System.out.format("%s sleep 完成%n", name);
        }

        // @Subscribe
        //没有该注解表示未订阅
        public void onEvent(EventD e) throws InterruptedException {
            String name = Thread.currentThread().getName();
            System.out.format("%s sleep 一会儿%n", name);
            Thread.sleep(1000);
            System.out.println(name + "订阅的是D事件,接收到:" + e);
            System.out.format("%s sleep 完成%n", name);
        }

        @Subscribe
        public void onEvent(DeadEvent de) {
            System.out.println(Thread.currentThread().getName() + "发布了错误的事件:" + de.getEvent());
        }
    }

    @Test
    public void singleThreadTest() {
        EventBus eb = new EventBus();
        eb.register(new EventListener());
        eb.post(new EventA());
        eb.post(new EventX());

        EventA a=new EventA();
        a.setName("测试一下！");
        eb.post(a);

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        EventBus eb = new AsyncEventBus(threadPool);
        eb.register(new EventListener());
        eb.post(new EventC());
        eb.post(new EventA());

        //未订阅事件
        eb.post(new EventD());
        threadPool.shutdown();
    }
}
