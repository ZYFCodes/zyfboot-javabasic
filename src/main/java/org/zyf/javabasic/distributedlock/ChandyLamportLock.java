package org.zyf.javabasic.distributedlock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanfengzhang
 * @description 使用 Java 实现的简化版 Chandy-Lamport 分布式锁的示例代码
 * @date 2023/4/24  23:28
 */
public class ChandyLamportLock {
    private AtomicInteger[] requestSeq;
    private AtomicInteger[] replySeq;
    private AtomicBoolean[] deferred;
    private AtomicBoolean[] using;
    private AtomicBoolean[] accessing;
    private int numNodes;
    private int myId;
    private volatile int numReplies;

    public ChandyLamportLock(int numNodes, int myId) {
        this.numNodes = numNodes;
        this.myId = myId;
        requestSeq = new AtomicInteger[numNodes];
        replySeq = new AtomicInteger[numNodes];
        deferred = new AtomicBoolean[numNodes];
        using = new AtomicBoolean[numNodes];
        accessing = new AtomicBoolean[numNodes];
        numReplies = 0;

        for (int i = 0; i < numNodes; i++) {
            requestSeq[i] = new AtomicInteger(0);
            replySeq[i] = new AtomicInteger(0);
            deferred[i] = new AtomicBoolean(false);
            using[i] = new AtomicBoolean(false);
            accessing[i] = new AtomicBoolean(false);
        }
    }

    public void lock() {
        requestSeq[myId].set(findMax(requestSeq) + 1);
        broadcast(requestSeq[myId].get());
        deferred[myId].set(false);
        numReplies = 0;

        while (numReplies < numNodes - 1) {
            // 等待接收其他节点的回复
        }

        accessing[myId].set(true);

        while (accessing[myId].get()) {
            // 在获取锁之前，等待其他节点释放锁
        }
    }

    public void unlock() {
        accessing[myId].set(false);
        using[myId].set(false);

        for (int i = 0; i < numNodes; i++) {
            if (deferred[i].get()) {
                replySeq[i].set(requestSeq[myId].get());
                deferred[i].set(false);
            }
        }
    }

    private int findMax(AtomicInteger[] arr) {
        int max = Integer.MIN_VALUE;
        for (AtomicInteger num : arr) {
            max = Math.max(max, num.get());
        }
        return max;
    }

    private void broadcast(int value) {
        for (int i = 0; i < numNodes; i++) {
            if (i != myId) {
                replySeq[i].set(0);
                deferred[i].set(true);
            }
        }
    }
}
