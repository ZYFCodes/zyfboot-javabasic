package org.zyf.javabasic.letcode.sort;

/**
 * @author yanfengzhang
 * @description 冒泡排序（Bubble Sort）
 * 通过不断地交换相邻两个元素的位置，把最大的元素逐渐“冒泡”到数组的最后面，从而实现排序.
 * @date 2023/4/15  22:15
 */
public class BubbleSort {
    public static void bubbleSort(int[] array) {
        /*获取数组长度*/
        int n = array.length;
        /*外层循环控制排序轮数*/
        for (int i = 0; i < n; i++) {
            /*内层循环控制每轮比较的次数*/
            for (int j = 0; j < n - i - 1; j++) {
                /*如果顺序不正确就交换相邻两个元素的位置*/
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort1(int[] array) {
        int n = array.length;
        /*记录最后一次交换的位置*/
        int lastSwappedIndex = n - 1;
        for (int i = 0; i < n; i++) {
            /*标记本轮循环是否有交换发生*/
            boolean swapped = false;
            for (int j = 0; j < lastSwappedIndex; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            /*如果本轮循环没有交换发生，说明已经排好序*/
            if (!swapped) {
                break;
            }
            /*更新最后一次交换的位置*/
            lastSwappedIndex--;
        }
    }

    public static void cocktailSort(int[] arr) {
        /*标志位，记录是否进行过交换*/
        boolean swapped = true;
        /*从左向右排序的起始位置*/
        int start = 0;
        /*从右向左排序的起始位置*/
        int end = arr.length - 1;

        /*只要还有元素需要排序就继续循环*/
        while (swapped) {
            swapped = false;

            /*从左向右进行一次冒泡排序，将最大的元素放到数组的末尾*/
            for (int i = start; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    /*交换两个元素的位置*/
                    swap(arr, i, i + 1);
                    /*标记已经进行过交换*/
                    swapped = true;
                }
            }
            /*如果没有进行过交换，则说明数组已经有序，直接退出循环*/
            if (!swapped) {
                break;
            }

            swapped = false;
            /*更新从右向左排序的起始位置*/
            end--;

            /*从右向左进行一次冒泡排序，将最小的元素放到数组的开头*/
            for (int i = end - 1; i >= start; i--) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            /*更新从左向右排序的起始位置*/
            start++;
        }
    }

    /*交换数组中两个元素的位置*/
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        /*创建一个待排序的数组*/
        int[] array = {5, 3, 8, 6, 4};
        /*对数组进行排序*/
        bubbleSort(array);
        /*输出排序后的结果*/
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
