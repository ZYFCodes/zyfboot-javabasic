package org.zyf.javabasic.thread.toolstest.cyclicbarrier;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/15  17:03
 */
public class ReconciliationByCyclicBarrier {
    /**
     * 存在未对账订单
     */
    List<Object> needReconciliation = Lists.newArrayList();
    /**
     * 订单队列
     */
    Vector<ArrayList<Object>> pos;
    /**
     * 派送单队列
     */
    Vector<ArrayList<Object>> dos;
    /**
     * 执行回调的线程池
     */
    Executor executor = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier =
            new CyclicBarrier(2, () -> {
                executor.execute(() -> check());
            });

    void check() {
        Object p = pos.remove(0);
        Object d = dos.remove(0);
        // 执行对账操作
        Object diff = check(p, d);
        // 差异写入差异库
        save(diff);
    }

    void checkAll() throws Exception {
        // 循环查询订单库
        Thread T1 = new Thread(() -> {
            while (!CollectionUtils.isEmpty(needReconciliation)) {
                // 查询订单库
                pos.add(getPOrders());
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T1.start();
        // 循环查询运单库
        Thread T2 = new Thread(() -> {
            while (!CollectionUtils.isEmpty(needReconciliation)) {
                // 查询运单库
                dos.add(getDOrders());
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();
    }

    public static ArrayList<Object> getPOrders() {
        return Lists.newArrayList();
    }

    public static ArrayList<Object> getDOrders() {
        return Lists.newArrayList();
    }

    public static ArrayList<Object> check(Object pos, Object dos) {
        return Lists.newArrayList();
    }

    public static void save(Object diff) {
        return;
    }
}
