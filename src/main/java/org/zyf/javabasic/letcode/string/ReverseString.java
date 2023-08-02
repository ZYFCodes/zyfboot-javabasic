package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 编写一个算法来反转一个字符串。输入字符串以字符数组 char[] 的形式给出。
 * 示例 1:
 * 输入: ["h","e","l","l","o"]
 * 输出: ["o","l","l","e","h"]
 * 示例 2:
 * 输入: ["H","a","n","n","a","h"]
 * 输出: ["h","a","n","n","a","H"]
 * @date 2022/1/1  23:01
 */
public class ReverseString {

    /**
     * 最优解法是使用双指针来实现字符串反转。双指针方法可以在O(n)的时间复杂度内完成字符串反转，并且不需要使用额外的空间。
     * 以下是具体的步骤分析：
     * 使用两个指针 left 和 right，分别指向字符串的首尾字符。
     * 不断交换 left 和 right 指针所指向的字符，然后将指针向中间移动。直到 left 指针超过或等于 right 指针，完成字符串反转。
     * 字符串反转完成后，原来的字符串就会被修改为反转后的字符串。
     * 由于这个方法只涉及一次遍历，因此时间复杂度是 O(n)。同时，它只使用了常数级的额外空间，即两个指针，所以空间复杂度是 O(1)。
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        // 不断交换 left 和 right 指针所指向的字符
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // 将指针向中间移动
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};

        // 反转字符数组 s1 和 s2
        ReverseString reverse = new ReverseString();
        reverse.reverseString(s1);
        reverse.reverseString(s2);

        // 打印反转后的结果
        System.out.println("反转后的字符数组 s1：");
        // 输出结果：o,l,l,e,h
        printArray(s1);

        System.out.println("\n反转后的字符数组 s2：");
        // 输出结果：h,a,n,n,a,H
        printArray(s2);
    }

    private static void printArray(char[] arr) {
        for (char c : arr) {
            System.out.print(c + " ");
        }
    }
}
