package org.zyf.javabasic.viator;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义函数类
 * @author: zhangyanfeng
 * @create: 2023-10-21 15:03
 **/
public class MultiplyFunction implements AviatorFunction {
    @Override
    public String getName() {
        return "multiply";
    }

    @Override
    public AviatorObject call(Map<String, Object> map) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(left.doubleValue() * right.doubleValue());
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14, AviatorObject aviatorObject15) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14, AviatorObject aviatorObject15, AviatorObject aviatorObject16) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14, AviatorObject aviatorObject15, AviatorObject aviatorObject16, AviatorObject aviatorObject17) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14, AviatorObject aviatorObject15, AviatorObject aviatorObject16, AviatorObject aviatorObject17, AviatorObject aviatorObject18) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14, AviatorObject aviatorObject15, AviatorObject aviatorObject16, AviatorObject aviatorObject17, AviatorObject aviatorObject18, AviatorObject aviatorObject19) {
        return null;
    }

    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject aviatorObject, AviatorObject aviatorObject1, AviatorObject aviatorObject2, AviatorObject aviatorObject3, AviatorObject aviatorObject4, AviatorObject aviatorObject5, AviatorObject aviatorObject6, AviatorObject aviatorObject7, AviatorObject aviatorObject8, AviatorObject aviatorObject9, AviatorObject aviatorObject10, AviatorObject aviatorObject11, AviatorObject aviatorObject12, AviatorObject aviatorObject13, AviatorObject aviatorObject14, AviatorObject aviatorObject15, AviatorObject aviatorObject16, AviatorObject aviatorObject17, AviatorObject aviatorObject18, AviatorObject aviatorObject19, AviatorObject... aviatorObjects) {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public AviatorObject call() throws Exception {
        return null;
    }
}