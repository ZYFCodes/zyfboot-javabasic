package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定两个字符串 s 和 t，判断它们是否是同构字符串。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * @date 2023/4/10  23:30
 */
public class IsomorphicStrings {

    /**
     * 同构字符串问题可以通过哈希表和字符映射来解决。具体步骤如下：
     * <p>
     * 创建两个哈希表，分别用于记录 s 到 t 的字符映射和 t 到 s 的字符映射。
     * 遍历字符串 s 和 t，对于每个字符，分别在两个哈希表中查找其对应的字符映射。
     * 如果两个哈希表中都能找到对应的字符映射，但不相同，则说明 s 和 t 不是同构字符串，直接返回 false。
     * 如果两个哈希表中只有其中一个哈希表中能找到对应的字符映射，则说明 s 和 t 不是同构字符串，直接返回 false。
     * 如果两个哈希表中都找不到对应的字符映射，则将当前字符的映射加入两个哈希表中。
     * 遍历结束后，没有发现不同的字符映射，则说明 s 和 t 是同构字符串，返回 true。
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        /*s 到 t 的字符映射*/
        Map<Character, Character> s2t = new HashMap<>();
        /*t 到 s 的字符映射*/
        Map<Character, Character> t2s = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                /*如果两个哈希表中都能找到对应的字符映射，但不相同，则说明 s 和 t 不是同构字符串，直接返回 false。*/
                return false;
            }
            if (!s2t.containsKey(x) && !t2s.containsKey(y)) {
                /*如果两个哈希表中都找不到对应的字符映射，则将当前字符的映射加入两个哈希表中。*/
                s2t.put(x, y);
                t2s.put(y, x);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "egg";
        String t1 = "add";
        /*true*/
        System.out.println(new IsomorphicStrings().isIsomorphic(s1, t1));

        String s2 = "foo";
        String t2 = "bar";
        /*false*/
        System.out.println(new IsomorphicStrings().isIsomorphic(s2, t2));

        String s3 = "paper";
        String t3 = "title";
        /*true*/
        System.out.println(new IsomorphicStrings().isIsomorphic(s3, t3));
    }
}
