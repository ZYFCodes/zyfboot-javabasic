package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 给定一个数字，按照如下规则把它翻译成字符串：0翻译成”a”，1翻译成”b”，……，25翻译成”z”。
 * 一个数字可能有多个翻译。求给定数字所有可能的翻译方法的总数。
 * @date 2023/6/8  22:25
 */
public class TranslateNumToStr {
    /**
     * 这是一个动态规划的问题。我们可以定义一个dp数组，其中dp[i]表示前i个数字的翻译方法的总数。
     * 对于当前位置i，有两种情况：
     * 1.	如果第i-1位和第i位组成的两位数在10到25之间（包含10和25），那么可以把第i位和第i-1位看作一个整体进行翻译。则dp[i] = dp[i-2] + dp[i-1]。
     * 2.	如果第i-1位和第i位组成的两位数不在10到25之间，那么只能把第i位单独翻译。则dp[i] = dp[i-1]。
     * 初始情况下，dp[0] = 1，dp[1] = 1，因为只有一个数字时只有一种翻译方式。
     * 最终结果为dp[n]，其中n为给定数字的位数。
     */
    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        int n = numStr.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            String twoDigits = numStr.substring(i - 2, i);
            if (twoDigits.compareTo("10") >= 0 && twoDigits.compareTo("25") <= 0) {
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        TranslateNumToStr solution = new TranslateNumToStr();

        int num = 12258;
        int count = solution.translateNum(num);
        System.out.println("The total number of translation methods is: " + count);
    }
}
