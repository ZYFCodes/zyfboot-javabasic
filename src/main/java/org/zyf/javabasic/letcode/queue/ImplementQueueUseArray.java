package org.zyf.javabasic.letcode.queue;

/**
 * @author yanfengzhang
 * @description 设计并实现一个基于数组的队列（Queue）数据结构，
 * 要求支持入队（enqueue）和出队（dequeue）操作，
 * 并能够正确地维护队列的顺序。
 * enqueue 和 dequeue 操作的时间复杂度为 O(1)，isEmpty 和 size 方法的时间复杂度也为 O(1)。
 *
 * 要使用数组实现一个队列，可以利用数组的特性进行操作。以下是一种常见的实现思路：
 * 	1.	创建一个固定大小的数组，用于存储队列的元素。
 * 	2.	使用两个指针，分别指向队列的头部和尾部。
 * 	3.	初始时，头部和尾部指针都指向数组的起始位置。
 * 	4.	入队操作：将元素添加到尾部指针所指向的位置，然后将尾部指针后移一位。
 * 	5.	出队操作：将头部指针所指向的元素移除，并将头部指针后移一位。
 * 	6.	队列为空时，头部和尾部指针指向同一个位置。
 * @date 2023/6/14  23:51
 */
public class ImplementQueueUseArray {
    private int capacity;
    private int[] queue;
    // 头部指针
    private int head;
    // 尾部指针
    private int tail;

    public ImplementQueueUseArray(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.head = 0;
        this.tail = 0;
    }

    public void enqueue(int item) {
        if (tail == capacity) {
            // 队列已满
            throw new IndexOutOfBoundsException("Queue is full");
        }
        queue[tail] = item;
        tail++;
    }

    public int dequeue() {
        if (head == tail) {
            // 队列为空
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        int item = queue[head];
        head++;
        return item;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return tail - head;
    }

    public static void main(String[] args) {
        ImplementQueueUseArray queue = new ImplementQueueUseArray(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Size of queue: " + queue.size());

        int item1 = queue.dequeue();
        int item2 = queue.dequeue();

        System.out.println("Dequeued items: " + item1 + ", " + item2);
        System.out.println("Size of queue after dequeue: " + queue.size());
    }
}
