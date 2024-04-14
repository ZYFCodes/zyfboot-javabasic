package org.zyf.javabasic.test.search;

import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 用于表示跳表（Skip List）及其基本操作，包括插入、删除和查找
 * @author: zhangyanfeng
 * @create: 2024-04-14 12:43
 **/
public class SkipList {

    private static final int MAX_LEVEL = 16; // 最大级别
    private int level; // 当前最大级别
    private SkipListNode head; // 头节点
    private Random rand; // 用于生成随机级别的随机数

    // 构造函数
    public SkipList() {
        this.level = 0;
        this.head = new SkipListNode(Integer.MIN_VALUE, MAX_LEVEL);
        this.rand = new Random();
    }

    // 生成随机级别
    private int randomLevel() {
        int level = 0;
        while (rand.nextDouble() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    // 插入节点
    public void insert(int val) {
        int newLevel = randomLevel();
        SkipListNode newNode = new SkipListNode(val, newLevel);
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = head;

        // 从最高级别开始查找插入位置
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].val < val) {
                current = current.next[i];
            }
            update[i] = current;
        }

        // 更新最大级别
        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) {
                update[i] = head;
            }
            level = newLevel;
        }

        // 插入节点
        for (int i = 0; i <= newLevel; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // 删除节点
    public void delete(int val) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = head;

        // 查找待删除节点位置
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].val < val) {
                current = current.next[i];
            }
            update[i] = current;
        }

        // 删除节点
        if (current.next[0] != null && current.next[0].val == val) {
            SkipListNode deletedNode = current.next[0];
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] == deletedNode) {
                    update[i].next[i] = deletedNode.next[i];
                }
            }
            // 更新最大级别
            while (level > 0 && head.next[level] == null) {
                level--;
            }
        }
    }

    // 查找节点
    public boolean search(int val) {
        SkipListNode current = head;
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].val < val) {
                current = current.next[i];
            }
        }
        current = current.next[0];
        return current != null && current.val == val;
    }

    class SkipListNode {
        int val;
        SkipListNode[] next;

        // 构造函数
        public SkipListNode(int val, int level) {
            this.val = val;
            this.next = new SkipListNode[level + 1];
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        // 插入节点
        skipList.insert(10);
        skipList.insert(20);
        skipList.insert(30);
        skipList.insert(40);
        skipList.insert(50);

        // 查找节点
        int target = 30;
        if (skipList.search(target)) {
            System.out.println("节点 " + target + " 存在于跳表中。");
        } else {
            System.out.println("节点 " + target + " 不存在于跳表中。");
        }

        // 删除节点
        skipList.delete(30);
        System.out.println("节点 " + target + "在跳表中删除节点后：");
        if (skipList.search(target)) {
            System.out.println("节点 " + target + " 存在于跳表中。");
        } else {
            System.out.println("节点 " + target + " 不存在于跳表中。");
        }
    }
}
