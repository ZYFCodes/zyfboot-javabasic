package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 最后一个单词的长度
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:09
 **/
public class LastWordLength {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int index = s.length() - 1;

        // 从后向前跳过空格
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }

        // 计算最后一个单词的长度
        while (index >= 0 && s.charAt(index) != ' ') {
            length++;
            index--;
        }

        return length;
    }

    public static void main(String[] args) {
        LastWordLength solution = new LastWordLength();

        // 测试用例
        System.out.println(solution.lengthOfLastWord("Hello World"));         // 输出: 5
        System.out.println(solution.lengthOfLastWord(" fly me to the moon ")); // 输出: 4
        System.out.println(solution.lengthOfLastWord("luffy is still joyboy")); // 输出: 6
    }
}
