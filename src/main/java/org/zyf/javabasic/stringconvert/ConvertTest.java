package org.zyf.javabasic.stringconvert;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2019-12-31 11:30
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConvertTest {
    /**
     * list转换为字符串
     */
    @Test
    public void joinTest() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        String result = Joiner.on(",").join(names).trim();
        TestCase.assertEquals(result, "John,Jane,Adam,Tom");
    }


    /**
     * map转换为字符串
     */
    @Test
    public void whenConvertMapToString() {
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        String result = Joiner.on(" , ").withKeyValueSeparator(" = ")
                .join(salary);
        System.out.println(result);
    }

    /**
     * list转String，跳过null
     */
    @Test
    public void whenConvertListToStringAndSkipNull() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").skipNulls().join(names).trim();
        System.out.println(result);
        TestCase.assertEquals(result, "John,Jane,Adam,Tom");
    }

    /**
     * list转String，将null变成其他值
     */
    @Test
    public void whenUseForNull() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").useForNull("nameless").join(names);
        System.out.println(result);
        TestCase.assertEquals(result, "John,nameless,Jane,Adam,Tom");
    }

    /**
     * String to List
     */
    @Test
    public void whenCreateListFromString() {
        String input = "apple - banana - orange";
        List<String> result = Splitter.on("-").trimResults().omitEmptyStrings().splitToList(input);
        System.out.println(result);
        //TestCase.assertEquals(result, contains("apple", "banana", "orange"));
    }

    /**
     * String to Map
     */
    @Test
    public void whenCreateMapFromString() {
        String input = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(input);

        TestCase.assertEquals("first", result.get("John"));
        TestCase.assertEquals("second", result.get("Adam"));
    }

    /**
     * 多个字符进行分割
     */
    @Test
    public void whenSplitStringOnMultipleSeparator() {
        String input = "apple.banana,,orange,,.";
        List<String> result = Splitter.onPattern("[.|,]")
                .omitEmptyStrings()
                .splitToList(input);
        System.out.println(result);
    }

    /**
     * 每隔多少字符进行分割
     */
    @Test
    public void whenSplitStringOnSpecificLength() {
        String input = "Hello world";
        List<String> result = Splitter.fixedLength(3).splitToList(input);
        System.out.println(result);
    }

    /**
     * 限制分割多少字后停止
     */
    @Test
    public void whenLimitSplitting() {
        String input = "a,b,c,d,e";
        List<String> result = Splitter.on(",")
                .limit(4)
                .splitToList(input);

        TestCase.assertEquals(4, result.size());
        System.out.println(result);
    }
}
