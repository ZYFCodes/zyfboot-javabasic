package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 将一个字符串中的每个单词反转。
 * 例如，对于字符串 "Let's take LeetCode contest"，
 * 反转每个单词后得到 "s'teL ekat edoCteeL tsetnoc"。
 * @date 2021/3/6  23:24
 */
public class WordReverse {

    /**
     * 最优解法是使用双指针的方法来将一个字符串中的每个单词反转。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 定义两个指针 start 和 end，分别表示当前单词的起始位置和结束位置。
     * * 遍历字符串，当遇到空格或到达字符串末尾时，将 start 到 end 之间的字符进行反转。
     * * 更新 start 和 end，继续遍历字符串直到结束。
     * 这种解法的核心思想是利用双指针来定位每个单词的起始位置和结束位置，然后对每个单词进行反转。
     * 由于这个解法只需要遍历一次字符串，并且在遍历过程中对每个单词进行反转操作，因此时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 另外，由于只需要使用常数大小的额外空间来进行指针操作和字符反转，空间复杂度是 O(1)。
     * 综上所述，使用双指针的方法来将一个字符串中的每个单词反转是最优解法，具有高效的时间复杂度和节省空间的特点。
     */
    public static String reverseWords(String s) {
        char[] charArray = s.toCharArray();
        // 当前单词的起始位置
        int start = 0;
        // 当前单词的结束位置
        int end = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                // 遇到空格，反转当前单词
                reverseWord(charArray, start, end);
                // 更新下一个单词的起始位置
                start = i + 1;
            }
            // 更新当前单词的结束位置
            end = i;
        }

        // 反转最后一个单词
        reverseWord(charArray, start, end);

        return new String(charArray);
    }

    /**
     * 反转单词的函数
     */
    private static void reverseWord(char[] charArray, int start, int end) {
        while (start < end) {
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        String reversed = reverseWords(s);
        System.out.println("Reversed Words: " + reversed);
    }

}
