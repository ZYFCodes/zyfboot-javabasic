//package org.zyf.javabasic.aop.mock;
//
//import com.taobao.tair3.client.Result;
//import com.taobao.tair3.client.ResultMap;
//import com.taobao.tair3.client.util.ByteArray;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.zyf.javabasic.config.SwitchCommonConfig;
//
//import java.lang.reflect.Method;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
///**
// * @author yanfengzhang
// * @description 商品缓存持久化存储服务
// * ps针对B端处理：waimai_kv_group_product_store
// * ps针对C端处理：waimai_kv_group_product_cache
// * @date 2022/4/8  19:59
// */
//@Component
//@Aspect
//@Slf4j
//public class ProductStoreAspect {
//
//    /**
//     * 切入点1：ps针对B端和C端缓存处理
//     */
//    @Pointcut("execution(* com.taobao.tair3..*.*(..))")
//    public void productStore() {
//    }
//
//    @Around("productStore()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        /*获取其基本的Signature信息*/
//        Signature signature = point.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method targetMethod = methodSignature.getMethod();
//        /*返回的具体类*/
//        String resultTypeName = targetMethod.getAnnotatedReturnType().getType().getTypeName();
//
//        /*将当前基本信息进行组装处理*/
//        String className = methodSignature.getDeclaringTypeName();
//        String methodName = targetMethod.getName();
//        String mockStoreInfo = className + ":" + methodName;
//
//        /*缓存结果为Future<Result<Void>>的内容*/
//        if (SwitchCommonConfig.getMockProductStore().contains(mockStoreInfo)) {
//            if (resultTypeName.equals("void")) {
//                return true;
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<java.lang.Void>>")) {
//                return new Future<Result<Void>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<Void> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<Void> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<byte[]>>")) {
//                return new Future<Result<byte[]>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<byte[]> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<byte[]> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.ResultMap<com.taobao.tair3.client.util.ByteArray, com.taobao.tair3.client.Result<java.lang.Void>>>")) {
//                return new Future<ResultMap<ByteArray, Result<Void>>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Void>> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Void>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<java.lang.Integer>>")) {
//                return new Future<Result<Integer>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<Integer> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<Integer> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.ResultMap<com.taobao.tair3.client.util.ByteArray, com.taobao.tair3.client.Result<java.lang.Integer>>>")) {
//                return new Future<ResultMap<ByteArray, Result<Integer>>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Integer>> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Integer>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.ResultMap<com.taobao.tair3.client.util.ByteArray, com.taobao.tair3.client.Result<java.lang.Long>>>")) {
//                return new Future<ResultMap<ByteArray, Result<Long>>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Long>> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Long>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.ResultMap<com.taobao.tair3.client.util.ByteArray, com.taobao.tair3.client.Result<java.lang.Double>>>")) {
//                return new Future<ResultMap<ByteArray, Result<Double>>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Double>> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public ResultMap<ByteArray, Result<Double>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<java.lang.Boolean>>")) {
//                return new Future<Result<Boolean>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<Boolean> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<Boolean> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<java.util.Set<byte[]>>")) {
//                return new Future<Result<Set<byte[]>>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<Set<byte[]>> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<Set<byte[]>> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<java.lang.Double>>")) {
//                return new Future<Result<Double>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<Double> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<Double> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.Result<java.lang.Long>>")) {
//                return new Future<Result<Long>>() {
//                    @Override
//                    public boolean cancel(boolean mayInterruptIfRunning) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isCancelled() {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean isDone() {
//                        return false;
//                    }
//
//                    @Override
//                    public Result<Long> get() throws InterruptedException, ExecutionException {
//                        return null;
//                    }
//
//                    @Override
//                    public Result<Long> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                        return null;
//                    }
//                };
//            }
//            if (resultTypeName.equals("java.util.concurrent.Future<com.taobao.tair3.client.ResultMap<java.lang.String, com.taobao.tair3.client.Result<java.lang.Void>>>")) {
//                return new ResultMap<String, Result<Void>>();
//            }
//
//            if (resultTypeName.equals("com.taobao.tair3.client.ResultMap<com.taobao.tair3.client.util.ByteArray, com.taobao.tair3.client.Result<java.util.Map<om.taobao.tair3.client.util.ByteArray, om.taobao.tair3.client.Result<java.lang.Void>>>>")) {
//                return new ResultMap<ByteArray, Result<Map<ByteArray, Result<Void>>>>();
//            }
//            return point.proceed();
//        }
//
//        /*其他方式下按原方式返回你即可*/
//        return point.proceed();
//    }
//}
//
