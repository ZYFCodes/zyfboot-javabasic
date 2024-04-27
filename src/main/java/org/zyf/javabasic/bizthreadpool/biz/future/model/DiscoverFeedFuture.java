package org.zyf.javabasic.bizthreadpool.biz.future.model;

import lombok.Builder;
import lombok.Data;
import org.zyf.javabasic.bizthreadpool.enums.ResultType;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流异步结果封装
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:15
 **/

@Data
@Builder
public class DiscoverFeedFuture {
    /**
     * 预期返回值类型
     */
    private ResultType resultType;

    /**
     * future
     */
    private Future<?> future;

    /**
     * future对应的返回值
     */
    private Object result;

    /**
     * 超时时间 单位毫秒
     */
    private long timeOut;


    public void getFutureResult() throws Exception {
        if (timeOut > 0) {
            this.result = future.get(timeOut, TimeUnit.MILLISECONDS);
        } else {
            this.result = future.get();
        }
    }
}

