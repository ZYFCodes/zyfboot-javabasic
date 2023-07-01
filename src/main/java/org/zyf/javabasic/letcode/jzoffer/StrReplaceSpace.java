package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请实现一个函数，将一个字符串中的每个空格替换成 “%20”。
 * 例如，当字符串为 “We Are Happy” 时，替换后的字符串应为 “We%20Are%20Happy”。
 * @date 2023/6/4  23:40
 */
public class StrReplaceSpace {
    /**
     * 要替换字符串中的空格，可以遍历字符串的每个字符，当遇到空格时，将其替换为 “%20”。
     * 由于替换后的字符串长度会增加，所以可以先遍历一次字符串，统计出空格的个数，
     * 然后计算出替换后的字符串的长度。接下来从字符串末尾开始遍历，
     * 依次将字符复制到替换后的位置上，当遇到空格时，替换为 “%20”。
     * 最后得到的字符串即为替换后的结果。
     */
    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }

        // 原始字符串的长度
        int originalLength = str.length();
        // 空格的个数
        int spaceCount = 0;

        // 统计空格的个数
        for (int i = 0; i < originalLength; i++) {
            if (str.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        // 替换后的字符串的长度
        int newLength = originalLength + spaceCount * 2;
        // 替换后字符串的末尾索引
        int newIndex = newLength - 1;

        // 设置字符串的新长度
        str.setLength(newLength);

        // 从原字符串的末尾开始遍历，将字符复制到替换后的位置上
        for (int i = originalLength - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, c);
            }
        }

        return str.toString();
    }

    public static void main(String[] args) {
        StrReplaceSpace solution = new StrReplaceSpace();
        StringBuffer str = new StringBuffer("We Are Happy");
        String result = solution.replaceSpace(str);
        // 输出 "We%20Are%20Happy"
        System.out.println(result);
    }

}
