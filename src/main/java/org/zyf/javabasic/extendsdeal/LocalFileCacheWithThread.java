package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: zyfboot-javabasic
 * @description: 错误设计：LocalFileCacheWithThread继承Thread
 * @author: zhangyanfeng
 * @create: 2025-03-08 21:26
 **/
@Slf4j
@Component
public class LocalFileCacheWithThread extends Thread{
    public void saveToDisk() {
        log.info("【错误设计】正在保存缓存到磁盘...");
        try {
            Thread.sleep(500); // 模拟耗时操作
        } catch (InterruptedException e) {
            log.error("缓存保存线程被中断", e);
        }
        log.info("【错误设计】缓存保存完成");
    }

    @Override
    public void run() {
        saveToDisk();
    }
}
