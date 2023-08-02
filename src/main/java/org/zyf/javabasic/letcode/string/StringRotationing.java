package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 实现字符串的循环移位操作，将字符串向右循环移动指定的位数。
 * 例如，将字符串 "abcd" 循环右移两位，得到字符串 "cdab"。
 * @date 2021/2/12  23:05
 */
public class StringRotationing {

    /**
     * 最优解法是使用字符串切片的方法来实现字符串的循环移位操作。这种解法的时间复杂度是 O(n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 对于要循环右移的位数 k，先将 k 对字符串长度取模，以处理 k 大于字符串长度的情况。
     * * 将字符串 s 分成两部分，前半部分从索引 0 到 n - k - 1，后半部分从索引 n - k 到 n - 1，其中 n 是字符串的长度。
     * * 将前半部分和后半部分重新拼接得到循环右移后的字符串。
     * 这种解法的核心思想是，将字符串的后部分移到前面，形成循环右移的效果。
     * 这种解法只需进行一次字符串切片和拼接，从而实现字符串的循环移位操作。
     * 这种解法的空间复杂度是 O(n)，其中 n 是字符串的长度，因为需要额外的空间来存储切片后的字符串的两部分。
     * 综上所述，使用字符串切片的方法来实现字符串的循环移位操作是最优解法，具有高效和简洁的特点。
     */
    public static String rotateString(String s, int k) {
        // 对于要循环右移的位数 k，先将 k 对字符串长度取模，以处理 k 大于字符串长度的情况
        int n = s.length();
        k %= n;

        // 将字符串 s 分成两部分，前半部分从索引 0 到 n - k - 1，后半部分从索引 n - k 到 n - 1
        String firstPart = s.substring(0, n - k);
        String secondPart = s.substring(n - k, n);

        // 将前半部分和后半部分重新拼接得到循环右移后的字符串
        return secondPart + firstPart;
    }

    public static void main(String[] args) {
        String s = "abcd";
        int k = 2;

        String result = rotateString(s, k);
        // 输出结果：Rotate "abcd" 2 positions to the right: "cdab"
        System.out.println("Rotate \"" + s + "\" " + k + " positions to the right: " + result);
    }
}
