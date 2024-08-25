package org.zyf.javabasic.letcode.jd150.stringarray;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: O(1) 时间插入、删除和获取随机元素
 * @author: zhangyanfeng
 * @create: 2024-08-25 09:18
 **/
public class RandomizedSet {
    // 动态数组用于存储集合中的元素
    private List<Integer> nums;
    // 哈希表用于存储每个元素对应在动态数组中的索引
    private Map<Integer, Integer> valToIndex;
    private Random rand;

    // 构造函数，初始化动态数组和哈希表
    public RandomizedSet() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();
        rand = new Random();
    }

    // 插入操作
    public boolean insert(int val) {
        // 如果元素已存在，返回false
        if (valToIndex.containsKey(val)) {
            return false;
        }
        // 在数组末尾添加新元素，并在哈希表中记录其索引
        nums.add(val);
        valToIndex.put(val, nums.size() - 1);
        return true;
    }

    // 删除操作
    public boolean remove(int val) {
        // 如果元素不存在，返回false
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        // 获取待删除元素的索引
        int index = valToIndex.get(val);
        // 将待删除元素与数组的最后一个元素交换位置
        int lastElement = nums.get(nums.size() - 1);
        nums.set(index, lastElement);
        valToIndex.put(lastElement, index);
        // 删除数组的最后一个元素，并移除哈希表中的记录
        nums.remove(nums.size() - 1);
        valToIndex.remove(val);
        return true;
    }

    // 随机获取元素操作
    public int getRandom() {
        // 从数组中随机选择一个元素并返回
        return nums.get(rand.nextInt(nums.size()));
    }

    // 测试用例
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

        System.out.println(randomizedSet.insert(1)); // true
        System.out.println(randomizedSet.remove(2)); // false
        System.out.println(randomizedSet.insert(2)); // true
        System.out.println(randomizedSet.getRandom()); // 1 or 2
        System.out.println(randomizedSet.remove(1)); // true
        System.out.println(randomizedSet.insert(2)); // false
        System.out.println(randomizedSet.getRandom()); // 2
    }
}
