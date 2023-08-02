package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 将给定的非负整数转换为对应的英文单词表示，例如 12345 转换为 "Twelve Thousand Three Hundred Forty-Five"。
 * @date 2021/3/25  23:50
 */
public class IntegerToEnglishWords {

    private static final String[] ones = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
    };
    private static final String[] teens = {
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    private static final String[] tens = {
            "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    private static final String[] thousands = {
            "", "Thousand", "Million", "Billion"
    };

    /**
     * 最优解法是使用递归的方式来将给定的非负整数转换为对应的英文单词表示。这种解法的时间复杂度是 O(log10(n))，其中 n 是给定的非负整数。
     * 具体的最优解法步骤如下：
     * * 定义两个辅助数组 ones 和 tens，分别用于表示 1 到 9 和 10 到 90 的英文单词。
     * * 实现一个递归函数 convertToWords，用于将给定的非负整数转换为英文单词。
     * * 在递归函数中，将整数按照每三位进行分组，例如 12345 分组为 12 和 345。
     * * 处理每个分组，分别转换为对应的英文单词，并加上对应的单位（例如 "Thousand"、"Million" 等）。
     * * 将所有处理后的分组拼接起来得到最终结果。
     * 这种解法的核心思想是利用递归函数处理整数的每个分组，然后逐层返回处理后的结果，最终得到整个数字转换为英文单词的表示。
     * 由于这个解法是通过递归函数来处理每个分组，并且每次处理后的数字都会减小一半，因此时间复杂度是 O(log10(n))，其中 n 是给定的非负整数。
     * 另外，除了存储辅助数组和递归函数的栈空间外，这个解法不需要使用额外的空间，所以空间复杂度是 O(1)。
     * 综上所述，使用递归的方式来将给定的非负整数转换为对应的英文单词表示是最优解法，具有高效的时间复杂度和固定的空间复杂度。
     */
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder words = new StringBuilder();
        // 用于指示当前处理的分组的单位索引
        int index = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                // 处理当前分组的数字
                StringBuilder groupWords = new StringBuilder();
                convertToWords(groupWords, num % 1000);
                words.insert(0, groupWords.append(thousands[index]).append(" "));
            }

            // 继续处理下一个分组
            num /= 1000;
            index++;
        }

        return words.toString().trim();
    }

    private static void convertToWords(StringBuilder words, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            words.append(ones[num]).append(" ");
        } else if (num < 20) {
            words.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            words.append(tens[num / 10]).append(" ");
            convertToWords(words, num % 10);
        } else {
            words.append(ones[num / 100]).append(" Hundred ");
            convertToWords(words, num % 100);
        }
    }

    public static void main(String[] args) {
        int num1 = 12345;
        int num2 = 56789123;

        String words1 = numberToWords(num1);
        String words2 = numberToWords(num2);

        // 输出结果：12345 -> Twelve Thousand Three Hundred Forty Five
        System.out.println(num1 + " -> " + words1);
        // 输出结果：56789123 -> Fifty Six Million Seven Hundred Eighty Nine Thousand One Hundred Twenty Three
        System.out.println(num2 + " -> " + words2);
    }
}
