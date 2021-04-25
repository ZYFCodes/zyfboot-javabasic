package org.zyf.javabasic.thread.toolstest.countdownlatch;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanfengzhang
 * @description 利用并行优化对账系统
 * @date 2021/4/15  22:17
 */
public class ReconciliationByCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        /*创建2个线程的线程池*/
        Executor executor = Executors.newFixedThreadPool(2);
        /*存在未对账订单*/
        List<Object> needReconciliation = Lists.newArrayList();
        /*对账差异单*/
        ArrayList<Object> diff = Lists.newArrayList();
        /*订单集合*/
        AtomicReference<ArrayList<Object>> pos = new AtomicReference<>(Lists.newArrayList());
        /*派送单集合*/
        AtomicReference<ArrayList<Object>> dos = new AtomicReference<>(Lists.newArrayList());

        while (!CollectionUtils.isEmpty(needReconciliation)) {
            /*计数器初始化为2*/
            CountDownLatch latch = new CountDownLatch(2);
            /*查询未对账订单*/
            executor.execute(() -> {
                pos.set(getPOrders());
                latch.countDown();
            });
            /*查询派送单*/
            executor.execute(() -> {
                dos.set(getDOrders());
                latch.countDown();
            });

            /*等待两个查询操作结束*/
            latch.await();

            /*执行对账操作*/
            diff = check(pos, dos);
            /*差异写入差异库*/
            save(diff);
        }
    }

    public static ArrayList<Object> getPOrders() {
        return Lists.newArrayList();
    }

    public static ArrayList<Object> getDOrders() {
        return Lists.newArrayList();
    }

    public static ArrayList<Object> check(AtomicReference<ArrayList<Object>> pos, AtomicReference<ArrayList<Object>> dos) {
        return Lists.newArrayList();
    }

    public static void save(ArrayList<Object> diff) {
        return;
    }
}
