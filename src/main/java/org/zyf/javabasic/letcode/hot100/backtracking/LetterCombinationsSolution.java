package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 电话号码的字母组合（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:21
 **/
public class LetterCombinationsSolution {
    // 映射数字到字母
    private final String[] mapping = {
            "",    // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // 如果当前组合的长度等于输入的数字长度，添加到结果中
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 获取当前数字对应的字母
        String letters = mapping[digits.charAt(index) - '0'];

        // 遍历当前数字对应的每个字母
        for (char letter : letters.toCharArray()) {
            current.append(letter); // 选择当前字母
            backtrack(result, current, digits, index + 1); // 递归处理下一个数字
            current.deleteCharAt(current.length() - 1); // 撤销选择（回溯）
        }
    }

    public static void main(String[] args) {
        LetterCombinationsSolution solution = new LetterCombinationsSolution();
        System.out.println(solution.letterCombinations("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(solution.letterCombinations(""));  // []
        System.out.println(solution.letterCombinations("2")); // ["a","b","c"]
    }
}
