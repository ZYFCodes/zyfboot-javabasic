package org.zyf.javabasic.guava.collectiontools;

import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述：对应集合类后面加上s
 *
 * @author yanfengzhang
 * @date 2019-12-20 15:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetTest {

    Set<String> set1 = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
    Set<String> set2 = ImmutableSet.of("two", "three", "five", "seven");

    @Test
    public void testUnionSet() {
        Set<String> unionset = Sets.union(set1, set2);

        System.out.println("集合1为：" + set1);
        System.out.println("集合2为：" + set2);
        System.out.println("联合两个集合为：" + unionset);
    }

    @Test
    public void testIntersectionSet() {
        Set<String> intersectionSet = Sets.intersection(set1, set2);

        System.out.println("集合1为：" + set1);
        System.out.println("集合2为：" + set2);
        System.out.println("两个集合交集为：" + intersectionSet);
    }

    @Test
    public void testSetIntersection() {
        Set<String> set1 = Sets.newHashSet("Apple", "Banana", "Orange");
        Set<String> set2 = Sets.newHashSet("Banana", "Orange", "Grape");

        // 计算交集
        Set<String> intersection = Sets.intersection(set1, set2);

        System.out.println("集合 1: " + set1);
        System.out.println("集合 2: " + set2);
        System.out.println("交集: " + intersection);
    }

    @Test
    public void testSetUnion() {
        Set<String> set1 = Sets.newHashSet("Apple", "Banana");
        Set<String> set2 = Sets.newHashSet("Banana", "Orange", "Grape");

        // 计算并集
        Set<String> union = Sets.union(set1, set2);

        System.out.println("集合 1: " + set1);
        System.out.println("集合 2: " + set2);
        System.out.println("并集: " + union);
    }

    @Test
    public void testSetDifference() {
        Set<String> set1 = Sets.newHashSet("Apple", "Banana", "Orange");
        Set<String> set2 = Sets.newHashSet("Banana", "Orange", "Grape");

        // 计算差集
        Set<String> difference = Sets.difference(set1, set2);

        System.out.println("集合 1: " + set1);
        System.out.println("集合 2: " + set2);
        System.out.println("差集 (set1 - set2): " + difference);
    }

    @Test
    public void testSetSubset() {
        Set<String> set1 = Sets.newHashSet("Apple", "Banana");
        Set<String> set2 = Sets.newHashSet("Apple", "Banana", "Orange");

        // 判断 set1 是否是 set2 的子集
        boolean isSubset = set2.containsAll(set1);

        System.out.println("集合 1: " + set1);
        System.out.println("集合 2: " + set2);
        System.out.println("集合 1 是否是集合 2 的子集: " + isSubset);
    }

    @Test
    public void testCharMatcherFeatures() {
        String input = "Hello 123 World! ABC-def_gh!@#";

        // 1. 基本匹配器
        System.out.println("1. 基本匹配器");
        System.out.println("是否包含字符 'H': " + CharMatcher.is('H').matchesAnyOf(input)); // true
        System.out.println("保留 'o' 字符: " + CharMatcher.is('o').retainFrom(input)); // "oo"
        System.out.println("移除 'o' 字符: " + CharMatcher.is('o').removeFrom(input)); // "Hell 123 Wrld! ABC-def_gh!@#"

        // 2. 范围匹配器
        System.out.println("\n2. 范围匹配器");
        System.out.println("保留小写字母: " + CharMatcher.inRange('a', 'z').retainFrom(input)); // "elloorldefgh"
        System.out.println("移除大写字母: " + CharMatcher.inRange('A', 'Z').removeFrom(input)); // "ello 123 -def_gh!@#"

        // 3. 字符类型匹配器
        System.out.println("\n3. 字符类型匹配器");
        System.out.println("保留数字: " + CharMatcher.digit().retainFrom(input)); // "123"
        System.out.println("移除空白字符: " + CharMatcher.whitespace().removeFrom(input)); // "Hello123World!ABC-def_gh!@#"
        System.out.println("替换非ASCII字符为'_': " + CharMatcher.ascii().negate().replaceFrom(input, '_')); // "Hello 123 World! ABC-def_gh!@#"

        // 4. 自定义组合匹配器
        System.out.println("\n4. 自定义组合匹配器");
        System.out.println("保留数字或大写字母: " + CharMatcher.digit().or(CharMatcher.inRange('A', 'Z')).retainFrom(input)); // "123ABC"
        System.out.println("移除小写字母和数字: " + CharMatcher.javaLetter().and(CharMatcher.inRange('a', 'z')).or(CharMatcher.digit()).removeFrom(input)); // "Hello World! ABC-_gh!@#"

        // 5. 常用方法
        System.out.println("\n5. 常用方法");
        // retainFrom：保留匹配字符
        System.out.println("保留数字: " + CharMatcher.digit().retainFrom(input)); // "123"
        // removeFrom：移除匹配字符
        System.out.println("移除标点: " + CharMatcher.is('_').or(CharMatcher.is('-')).removeFrom(input)); // "Hello 123 World! ABCdefgh!@#"
        // replaceFrom：将匹配字符替换为指定字符
        System.out.println("将数字替换为 '*': " + CharMatcher.digit().replaceFrom(input, '*')); // "Hello *** World! ABC-def_gh!@#"
        // trimFrom：从两端移除匹配字符
        String trimInput = "__Hello__";
        System.out.println("移除两端下划线: " + CharMatcher.is('_').trimFrom(trimInput)); // "Hello"
        // collapseFrom：将连续匹配字符折叠成一个指定字符
        String collapseInput = "Hello     World";
        System.out.println("折叠多余空格: " + CharMatcher.whitespace().collapseFrom(collapseInput, ' ')); // "Hello World"
        // countIn：统计匹配字符的数量
        System.out.println("数字数量: " + CharMatcher.digit().countIn(input)); // 3

        // 6. 清理与验证
        System.out.println("\n6. 清理与验证");
        String specialInput = "H\u200Bello\u200C World!";
        System.out.println("清理控制字符: " + CharMatcher.javaIsoControl().removeFrom(specialInput)); // "Hello World!"
        System.out.println("是否全为数字: " + CharMatcher.digit().matchesAllOf("12345")); // true
        System.out.println("是否包含非法字符: " + CharMatcher.javaLetter().or(CharMatcher.digit()).negate().matchesAnyOf(input)); // true

        // 7. 扩展示例：过滤字符串流
        System.out.println("\n7. 过滤字符串流");
        String[] words = {"hello", "123", "world!", "ABC"};
        String result = java.util.Arrays.stream(words)
                .map(word -> CharMatcher.javaLetter().retainFrom(word)) // 只保留字母
                .collect(Collectors.joining(", "));
        System.out.println("过滤后结果: " + result); // "hello, , world, ABC"
    }


}
