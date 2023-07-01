package org.zyf.javabasic.letcode.jzoffer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 字符串的排列问题
 * @date 2023/6/5  22:52
 */
public class Permutation {
    /**
     * 回溯算法可以用于解决字符串的排列问题。下面是使用回溯算法解决字符串排列问题的思路：
     * 1.	将字符串转换为字符数组，方便处理。
     * 2.	创建一个布尔数组used，用于标记字符是否已经被使用过。
     * 3.	定义一个递归函数，参数包括字符数组、当前索引、已生成的排列、结果列表和used数组。
     * 4.	如果当前索引等于字符数组的长度，表示已经生成了一个排列，将其加入结果列表。
     * 5.	遍历字符数组的所有字符，如果字符没有被使用过，则将其添加到已生成的排列中，标记为已使用，然后递归调用函数处理下一个索引。
     * 6.	在递归调用完成后，需要将添加的字符移出已生成的排列，将标记置为未使用，以便进行下一次循环。
     */
    public ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }

        // 将字符串转换为字符数组
        char[] arr = str.toCharArray();
        // 用于标记字符是否已经被使用过
        boolean[] used = new boolean[arr.length];

        // 对字符数组进行排序（可选）
        Arrays.sort(arr);

        permutationHelper(arr, 0, new StringBuilder(), result, used);

        return result;
    }

    private void permutationHelper(char[] arr, int index, StringBuilder sb, ArrayList<String> result, boolean[] used) {
        if (index == arr.length) {
            // 当索引等于数组长度时，表示已经生成了一个排列
            // 将排列加入结果列表
            result.add(sb.toString());
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (used[i] || (i > 0 && arr[i] == arr[i - 1] && !used[i - 1])) {
                    // 如果字符已经被使用过，或者当前字符与前一个字符相同且前一个字符未被使用，则跳过
                    continue;
                }

                // 添加字符到已生成的排列
                sb.append(arr[i]);
                // 标记字符为已使用
                used[i] = true;

                // 递归处理下一个索引
                permutationHelper(arr, index + 1, sb, result, used);

                // 移出已生成的排列的最后一个字符
                sb.deleteCharAt(sb.length() - 1);
                // 标记字符为未使用
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutation solution = new Permutation();

        String str = "abc";
        ArrayList<String> result = solution.permutation(str);

        for (String s : result) {
            System.out.println(s);
        }
    }

}
