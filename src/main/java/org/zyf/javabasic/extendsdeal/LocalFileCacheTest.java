package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * @program: zyfboot-javabasic
 * @description: 测试：同时验证错误设计和正确设计
 * @author: zhangyanfeng
 * @create: 2025-03-08 21:34
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocalFileCacheTest {
    @Autowired
    private LocalFileCacheWithThread incorrectCache; // 继承Thread的错误设计

    @Autowired
    private LocalFileCache correctCache; // 使用线程池的正确设计

    @Before
    public void setup() {
        log.info("测试开始...");
    }

    /**
     * 测试错误设计：继承 Thread，每次调用 start() 都会创建新的线程
     */
    @Test
    public void testIncorrectDesign() {
        log.info("【错误设计测试】开始执行...");
        incorrectCache.start();

        try {
            incorrectCache.start(); // 期望第二次 start() 抛出 IllegalThreadStateException
            fail("错误设计多次 start() 但未抛出异常");
        } catch (IllegalThreadStateException e) {
            log.info("成功捕获预期异常：{}", e.getClass().getSimpleName());
        }
    }


    /**
     * 测试正确设计：使用线程池执行任务
     */
    @Test
    public void testCorrectDesign() {
        log.info("【正确设计测试】开始执行...");

        try {
            correctCache.scheduleTask();
        } catch (Exception e) {
            log.error("正确设计触发异常", e);
            fail("正确设计触发异常：" + e.getMessage());
        }

        // 使用 CountDownLatch 来验证是否在 2 秒内完成
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 等待任务执行
                latch.countDown();  // 任务执行完成
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        try {
            boolean completed = latch.await(2, TimeUnit.SECONDS);
            assertTrue("任务在 2 秒内未完成", completed);
        } catch (InterruptedException e) {
            fail("测试被中断：" + e.getMessage());
        }

        log.info("【正确设计测试】执行成功");
    }

}
