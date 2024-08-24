package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 括号生成（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:31
 **/
public class GenerateParenthesisSolution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String current, int leftCount, int rightCount, int n) {
        // 当当前组合的长度等于 2 * n 时，说明已经生成了一个有效的组合
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // 只要左括号的数量小于 n，就可以添加一个左括号
        if (leftCount < n) {
            backtrack(result, current + "(", leftCount + 1, rightCount, n);
        }

        // 只要右括号的数量小于左括号的数量，就可以添加一个右括号
        if (rightCount < leftCount) {
            backtrack(result, current + ")", leftCount, rightCount + 1, n);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesisSolution solution = new GenerateParenthesisSolution();
        System.out.println(solution.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println(solution.generateParenthesis(1)); // ["()"]
    }
}
