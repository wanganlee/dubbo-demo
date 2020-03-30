package com.demo.io;

import com.google.common.collect.Sets;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存用户名和对应输出流之间的映射关系
 * <p>
 * 这样既可以通过用户名找到对应的输出流，也可以根据输出流找到对应的用户名
 * </p>
 *
 * @author developer.wang
 * @date 2020/3/24
 */
public class CrazyItMap<K, V> {

    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(32);

    public ConcurrentHashMap<K, V> map() {
        return this.map;
    }

    /**
     * 根据value来删除指定项
     */
    public void removeByValue(Object value) {
        Assert.notNull(value, "value must not be null");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                map.remove(entry.getKey());
                break;
            }
        }
    }

    /**
     * 获取所有的value的集合
     */
    public Set<V> valueSet() {
        return Sets.newConcurrentHashSet(map.values());
    }

    /**
     * 根据value查找key
     */
    public K getKeyByValue(V value) {
        Assert.notNull(value, "value must not be null");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value == entry.getValue() || Objects.equals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 实现put方法，该方法不允许value重复
     */
    public V put(K key, V value) {
        Assert.notNull(key, "key must not be null");
        Assert.notNull(value, "value must not be null");

        if (map.containsValue(value)) {
            throw new IllegalArgumentException(String.format("map already has value element: %s", value));
        }
        map.put(key, value);
        return value;
    }

}
