package org.zyf.javabasic.designpatterns.template.retry;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/2022/3/15  00:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RetryTemplateTest {

    @Autowired
    private RetryBizService retryBizService;

    @Test
    public void testRetryBizService() {
        String aphorisms = null;
        try {
            aphorisms = (String) new RetryTemplate() {
                @Override
                protected Object doBiz() throws Exception {
                    return retryBizService.getAphorisms();
                }
            }.setRetryTime(3).setSleepMills(200).execute();
        } catch (Exception e) {
            log.warn("[RetryBizService名言警句展示] 系统系统进行重试调用失败！");
        }
        if (StringUtils.isEmpty(aphorisms)) {
            log.error("[RetryBizService名言警句展示异常，请稍后重试]");
            return;
        }
        log.info("测试成功，得到的名言警句为：{}", aphorisms);
    }

    @Test
    public void testGuavaRetrying() {
        Callable<String> callable = () -> {
            /*业务逻辑*/
            return retryBizService.getAphorisms();
        };

        /*定义重试器*/
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                /*如果结果为空则重试*/
                .retryIfResult(Objects::isNull)
                /*发生IO异常则重试*/
                .retryIfExceptionOfType(Exception.class)
                /*发生运行时异常则重试*/
                .retryIfRuntimeException()
                /*等待*/
                .withWaitStrategy(WaitStrategies.incrementingWait(100, TimeUnit.MILLISECONDS,
                        100, TimeUnit.MILLISECONDS))
                /*允许执行4次（首次执行 + 最多重试3次）*/
                .withStopStrategy(StopStrategies.stopAfterAttempt(4))
                .build();

        try {
            /*执行*/
            String aphorisms = retryer.call(callable);
            if (StringUtils.isEmpty(aphorisms)) {
                log.error("[RetryBizService名言警句展示异常，请稍后重试]");
                return;
            }
            log.info("测试成功，得到的名言警句为：{}", aphorisms);
        } catch (RetryException | ExecutionException e) {
            /*重试次数超过阈值或被强制中断*/
            log.warn("[RetryBizService名言警句展示] 系统系统进行重试调用失败！");
        }
    }
}
