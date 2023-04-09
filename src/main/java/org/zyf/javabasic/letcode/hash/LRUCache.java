package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作：获取数据 get 和写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * @date 2023/4/9  19:11
 */
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        /*将节点移动到双向链表头部*/
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            /*如果节点不存在，则创建一个新节点并加入到双向链表头部和哈希表中*/
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                /*如果超出容量，则删除双向链表尾部节点并在哈希表中删除对应的键值对*/
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            /*如果节点存在，则更新节点的值，并将节点移动到双向链表头部*/
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        /*将节点加入到双向链表头部*/
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        /*从双向链表中删除节点*/
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        /*将节点移动到双向链表头部*/
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        /*删除双向链表尾部节点，并返回被删除的节点*/
        DLinkedNode tail = this.tail.prev;
        removeNode(tail);
        return tail;
    }

    /**
     * 可以看到，LRU 缓存机制在存储容量达到最大值时，
     * 能够正确地淘汰最近最少使用的节点，
     * 并保证每个节点的访问顺序符合 LRU 缓存机制的要求。
     */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        /*output: 1*/
        System.out.println(cache.get(1));
        cache.put(3, 3);
        /*output: -1*/
        System.out.println(cache.get(2));
        cache.put(4, 4);
        /*output: -1*/
        System.out.println(cache.get(1));
        /*output: 3*/
        System.out.println(cache.get(3));
        /*output: 4*/
        System.out.println(cache.get(4));
    }

}

