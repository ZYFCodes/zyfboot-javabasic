package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 示例 1：输入：columnTitle = "A"        输出：1
 * 示例 2：输入：columnTitle = "AB”    输出：28
 * 示例 3：输入：columnTitle = "ZY"    输出：701
 * 示例 4：输入：columnTitle = "FXSHRXW"   输出：2147483647
 * <p>
 * 提示：
 * - 1 <= columnTitle.length <= 7
 * - columnTitle 仅由大写英文组成
 * - columnTitle 在范围 ["A", "FXSHRXW"] 内
 * @date 2023/7/9  22:58
 */
public class ExcelSheetColumnNumber {

    /**
     * 最优解法可以通过进制转换来实现Excel表列序号的计算。
     * 1. 从右向左遍历列名称的每个字符，将每个字符对应的数字值乘以 26 的幂，然后累加到结果中。
     * 2. 由于 Excel 表格中的列名称是26进制的表示，所以从右向左遍历可以保证按权重从小到大计算。
     * 3. 计算完成后得到的结果即为对应的列序号。
     * 例如，对于 "AB"，从右向左遍历可以得到 A=1 和 B=2，计算结果为 1 * 26^1 + 2 * 26^0 = 26 + 2 = 28，得到正确的列序号。
     * 该算法的时间复杂度为 O(n)，其中 n 是列名称的长度。因为需要遍历列名称的每个字符进行计算。
     * 而空间复杂度为 O(1)，只需要常数级的额外空间来保存计算结果。
     */
    public static int titleToNumber(String columnTitle) {
        int result = 0;
        // 幂，初始为0
        int power = 0;

        // 从右向左遍历列名称的每个字符
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            char ch = columnTitle.charAt(i);
            // 将字符对应的数字值计算出来
            int digit = ch - 'A' + 1;

            // 将每个字符对应的数字值乘以 26 的幂，并累加到结果中
            result += digit * Math.pow(26, power);
            // 幂加1，以便计算下一位的权重
            power++;
        }

        return result;
    }

    public static void main(String[] args) {
        String columnTitle1 = "A";
        String columnTitle2 = "AB";
        String columnTitle3 = "ZY";
        String columnTitle4 = "FXSHRXW";

        System.out.println("Input: " + columnTitle1 + " Output: " + titleToNumber(columnTitle1));
        System.out.println("Input: " + columnTitle2 + " Output: " + titleToNumber(columnTitle2));
        System.out.println("Input: " + columnTitle3 + " Output: " + titleToNumber(columnTitle3));
        System.out.println("Input: " + columnTitle4 + " Output: " + titleToNumber(columnTitle4));
    }
}
