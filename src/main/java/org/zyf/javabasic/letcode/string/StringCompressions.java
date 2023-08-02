package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 实现字符串的压缩和解压缩功能，例如 Run-Length Encoding (RLE)。
 * Run-Length Encoding 是一种简单的压缩算法，它将连续出现的字符替换为字符和出现次数的组合。
 * 例如，对于字符串 "aaabbcccaa"，使用 Run-Length Encoding 可以将其压缩为 "3a2b3c2a"。
 * 解压缩则是将压缩后的字符串还原为原始字符串的过程。
 * @date 2021/3/2  23:12
 */
public class StringCompressions {

    /**
     * 实现字符串压缩功能
     */
    public static String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        // 遍历字符串并统计连续相同字符的数量
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                // 将连续相同字符替换为字符和出现次数的组合
                compressed.append(count).append(str.charAt(i));
                count = 1;
            }
        }

        // 处理最后一个字符
        compressed.append(count).append(str.charAt(str.length() - 1));

        return compressed.toString();
    }

    /**
     * 实现字符串解压缩功能
     */
    public static String decompress(String str) {
        StringBuilder decompressed = new StringBuilder();
        int count = 0;

        // 遍历压缩后的字符串
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                // 获取连续出现的次数
                count = count * 10 + (c - '0');
            } else {
                // 将字符和出现次数的组合还原为连续相同字符
                for (int j = 0; j < count; j++) {
                    decompressed.append(c);
                }
                count = 0;
            }
        }

        return decompressed.toString();
    }

    public static void main(String[] args) {
        String originalStr = "aaabbcccaa";

        String compressedStr = compress(originalStr);
        // 输出结果：Compressed: 3a2b3c2a
        System.out.println("Compressed: " + compressedStr);

        String decompressedStr = decompress(compressedStr);
        // 输出结果：Decompressed: aaabbcccaa
        System.out.println("Decompressed: " + decompressedStr);
    }

}
