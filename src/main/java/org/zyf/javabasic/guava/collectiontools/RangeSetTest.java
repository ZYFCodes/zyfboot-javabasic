package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：用来表述区间
 *
 * @author yanfengzhang
 * @date 2019-12-24 20:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RangeSetTest {
    @Test
    public void testRangeSet() {
        /**当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。*/
        RangeSet<Integer> rangeSet = TreeRangeSet.create();

        // {[1,10]}
        rangeSet.add(Range.closed(1, 10));
        //不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(11, 15));
        //相连区间: {[1,10], [11,20)}
        rangeSet.add(Range.closedOpen(15, 20));
        //空区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0));
        //分割[1, 10]; {[1,5], [10,10], [11,20)}
        rangeSet.remove(Range.open(5, 10));

        System.out.println(rangeSet);
    }
}
