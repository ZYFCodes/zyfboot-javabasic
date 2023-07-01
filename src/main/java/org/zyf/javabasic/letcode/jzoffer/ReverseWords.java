package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description  请实现一个函数，将一个字符串中的单词进行翻转。
 * 例如，输入字符串 “I am a student.”，则输出 “student. a am I”。
 * @date 2023/6/5  22:35
 */
public class ReverseWords {
    /**
     * 可以按照以下步骤实现翻转单词序列的功能：
     * 	1.	首先去除字符串中的前导空格和尾部空格，可以使用 trim() 方法。
     * 	2.	将字符串按空格进行分割，得到一个单词数组。
     * 	3.	使用双指针的方法，对单词数组进行翻转。初始化两个指针 left 和 right，分别指向数组的首尾元素。
     * 	•	交换指针指向的元素。
     * 	•	左指针向右移动，右指针向左移动，直到两个指针相遇。
     * 	4.	将翻转后的单词数组拼接成一个新的字符串，每个单词之间用空格分隔。
     */
    public String reverseWords(String s) {
        // 去除前导空格和尾部空格
        s = s.trim();

        // 按空格分割字符串，得到单词数组
        String[] words = s.split("\\s+");

        int left = 0;
        int right = words.length - 1;

        // 双指针翻转单词数组
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;

            left++;
            right--;
        }

        // 拼接单词数组成新的字符串
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append(" ");
        }

        // 去除最后一个空格
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseWords solution = new ReverseWords();

        String s = "I am a student.";
        String result = solution.reverseWords(s);

        System.out.println("翻转后的字符串：" + result);
    }
}
