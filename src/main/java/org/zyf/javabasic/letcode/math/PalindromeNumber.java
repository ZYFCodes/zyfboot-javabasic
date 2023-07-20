package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个整数 x，判断它是否是回文数。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1：输入：x = 121      输出：true
 * 示例 2：输入：x = -121    输出：false
 * 解释：从左向右读为 "121"，从右向左读为 "121-"。因此它不是一个回文数。
 * 示例 3：输入：x = 10       输出：false
 * 解释：从右向左读为 "01"。因此它不是一个回文数。
 * 示例 4：输入：x = -101    输出：false
 * <p>
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 * 注意：解决此问题时，请考虑整数的边界情况。
 * @date 2023/7/3  23:31
 */
public class PalindromeNumber {

    /**
     * 最优解法可以通过以下步骤来判断一个整数是否为回文数：
     * 1. 将整数转换为字符串，便于进行字符的比较。
     * 2. 使用双指针法，一个指针从字符串的左侧开始，另一个指针从右侧开始，逐个字符进行比较。
     * 3. 如果对应位置的字符相同，则继续比较下一个位置；如果对应位置的字符不同，则说明整数不是回文数，直接返回false。
     * 4. 如果左指针大于等于右指针，说明整数是回文数，返回true。
     * 该算法的时间复杂度为 O(n)，其中 n 为整数的位数，因为最坏情况下需要遍历整个字符串。
     * 而空间复杂度为 O(n)，需要将整数转换为字符串来存储。
     * 需要注意的是，在转换整数为字符串时，由于整数可能有负号，因此在比较时需要考虑正负号，负号位于字符串的首位，不会影响回文数的判断。
     */
    public static boolean isPalindrome(int x) {
        // 如果整数为负数，直接返回false
        if (x < 0) {
            return false;
        }

        // 将整数转换为字符串
        String str = String.valueOf(x);

        // 左指针
        int left = 0;
        // 右指针
        int right = str.length() - 1;

        // 使用双指针法进行比较
        while (left < right) {
            // 如果对应位置的字符不同，返回false
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            // 继续比较下一个位置
            left++;
            right--;
        }

        // 如果左指针大于等于右指针，说明整数是回文数，返回true
        return true;
    }

    public static void main(String[] args) {
        int x1 = 121;
        int x2 = -121;
        int x3 = 10;
        int x4 = -101;

        System.out.println("Input: " + x1 + " Output: " + isPalindrome(x1));
        System.out.println("Input: " + x2 + " Output: " + isPalindrome(x2));
        System.out.println("Input: " + x3 + " Output: " + isPalindrome(x3));
        System.out.println("Input: " + x4 + " Output: " + isPalindrome(x4));
    }
}
