package org.zyf.javabasic.aop.basic;

import javassist.bytecode.ByteArray;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.common.Result;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.WordRegular;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/8  21:42
 */
@Service
public class VoidBooleanService {

    public boolean getBoolean() {
        return false;
    }

    public Boolean getBoolean2() {
        return false;
    }

    public void getVoid() {
        return;
    }

    public Integer getInt1() {
        return 3;
    }

    public int getInt2() {
        return 4;
    }

    public WordRegular getWordRegular() {
        return WordRegular.builder().build();
    }

    public Future<Result<byte[]>> getFuturebyte() {
        return new Future<Result<byte[]>>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Result<byte[]> get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Result<byte[]> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    public Future<Result<Void>> getFuture() {
        return new Future<Result<Void>>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Result<Void> get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Result<Void> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    public Future<ResultMap<ByteArray, Result<Void>>> getFutureResultMapByteArrayResultVoid() {
        return new Future<ResultMap<ByteArray, Result<Void>>>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public ResultMap<ByteArray, Result<Void>> get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public ResultMap<ByteArray, Result<Void>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    public Future<Result<Set<byte[]>>> getFutureResultSetbyte() {
        return new Future<Result<Set<byte[]>>>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Result<Set<byte[]>> get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Result<Set<byte[]>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    public ResultMap<String, Result<Void>> getResultMapStringResultVoid() {
        return new ResultMap<String, Result<Void>>();
    }

    public ResultMap<ByteArray, Result<Map<ByteArray, Result<Void>>>> getResultMapByteArrayResultMapByteArrayResultVoid() {
        return new ResultMap<ByteArray, Result<Map<ByteArray, Result<Void>>>>();
    }
}
