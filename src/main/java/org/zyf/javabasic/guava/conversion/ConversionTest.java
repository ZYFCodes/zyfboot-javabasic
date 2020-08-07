package org.zyf.javabasic.guava.conversion;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description guava处理字符串与List之间，字符串与map之间的转换
 * @date 2020/8/5  19:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversionTest {

    @Test
    public void testListToString() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", null, "Tom");

        /*list转换为字符串, 跳过null*/
        String result2 = Joiner.on(",").skipNulls().join(names);
        /*list转换为字符串, 将null变成其他值*/
        String result3 = Joiner.on(",").useForNull("Zhangyanfeng").join(names);

        System.out.println("原列表内容为：" + names);
        System.out.println("操作（list转换为字符串, 跳过null）结果为：" + result2);
        System.out.println("操作（list转换为字符串, 将null变成其他值）结果为：" + result3);
    }

    @Test
    public void testStringToList(){
        String input = "apple,,banana,,,orange,null,";
        List<String> result1 = Splitter.on(",").trimResults().splitToList(input);
        List<String> result2 = Splitter.on(",").omitEmptyStrings().splitToList(input);

        System.out.println("原字符串内容为:" + input);
        System.out.println("将其转换为对应的列表(保留为空的内容)内容为:"+result1);
        System.out.println("将其转换为对应的列表(去除列表中的空内容)内容为:"+result2);
    }

    @Test
    public void testMapToString(){
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);

        /*Map转为字符串形式*/
        String result = Joiner.on(",").withKeyValueSeparator(" = ").join(salary);

        System.out.println("原字典内容为:"+salary);
        System.out.println("将其转换为字符串形式为:"+result);
    }

    @Test
    public void testStringToMap(){
        String input = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",").withKeyValueSeparator("=").split(input);

        System.out.println("原字符串内容为:"+input);
        System.out.println("将其转换为Map内容后为:"+result);
    }

    /**
     * 多个字符进行分割
     */
    @Test
    public void whenSplitStringOnMultipleSeparator_thenSplit() {
        String input = "apple.banana,,orange,,.";
        List<String> result = Splitter.onPattern("[.|,]").omitEmptyStrings().splitToList(input);
        System.out.println(result);
    }

    /**
     * 每隔多少字符进行分割
     */
    @Test
    public void whenSplitStringOnSpecificLength_thenSplit() {
        String input = "Hello world";
        List<String> result = Splitter.fixedLength(3).splitToList(input);
        System.out.println(result);
    }

    /**
     * 限制分割多少字后停止
     */
    @Test
    public void whenLimitSplitting_thenLimited() {
        String input = "a,b,c,d,e";
        List<String> result = Splitter.on(",").limit(3).splitToList(input);

        System.out.println(result);
    }

}
