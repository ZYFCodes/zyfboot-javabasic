package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Map;

/**
 * 描述：Multimap系列接口集合 和Multiset很相似 可以理解成Multiset的map版本 对应的实现将上面的对应map改为Multimap
 * Multimap主要是为了一个键映射到多个值。换句话说，Multimap是把键映射到任意多个值的一般方式。
 * 可以和传统的缓存Map<K, Collection<V>>进行对比
 *
 * @author yanfengzhang
 * @date 2019-12-24 19:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MultimapTest {
    @Test
    public void testMultimap() {
        Multimap<Integer, String> multimap = HashMultimap.create();
        /**转换成Map<K, Collection<V>>格式 且对转换后的map做操作会影响原有的Multimap*/
        //multimap.asMap();
        /**添加键到单个值的映射*/
        multimap.put(1, "a");
        /**添加多个值的映射*/
        multimap.putAll(2, Lists.newArrayList("a", "b", "c", "d"));
        multimap.put(1, "a");
        /**移除键到值的映射；如果有这样的键值并成功移除，返回true。*/
        multimap.remove(2, "b");
        /**移除一个key所有的映射值*/
        //multimap.removeAll(1);
        /**替换原有的映射值集合*/
        //multimap.replaceValues(2, Lists.newArrayList("a", "b", "c", "d"));

        System.out.println(multimap);
    }

    @Test
    public void testMultimapBasic() {
        Multimap<String, String> multimap = ArrayListMultimap.create();

        // 添加多组键值对
        multimap.put("Fruits", "Apple");
        multimap.put("Fruits", "Banana");
        multimap.put("Vegetables", "Carrot");

        System.out.println("Multimap 内容: " + multimap);
        System.out.println("Fruits 分组: " + multimap.get("Fruits"));
        System.out.println("Vegetables 分组: " + multimap.get("Vegetables"));
    }

    @Test
    public void testMapToMultimap() {
        Map<String, String> map = Maps.newHashMap();
        map.put("A", "1");
        map.put("B", "2");
        map.put("A", "3");

        // 转换为 Multimap
        Multimap<String, String> multimap = Multimaps.forMap(map);

        System.out.println("普通 Map 内容: " + map);
        System.out.println("转换后的 Multimap 内容: " + multimap);
    }

    @Test
    public void testMultimapAdvanced() {
        Multimap<String, String> multimap = ArrayListMultimap.create();

        multimap.putAll("Fruits", Lists.newArrayList("Apple", "Banana", "Orange"));
        multimap.put("Fruits", "Grape");
        multimap.remove("Fruits", "Banana");

        System.out.println("更新后的 Multimap 内容: " + multimap);
    }



}
