package org.zyf.javabasic.letcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 使用堆排序思想实现优先级队列
 * 对于一个优先级队列，每次插入一个元素后需要重新排序，可以使用堆排序的思想，
 * 将插入的元素加入堆中，然后进行堆排序，即可维护优先级队列的有序性。
 * @date 2023/4/16  12:21
 */
public class PriorityQueueUseHeap<T extends Comparable<T>> {
    private List<T> heap;

    public PriorityQueueUseHeap() {
        heap = new ArrayList<>();
    }

    public void insert(T item) {
        heap.add(item);
        int i = heap.size() - 1;
        while (i > 0) {
            int j = (i - 1) / 2;
            if (heap.get(i).compareTo(heap.get(j)) <= 0) {
                break;
            }
            Collections.swap(heap, i, j);
            i = j;
        }
    }

    public T peek() {
        if (heap.size() == 0) {
            return null;
        }
        return heap.get(0);
    }

    public T remove() {
        if (heap.size() == 0) {
            return null;
        }
        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (heap.size() > 0) {
            heap.set(0, last);
            int i = 0;
            while (i < heap.size()) {
                int left = i * 2 + 1;
                int right = i * 2 + 2;
                if (left >= heap.size()) {
                    break;
                }
                int j = left;
                if (right < heap.size() && heap.get(right).compareTo(heap.get(left)) > 0) {
                    j = right;
                }
                if (heap.get(i).compareTo(heap.get(j)) >= 0) {
                    break;
                }
                Collections.swap(heap, i, j);
                i = j;
            }
        }
        return root;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public static void main(String[] args) {
        /*创建一个自定义的最大堆*/
        PriorityQueueUseHeap<Integer> maxHeap = new PriorityQueueUseHeap<>();

        /*插入一些测试数据*/
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.insert(6);

        /*求最大的 k 个元素*/
        int k = 3;

        System.out.println("最大的 " + k + " 个元素是：");
        for (int i = 0; i < k; i++) {
            Integer num = maxHeap.remove();
            System.out.print(num + " ");
        }
    }

}
