package org.zyf.javabasic.letcode.jd150.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 只出现一次的数字 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 19:05
 **/
public class SingleNumber {
    public int singleNumber(int[] nums) {
        // ones记录出现1次的位，twos记录出现2次的位
        int ones = 0, twos = 0;
        for (int num : nums) {
            // 更新ones，twos的值
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        // 返回ones，表示那个只出现一次的数
        return ones;
    }
}
