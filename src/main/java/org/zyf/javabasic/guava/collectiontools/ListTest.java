package org.zyf.javabasic.guava.collectiontools;

import com.google.common.base.Joiner;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;

/**
 * 描述：对应集合类后面加上s
 *
 * @author yanfengzhang
 * @date 2019-12-20 15:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListTest {

    @Test
    public void testReverseList() {
        List<String> originalList = Lists.newArrayList("z", "y", "f");
        //反转List
        List<String> reverseList = Lists.reverse(originalList);

        System.out.println("原列表为：" + originalList);
        System.out.println("反转列表为：" + reverseList);
    }

    @Test
    public void testSplitSize() {
        List<String> originalList = Lists.newArrayList("z", "y", "f", "uu");
        //反转List
        List<List<String>> reverseList = Lists.partition(originalList, 3);

        System.out.println("原列表为：" + originalList);
        System.out.println("指定大小分割列表为：" + reverseList);
    }

    @Test
    public void testListToString() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", null, "Tom");

        // 跳过 null 值
        String resultSkipNulls = Joiner.on(",").skipNulls().join(names);

        // 将 null 替换为指定值
        String resultUseForNull = Joiner.on(",").useForNull("Default").join(names);

        System.out.println("跳过 null 值: " + resultSkipNulls);
        System.out.println("替换 null 值: " + resultUseForNull);
    }

    @Test
    public void testStringToList() {
        String input = "apple,,banana,,,orange,null";

        // 保留空字符串
        List<String> resultKeepEmpty = Splitter.on(",").splitToList(input);

        // 去除空字符串
        List<String> resultOmitEmpty = Splitter.on(",").omitEmptyStrings().splitToList(input);

        System.out.println("保留空字符串: " + resultKeepEmpty);
        System.out.println("去除空字符串: " + resultOmitEmpty);
    }

    @Test
    public void testRemoveDuplicates() {
        List<String> names = Lists.newArrayList("John", "Jane", "John", null,"Jane");

        // 保留 null，但去除重复
        List<String> uniqueKeepNull = Lists.newArrayList(Sets.newHashSet(names));

        // 去除重复和 null
        List<String> uniqueNoNull = ImmutableSet.copyOf(
                Iterables.filter(names, Predicates.not(Predicates.isNull()))
        ).asList();

        System.out.println("保留 null 的去重: " + uniqueKeepNull);
        System.out.println("去除 null 的去重: " + uniqueNoNull);
    }

}
