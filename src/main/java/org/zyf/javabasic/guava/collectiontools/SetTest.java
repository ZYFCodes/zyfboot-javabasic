package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Set;

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


}
