package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;

/**
 * 描述：对应集合类后面加上s
 *
 * @author yanfengzhang
 * @date 2019-12-20 15:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListTest {

    @Test
    public void testReverseList() {
        List<String> originalList = Lists.newArrayList("z", "y", "f");
        //反转List
        List<String> reverseList = Lists.reverse(originalList);

        System.out.println("原列表为：" + originalList);
        System.out.println("反转列表为：" + reverseList);
    }

    @Test
    public void testSplitSize() {
        List<String> originalList = Lists.newArrayList("z", "y", "f", "uu");
        //反转List
        List<List<String>> reverseList = Lists.partition(originalList, 3);

        System.out.println("原列表为：" + originalList);
        System.out.println("指定大小分割列表为：" + reverseList);
    }


}
