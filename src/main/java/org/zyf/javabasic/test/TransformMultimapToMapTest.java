package org.zyf.javabasic.test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/24  18:30
 */
public class TransformMultimapToMapTest {

    public static <K, V> Map<K, List<V>> transformMultimapToMap(Multimap<K, V> multimap) {
        Map<K, List<V>> resultMap = Maps.newHashMap();

        for (Map.Entry<K, Collection<V>> entry : multimap.asMap().entrySet()) {
            resultMap.put(entry.getKey(), Lists.newArrayList(entry.getValue()));
        }

        return resultMap;
    }

    public static void main(String[] args) {
        Multimap<Long, Long> bgCategoryIdsByWmPoiTagIds = HashMultimap.create();
        bgCategoryIdsByWmPoiTagIds.put(1L, 1L);
        bgCategoryIdsByWmPoiTagIds.put(1L, 2L);
        bgCategoryIdsByWmPoiTagIds.put(1L, 2L);
        bgCategoryIdsByWmPoiTagIds.put(1L, 3L);
        bgCategoryIdsByWmPoiTagIds.put(2L, 1L);

        Map<Long, List<Long>> bgCategoryIdsMap = transformMultimapToMap(bgCategoryIdsByWmPoiTagIds);
        System.out.println(bgCategoryIdsByWmPoiTagIds);
        System.out.println(bgCategoryIdsMap);


        Collection<Long> eachBgCategoryIds = bgCategoryIdsByWmPoiTagIds.get(1L);
        List<Long> test = Lists.newArrayList();
        test.add(5L);
        eachBgCategoryIds.addAll(test);
        //eachBgCategoryIds.clear();
        System.out.println(eachBgCategoryIds);
    }
}
