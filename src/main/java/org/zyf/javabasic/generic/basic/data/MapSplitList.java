package org.zyf.javabasic.generic.basic.data;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/7/14  11:30
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 将Map切割成若干个Map
 *
 * @author zhangyanfeng
 * 6:57 PM 2020/2/8
 */
@Data
@Slf4j
public class MapSplitList<K, V> {

    private Map<K, V> map;
    private LinkedHashMap<K, V> chunkMap;
    private int limit;

    public MapSplitList(Map<V, V> map, int limit) {
        this.map= (Map<K, V>) map;
        this.limit=limit;
    }

    public MapSplitList(LinkedHashMap<K, V> map, int limit) {
        this.map= (LinkedHashMap<K, V>) map;
        this.limit=limit;
    }

    /**
     * 将HashMap切割成若干个HashMap
     * @return
     */
    public List<Map<K, V>> getResultForMap() {
        List<Map<K, V>> list = Lists.newArrayList();

        try {
            if (MapUtils.isEmpty(map)) {
                return list;
            }

            Map<K, V> tmpMap = Maps.newHashMap();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                tmpMap.put(entry.getKey(), entry.getValue());
                if (tmpMap.size() % limit == 0) {
                    list.add(tmpMap);
                    tmpMap = Maps.newHashMap();
                }
            }
            if (MapUtils.isNotEmpty(tmpMap)) {
                list.add(tmpMap);
            }
            return list;
        } catch (Exception e) {
            log.error("map:{} , limit :{} ,\n e :{}", this.map, this.limit, e);
            return Lists.newArrayList();
        }

    }

    /**
     * 将map 拆分成多个map
     * @return
     */
    public List<LinkedHashMap<K, V>> getResultForLinkedHashMap() {
        if (MapUtils.isEmpty(chunkMap) || limit <= 0) {
            return Lists.newArrayList();
        }
        Set<K> keySet = chunkMap.keySet();
        Iterator<K> iterator = keySet.iterator();
        int i = 1;
        List<LinkedHashMap<K, V>> total = new ArrayList<>();
        LinkedHashMap<K, V> tem = new LinkedHashMap<>();
        while (iterator.hasNext()) {
            K next = iterator.next();
            tem.put(next, chunkMap.get(next));
            if (i == limit) {
                total.add(tem);
                tem = new LinkedHashMap<>();
                i = 0;
            }
            i++;
        }
        if (!CollectionUtils.isEmpty(tem)) {
            total.add(tem);
        }
        return total;
    }

    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        LinkedHashMap<String, String> chunkMap = Maps.newLinkedHashMap();
        chunkMap.put("1", "1");
        chunkMap.put("2", "2");
        chunkMap.put("3", "3");
        chunkMap.put("4", "4");

        System.out.println("使用泛化对象处理");
        System.out.println(new MapSplitList<String, String>(map, 1).getResultForMap());
        System.out.println(new MapSplitList<String, String>(map, 2).getResultForMap());
        System.out.println(new MapSplitList<String, String>(map, 3).getResultForMap());
        System.out.println(new MapSplitList<String, String>(map, 4).getResultForMap());
        System.out.println(new MapSplitList<String, String>(map, 5).getResultForMap());

        System.out.println("使用自定义函数处理");
        System.out.println(new MapSplitList<String, String>(chunkMap, 1).getResultForMap());
        System.out.println(new MapSplitList<String, String>(chunkMap, 2).getResultForMap());
        System.out.println(new MapSplitList<String, String>(chunkMap, 3).getResultForMap());
        System.out.println(new MapSplitList<String, String>(chunkMap, 4).getResultForMap());
        System.out.println(new MapSplitList<String, String>(chunkMap, 5).getResultForMap());
    }
}

