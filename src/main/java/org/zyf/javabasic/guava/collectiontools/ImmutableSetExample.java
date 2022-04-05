package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.ImmutableSet;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.awt.*;

/**
 * 描述：不可变集合---静态集合不可修改
 *
 * @author yanfengzhang
 * @date 2019-12-19 20:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImmutableSetExample {
    /**
     * 以set为例
     */
    ImmutableSet<String> zyf1 = ImmutableSet.of("a", "b", "c");
    ImmutableSet<Integer> zyf2 = ImmutableSet.of(1, 2, 3);
    ImmutableSet<Integer> defensiveCopy = ImmutableSet.copyOf(zyf2);
    ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet.<Color>builder().add(new Color(0, 190, 255)).build();

}
