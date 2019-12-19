package org.zyf.javabasic.guava.basictools;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;

/**
 * 描述：排序器及对比器
 *
 * @author yanfengzhang
 * @date 2019-12-19 19:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderingTest {

    /**
     * 功能描述：对可排序类型做自然排序，如数字按大小，日期按先后排序
     *
     * @author yanfengzhang
     */
    @Test
    public void testNatural() {
        List preSortList = Lists.newArrayList(2, 9, 6, 6, 6);
        List sortList = Ordering.natural().sortedCopy(preSortList);

        System.out.println("待排序整数数组为：" + preSortList);
        System.out.println("自然排序后整数数组为：" + sortList);
    }

    /**
     * 功能描述：获取可迭代对象中最大的k个元素
     *
     * @author yanfengzhang
     */
    @Test
    public void testGetGreatest() {
        List originalList = Lists.newArrayList("dffc", "tgh", "axf", "fvb");
        List resultList = Ordering.natural().greatestOf(originalList, 3);

        System.out.println("原始数组(字符串数组)为：" + originalList);
        System.out.println("获取自然排序后(字符串数组)的前三个最大的：" + resultList);

        List originalListoo = Lists.newArrayList(3, 2, 5, 3, 7, 0, 4, 6, 8, 71, 45);
        List resultListoo = Ordering.natural().greatestOf(originalListoo, 3);

        System.out.println("原始数组(整数数组)为：" + originalListoo);
        System.out.println("获取自然排序后(整数数组)的前三个最大的：" + resultListoo);
    }

    /**
     * 功能描述：判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。
     *
     * @author yanfengzhang
     */
    @Test
    public void testIsOrdered() {
        List preSortList = Lists.newArrayList(2, 9, 6, 6, 6);
        Ordering.natural().isOrdered(preSortList);

        System.out.println("整数数组为：" + preSortList + ", 其是一个（允许有排序值相等的元素）已经排序的数组标志为：" + Ordering.natural().isOrdered(preSortList));
    }

    /**
     * 功能描述：判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。
     *
     * @author yanfengzhang
     */
    @Test
    public void testGetMinOrMax() {
        List preSortList = Lists.newArrayList(2, 9, 6, 6, 6);
        Ordering.natural().max(preSortList);
        Ordering.natural().min(preSortList);

        System.out.println("整数数组为：" + preSortList);
        System.out.println("其中最大的数是：" + Ordering.natural().max(preSortList));
        System.out.println("其中最小的数是：" + Ordering.natural().min(preSortList));

        System.out.println("有两个数为3和7，其中最大的是：" + Ordering.natural().max(3, 7) + ", 最小的数是：" + Ordering.natural().min(3, 7));
        System.out.println("有两个数为35,67,78,25，其中最大的是：" + Ordering.natural().max(35, 67, 78, 25) + ", 最小的数是：" + Ordering.natural().min(35, 67, 78, 25));
    }
}
