package org.zyf.javabasic.aop.basic;

import org.zyf.javabasic.common.Result;

import java.util.*;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  11:05
 */
public class ResultMap<K, V> extends Result<Map<K, V>> implements Map<K, V> {
    protected Map<K, V> map = null;

    public ResultMap() {
        this.map = new HashMap();
    }

    public ResultMap(TreeMap<K, V> map) {
        this.map = map;
    }

    public ResultMap(int initialCapacity) {
        this.map = new HashMap(initialCapacity);
    }

    public ResultMap(ResultMap resultMap) {
        this.map = new HashMap(resultMap.size());
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return this.map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }

    public void setResultCode(Set<ResultCode> codes) {

    }

    public void addResultCode(ResultCode rc) {
    }


}

