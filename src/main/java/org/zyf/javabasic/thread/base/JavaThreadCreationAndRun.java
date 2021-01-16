package org.zyf.javabasic.thread.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/1/16  12:56
 */
public class JavaThreadCreationAndRun {

    static class ZYFThread implements Runnable {
        private final String message;

        public ZYFThread(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            doSomething(message);
        }

        private void doSomething(String message) {
            System.out.println("The doSomething method was executed by thread:" + Thread.currentThread().getName());
            System.out.println("Do doSomething with " + message);
        }
    }

    static class CustomThread extends Thread {
        @Override
        public void run() {
            System.out.println("Running......");
        }
    }

    public static void main(String[] args) {
        System.out.println("The main method was executed by thread:" + Thread.currentThread().getName());

        ZYFThread zyfThread = new ZYFThread("Java Thread Anywhere");
        CustomThread customThread = new CustomThread();

        /*创建一个线程*/
        Thread thread = new Thread(zyfThread);

        /*设置线程名*/
        thread.setName("ZYF-Worker-Thread");

        /*启动线程*/
        thread.start();
        customThread.start();
    }
}
