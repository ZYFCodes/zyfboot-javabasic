package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：Multiset接口系列集合：提供一个可以统计元素插入重复次数的无序集合
 *
 * @author yanfengzhang
 * @date 2019-12-24 19:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MultisetTest {

    @Test
    public void testMultiset() {
        Multiset<String> multiSet = HashMultiset.create();
        /**插入字符串"aa"2次*/
        multiSet.add("aa", 3);
        /**删除n次*/
        multiSet.remove("aa", 1);
        /**获取不重复的元素只获取到一个aa*/
        multiSet.elementSet();
        /**统计aa插入的次数*/
        multiSet.count("aa");
        /**和Map的entrySet类似 返回set类型的entry集 支持getElement()和getCount()方法*/
        multiSet.entrySet();
        /**给定元素固定次数*/
        multiSet.setCount("aa", 5);
        /**返回集合元素的总个数(重复的也记录 若想去重使用countMap.elementSet().size())*/
        multiSet.size();
        System.out.println(multiSet);
    }

}
