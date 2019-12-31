package org.zyf.javabasic.guava.multithread;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * 描述：并发多线程编程
 *
 * @author yanfengzhang
 * @date 2019-12-30 21:06
 */
public class FuturesTest {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture explosion = service.submit(new Callable() {
            public String call() {
                return "this guava callable";
            }
        });
        Futures.addCallback(explosion, new FutureCallback() {
            //调用成功后执行的方法
            @Override
            public void onSuccess(Object o) {

            }

            ////调用失败后执行的方法
            public void onFailure(Throwable thrown) {
            }
        });
    }

}
