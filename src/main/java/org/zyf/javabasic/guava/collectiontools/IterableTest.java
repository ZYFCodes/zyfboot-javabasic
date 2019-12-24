package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：对应集合类后面加上s
 *
 * @author yanfengzhang
 * @date 2019-12-23 18:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IterableTest {
    @Test
    public void testIterablesConcat() {
        //Iterables工具类连接两个Iterable（集合）
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6));

        System.out.println("第一个整数列表为：1，2，3");
        System.out.println("第一个整数列表为：4, 5, 6");
        System.out.println("Iterables工具类连接两个Iterable为：" + concatenated);
    }
}
