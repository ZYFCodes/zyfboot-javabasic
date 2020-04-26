package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：RangeMap类除了上述区间形容外 描述了”不相交的、非空的区间”到特定值的映射
 * 和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。
 * Range区间也是Guava提供的一种新类型
 *
 * @author yanfengzhang
 * @date 2019-12-24 20:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RangeMapTest {
    @Test
    public void testRangemap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();

        /**{[1,10] => "foo"}*/
        rangeMap.put(Range.closed(1, 10), "foo");
        /**{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}*/
        rangeMap.put(Range.open(3, 6), "bar");
        /**{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}*/
        rangeMap.put(Range.open(10, 20), "foo");
        /**{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}*/
        rangeMap.remove(Range.closed(5, 11));

        System.out.println(rangeMap);
    }
}
