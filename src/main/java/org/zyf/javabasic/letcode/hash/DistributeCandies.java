package org.zyf.javabasic.letcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每个数字代表一个糖果。
 * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 * @date 2023/4/10  23:50
 */
public class DistributeCandies {

    /**
     * 分糖果问题可以使用哈希表来解决。具体步骤如下：
     * <p>
     * 遍历糖果数组 candies，使用哈希表统计糖果的种类数。
     * 如果糖果的种类数大于糖果数组长度的一半，则最多只能拥有糖果数组长度的一半种糖果。否则，能够拥有所有的糖果种类。
     * 返回最终结果。
     */
    public int distributeCandies(int[] candies) {
        /*统计糖果的种类数*/
        Set<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        /*如果糖果的种类数大于糖果数组长度的一半，最多只能拥有糖果数组长度的一半种糖果*/
        int n = candies.length;
        int maxNum = n / 2;
        int candyType = set.size();
        return candyType >= maxNum ? maxNum : candyType;
    }

    public static void main(String[] args) {
        int[] candies1 = {1, 1, 2, 2, 3, 3};
        int[] candies2 = {1, 1, 2, 3};
        int[] candies3 = {6, 6, 6, 6};
        int[] candies4 = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3};

        /*Output: 3*/
        System.out.println(new DistributeCandies().distributeCandies(candies1));
        /*Output: 2*/
        System.out.println(new DistributeCandies().distributeCandies(candies2));
        /*Output: 1*/
        System.out.println(new DistributeCandies().distributeCandies(candies3));
        /*Output: 3*/
        System.out.println(new DistributeCandies().distributeCandies(candies4));
    }

}
