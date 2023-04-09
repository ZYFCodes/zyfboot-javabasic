package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定一个字符串路径，返回其简化的绝对路径。
 * 例如，path = "/home/", => "/home"；path = "/../", => "/"；
 * path = "/home//foo/", => "/home/foo"；
 * path = "/a/./b/../../c/", => "/c"
 * @date 2023/4/9  00:17
 */
public class SimplifyPath {

    /**
     * 最优解法是使用栈。具体做法如下：
     * <p>
     * 1 以“/”作为分隔符，将输入路径拆分成若干个部分。
     * 遍历这些部分：
     * 如果部分为空或为“.””，则不做处理，继续遍历下一个部分。
     * 如果部分为“..”，则将栈顶元素弹出，表示返回上级目录。
     * 否则，将部分压入栈中，表示进入下一级目录。
     * 2 遍历完所有部分后，将栈中的元素按顺序拼接起来，得到简化后的路径。
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if (s.equals(".") || s.equals("")) {
                /*对于"."和空字符串，不做任何操作*/
            } else if (s.equals("..")) {
                /*如果是"..", 则需要返回上一级目录*/
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                /*否则就是一个普通的路径名，需要入栈*/
                stack.push(s);
            }
        }

        /*构造简化后的路径*/
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/").append(s);
        }

        /*如果栈为空，说明简化后的路径是根路径"/" */
        if (stack.isEmpty()) {
            return "/";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/a//b/../../c/";
        /*输出：/c */
        System.out.println(new SimplifyPath().simplifyPath(path));
    }
}
