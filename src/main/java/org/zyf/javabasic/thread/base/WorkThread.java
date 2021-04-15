package org.zyf.javabasic.thread.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/1/16  13:52
 */
public class WorkThread {
    static class ZYFWorkThread {
        private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);

        /*用于处理队列workQueue中的任务的工作线程*/
        private final Thread workerThread = new Thread() {
            @Override
            public void run() {
                String task = null;
                while (true) {
                    try {
                        task = workQueue.take();
                    } catch (InterruptedException e) {
                        break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };

        public void init() {
            workerThread.start();
        }

        protected String doProcess(String task) {
            return task + "->processed.";
        }

        public void submit(String task) {
            try {
                workQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        ZYFWorkThread zyfWorkThread = new ZYFWorkThread();
        zyfWorkThread.init();

        /*ZYFWorkThread的客户端线程为main线程*/
        zyfWorkThread.submit("Somthing >>>>");
    }
}
