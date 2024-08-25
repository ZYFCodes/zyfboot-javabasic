package org.zyf.javabasic.letcode.jd150.stringarray;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 文本左右对齐
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:31
 **/
public class FullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int numOfLetters = 0;

        for (String word : words) {
            // 如果当前行可以容纳这个单词
            if (numOfLetters + word.length() + currentLine.size() <= maxWidth) {
                currentLine.add(word);
                numOfLetters += word.length();
            } else {
                // 如果当前行满了，处理当前行的文本
                result.add(justifyLine(currentLine, numOfLetters, maxWidth, false));
                currentLine = new ArrayList<>();
                currentLine.add(word);
                numOfLetters = word.length();
            }
        }

        // 处理最后一行，左对齐
        result.add(justifyLine(currentLine, numOfLetters, maxWidth, true));

        return result;
    }

    private String justifyLine(List<String> words, int numOfLetters, int maxWidth, boolean isLastLine) {
        // 如果是最后一行或只有一个单词，左对齐
        if (isLastLine || words.size() == 1) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1); // 删除最后一个空格
            while (sb.length() < maxWidth) {
                sb.append(" ");
            }
            return sb.toString();
        }

        // 计算每个空格的数量
        int totalSpaces = maxWidth - numOfLetters;
        int spacesBetweenWords = totalSpaces / (words.size() - 1);
        int extraSpaces = totalSpaces % (words.size() - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < words.size() - 1) {
                // 每个空格的基础数量
                for (int j = 0; j < spacesBetweenWords; j++) {
                    sb.append(" ");
                }
                // 分配多余的空格
                if (extraSpaces > 0) {
                    sb.append(" ");
                    extraSpaces--;
                }
            }
        }
        return sb.toString();
    }
}
