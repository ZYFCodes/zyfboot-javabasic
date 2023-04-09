package org.zyf.javabasic.letcode.queue;

/**
 * @author yanfengzhang
 * @description 设计你的循环队列实现。
 * 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。
 * 在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。
 * 但是使用循环队列，我们能使用这些空间去存储新的值。
 * @date 2023/4/9  14:58
 */
public class MyCircularQueue {
    /*存储队列元素的数组*/
    private int[] data;
    /*队头指针，指向队头元素*/
    private int front;
    /*队尾指针，指向队尾元素的下一个位置*/
    private int rear;
    /*队列中元素的个数*/
    private int size;

    public MyCircularQueue(int k) {
        /*初始化数组*/
        data = new int[k];
        /*初始时队头指针指向 -1*/
        front = -1;
        /*初始时队尾指针指向 -1*/
        rear = -1;
        /*初始时队列中元素个数为 0*/
        size = 0;
    }

    public boolean enQueue(int value) {
        /*队列已满，插入失败*/
        if (isFull()) {
            return false;
        }
        /*队列为空，需要将 front 指向插入的元素*/
        if (isEmpty()) {
            front = 0;
        }
        /*队尾指针向后移动一位，并使用取模操作保证指针不会越界*/
        rear = (rear + 1) % data.length;
        /*在队尾插入元素*/
        data[rear] = value;
        /*队列中元素个数加一*/
        size++;
        /*插入成功*/
        return true;
    }

    public boolean deQueue() {
        /*队列为空，删除失败*/
        if (isEmpty()) {
            return false;
        }
        /*队列中只有一个元素，需要将 front 和 rear 指向 -1*/
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        /*队列中有多个元素，需要将 front 指针向后移动一位*/
        else {
            /*队头指针向后移动一位，并使用取模操作保证指针不会越界*/
            front = (front + 1) % data.length;
        }
        /*队列中元素个数减一*/
        size--;
        /*删除成功*/
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            /*队列为空，返回 -1*/
            return -1;
        }
        /*返回队头元素*/
        return data[front];
    }

    public int Rear() {
        /*队列为空，返回 -1*/
        if (isEmpty()) {
            return -1;
        }
        /*返回队尾元素*/
        return data[rear];
    }

    public boolean isEmpty() {
        /*判断队列是否为空*/
        return size == 0;
    }

    public boolean isFull() {
        /*判断队列是否已满*/
        return size == data.length;
    }

    /**
     * 在该测试代码中，我们创建了一个容量为 3 的循环队列，
     * 然后进行了一系列操作来测试该类的实现是否正确。
     * 最后输出的结果符合预期，说明该循环队列的实现是正确的。
     */
    public static void main(String[] args) {
        /*创建容量为 3 的循环队列*/
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        /*输出 true，队列变为 [1]*/
        System.out.println(circularQueue.enQueue(1));
        /*输出 true，队列变为 [1, 2]*/
        System.out.println(circularQueue.enQueue(2));
        /*输出 true，队列变为 [1, 2, 3]*/
        System.out.println(circularQueue.enQueue(3));
        /*输出 false，队列已满，插入失败*/
        System.out.println(circularQueue.enQueue(4));
        /*输出 3，队尾元素为 3*/
        System.out.println(circularQueue.Rear());
        /*输出 true，队列已满*/
        System.out.println(circularQueue.isFull());
        /*输出 true，队列变为 [2, 3]*/
        System.out.println(circularQueue.deQueue());
        /*输出 true，队列变为 [2, 3, 4]*/
        System.out.println(circularQueue.enQueue(4));
        /*输出 4，队尾元素为 4*/
        System.out.println(circularQueue.Rear());
    }
}
