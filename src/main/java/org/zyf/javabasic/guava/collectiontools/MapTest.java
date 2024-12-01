package org.zyf.javabasic.guava.collectiontools;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Map;

/**
 * 描述：对应集合类后面加上s
 *
 * @author yanfengzhang
 * @date 2019-12-20 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapTest {
    Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
    Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 4);

    @Test
    public void testEntriesInCommon() {
        /**Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象，把不同点的维恩图分解*/
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println("第一个map具体内容为：" + left);
        System.out.println("第二个map具体内容为：" + right);
        System.out.println("两个map中键值对都相等的有：" + diff.entriesInCommon());
    }

    @Test
    public void testEntriesDiffering() {
        /**Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象，把不同点的维恩图分解*/
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println("第一个map具体内容为：" + left);
        System.out.println("第二个map具体内容为：" + right);
        System.out.println("两个map中键值对不相等的有：" + diff.entriesDiffering());
    }

    @Test
    public void testEntriesOnlyOnLeft() {
        /**Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象，把不同点的维恩图分解*/
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println("第一个map具体内容为：" + left);
        System.out.println("第二个map具体内容为：" + right);
        System.out.println("两个map中找到只存在于左边的有：" + diff.entriesOnlyOnLeft());
    }

    @Test
    public void testEntriesOnlyOnRight() {
        /**Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象，把不同点的维恩图分解*/
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println("第一个map具体内容为：" + left);
        System.out.println("第二个map具体内容为：" + right);
        System.out.println("两个map中找到只存在于右边的有：" + diff.entriesOnlyOnRight());
    }

    @Test
    public void testMapToString() {
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);

        // 使用 key-value 分隔符将 Map 转为字符串
        String result = Joiner.on(", ").withKeyValueSeparator(" = ").join(salary);

        System.out.println("原 Map 内容: " + salary);
        System.out.println("转换为字符串结果: " + result);
    }

    @Test
    public void testStringToMap() {
        String input = "John=Developer, Jane=Manager";

        // 根据分隔符解析字符串为 Map
        Map<String, String> result = Splitter.on(", ")
                .withKeyValueSeparator("=")
                .split(input);

        System.out.println("原字符串内容: " + input);
        System.out.println("转换为 Map 内容: " + result);
    }


}
