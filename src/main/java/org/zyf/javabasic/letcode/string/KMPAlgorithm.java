package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 要求实现字符串匹配算法，用于查找一个字符串在另一个字符串中的位置。
 * @date 2021/9/1  23:13
 */
public class KMPAlgorithm {

    /**
     * KMP 算法通过预处理模式串（待查找的子串），构建部分匹配表（Partial Match Table，PMT），用来在匹配过程中快速跳过不必要的比较。
     * 这个表告诉算法在失配时应该将模式串向右移动多少位，而不是一位一位地移动。
     * KMP 算法的核心思想在于不回溯主串的指针，而是利用部分匹配表的信息来调整模式串的位置。
     * 这样，可以大幅减少比较的次数，提高匹配效率。
     * KMP 算法的时间复杂度是 O(m + n)，其中 m 是主串的长度，n 是模式串的长度
     * 。KMP 算法的预处理部分时间复杂度为 O(n)，匹配部分时间复杂度为 O(m)。
     */
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        char[] txt = haystack.toCharArray();
        char[] pat = needle.toCharArray();

        int[] lps = computeLPSArray(pat);

        // txt 指针
        int i = 0;
        // pat 指针
        int j = 0;

        while (i < txt.length) {
            if (txt[i] == pat[j]) {
                i++;
                j++;
            }

            if (j == pat.length) {
                return i - j;
            } else if (i < txt.length && txt[i] != pat[j]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    /**
     * 计算部分匹配表（lps 表）
     */
    private static int[] computeLPSArray(char[] pat) {
        int[] lps = new int[pat.length];
        // 当前匹配的长度
        int len = 0;
        int i = 1;

        while (i < pat.length) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        int result = strStr(haystack, needle);
        // 输出结果：Needle "ll" found at index: 2
        System.out.println("Needle \"" + needle + "\" found at index: " + result);
    }
}
