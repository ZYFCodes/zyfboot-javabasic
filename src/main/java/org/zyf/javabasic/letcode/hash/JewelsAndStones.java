package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定字符串 J 代表石头中宝石的类型，和字符串 S 代表你拥有的石头。
 * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J中的字母不重复，J和S 中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * @date 2023/4/10  23:53
 */
public class JewelsAndStones {

    /**
     * 宝石与石头问题可以使用哈希表来解决。具体步骤如下：
     * <p>
     * 遍历字符串 J 中的每个字符，使用哈希表记录宝石的种类和数量。
     * 遍历字符串 S 中的每个字符，如果该字符是宝石，则更新计数器。
     * 返回计数器的值即为宝石的数量。
     */
    public int numJewelsInStones(String J, String S) {
        /*创建哈希表用于记录宝石的种类和数量*/
        Map<Character, Integer> map = new HashMap<>();

        /*遍历字符串 J 中的每个字符，记录宝石的种类和数量*/
        for (char c : J.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        /*计数器，用于记录宝石的数量*/
        int count = 0;

        /*遍历字符串 S 中的每个字符，如果该字符是宝石，则更新计数器*/
        for (char c : S.toCharArray()) {
            if (map.containsKey(c)) {
                count++;
            }
        }
        /*返回计数器的值即为宝石的数量*/
        return count;
    }

    public static void main(String[] args) {
        String J = "aA";
        String S = "aAAbbbb";
        int result = new JewelsAndStones().numJewelsInStones(J, S);
        /*Output: 3*/
        System.out.println(result);
    }
}
