package org.zyf.javabasic.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/15  17:17
 */
public class SafeArrayList<T> {
    //封装ArrayList
    List<T> c = new ArrayList<T>();

    //控制访问路径
    synchronized T get(int idx) {
        return c.get(idx);
    }

    synchronized void add(int idx, T t) {
        c.add(idx, t);
    }

    synchronized boolean addIfNotExist(T t) {
        if (!c.contains(t)) {
            c.add(t);
            return true;
        }
        return false;
    }

}
