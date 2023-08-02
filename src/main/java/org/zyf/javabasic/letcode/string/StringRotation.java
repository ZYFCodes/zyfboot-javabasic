package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 给定两个字符串 s1 和 s2，判断 s2 是否是由 s1 旋转得到的结果。
 * 旋转字符串是指将字符串的一部分移到末尾。例如，字符串 'water' 经过旋转后可以得到字符串 'aterw'。
 * 示例 1：
 * 输入：s1 = "water", s2 = "aterw"
 * 输出：true
 * 解释：s2 是 s1 旋转得到的结果。
 * 示例 2：
 * 输入：s1 = "abcde", s2 = "deabc"
 * 输出：true
 * 解释：s2 是 s1 旋转得到的结果。
 * 示例 3：
 * 输入：s1 = "abcde", s2 = "abced"
 * 输出：false
 * 解释：s2 不是 s1 旋转得到的结果。
 * @date 2021/2/7  23:01
 */
public class StringRotation {

    /**
     * 最优解法是使用字符串拼接的方法来判断一个字符串是否是另一个字符串旋转得到的结果。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 将字符串 s1 重复拼接一次，得到新的字符串 s1s1。
     * * 判断字符串 s2 是否是字符串 s1s1 的子串。
     * 这种解法的核心思想是：旋转得到的字符串是由原字符串重复拼接后形成的，因此如果 s2 是由 s1 旋转得到的结果，那么 s2 必然是 s1s1 的子串。
     * 这种解法只需将 s1 重复拼接一次，然后判断 s2 是否是 s1s1 的子串，从而得到字符串是否是旋转得到的结果。
     * 这种解法的空间复杂度是 O(n)，其中 n 是字符串的长度，因为需要额外的空间来存储重复拼接后的新字符串 s1s1。
     * 综上所述，使用字符串拼接的方法来判断字符串是否是旋转得到的结果是最优解法，具有高效和简洁的特点。
     */
    public static boolean isRotation(String s1, String s2) {
        // 将字符串 s1 重复拼接一次，得到新的字符串 s1s1
        String s1s1 = s1 + s1;

        // 判断字符串 s2 是否是字符串 s1s1 的子串
        return s1s1.contains(s2);
    }

    public static void main(String[] args) {
        String s1 = "water";
        String s2 = "aterw";

        boolean result = isRotation(s1, s2);
        // 输出结果： "aterw" is rotation of "water": true
        System.out.println("\"" + s2 + "\" is rotation of \"" + s1 + "\": " + result);
    }
}
