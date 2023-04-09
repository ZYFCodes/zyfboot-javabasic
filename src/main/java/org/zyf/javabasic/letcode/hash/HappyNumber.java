package org.zyf.javabasic.letcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 编写一个算法来判断一个数 n 是不是快乐数。
 * @date 2023/4/9  19:42
 */
public class HappyNumber {

    /**
     * 使用哈希集合（HashSet）来存储每一次计算得到的结果，
     * 当出现重复的结果时，就说明进入了循环，可以直接返回 false。
     * 如果计算得到的结果为 1，则说明该数是快乐数，返回 true。
     */
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        /*哈希集合判断循环*/
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /*计算下一个数*/
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n1 = 19;
        /*true*/
        System.out.println(new HappyNumber().isHappy(n1));

        int n2 = 2;
        /*false*/
        System.out.println(new HappyNumber().isHappy(n2));
    }

    public class FloydSolution {
        public boolean isHappy(int n) {
            int slow = n, fast = getNext(n);
            /*快慢指针判断循环*/
            while (fast != 1 && slow != fast) {
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            /*如果最终得到的是 1，则 n 是快乐数*/
            return fast == 1;
        }

        /*计算下一个数*/
        private int getNext(int n) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            return sum;
        }
    }


}
