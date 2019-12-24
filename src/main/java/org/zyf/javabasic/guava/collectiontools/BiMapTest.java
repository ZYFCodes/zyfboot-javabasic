package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：BiMap系列接口集合---为了解决实现键值对的双向映射需要维护两个单独的map的问题
 *
 * @author yanfengzhang
 * @date 2019-12-24 19:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BiMapTest {
    @Test
    public void testBiMap() {
        BiMap<Integer, String> userId = HashBiMap.create();
        userId.put(1, "tom");

        System.out.println("实际map存储数据为：" + userId);
        System.out.println("正常根据key查找value---根据1返回：" + userId.get(1));
        System.out.println("键值对的双向映射--->根据value查看key值---根据tom返回：" + userId.inverse().get("tom"));
    }
}
