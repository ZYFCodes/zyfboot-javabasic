package org.zyf.javabasic.generic.basic.data;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/7/14  11:30
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
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
 * @author jeb_lin
 * 6:57 PM 2020/2/8
 */
@AllArgsConstructor
@Data
@Slf4j
public class MapSplitList<K, V> {

    private Map<K, V> map;
    private int limit;

    public List<Map<K, V>> getList() {
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
     *
     * @param chunkMap 被拆的 map
     * @param chunkNum 每段的大小
     * @param <k>      map 的 key类 型
     * @param <v>      map 的value 类型
     * @return List
     */
    public static <k, v> List<LinkedHashMap<k, v>> mapChunk(LinkedHashMap<k, v> chunkMap, int chunkNum) {
        if (chunkMap == null || chunkNum <= 0) {
            List<LinkedHashMap<k, v>> list = new ArrayList<>();
            list.add(chunkMap);
            return list;
        }
        Set<k> keySet = chunkMap.keySet();
        Iterator<k> iterator = keySet.iterator();
        int i = 1;
        List<LinkedHashMap<k, v>> total = new ArrayList<>();
        LinkedHashMap<k, v> tem = new LinkedHashMap<>();
        while (iterator.hasNext()) {
            k next = iterator.next();
            tem.put(next, chunkMap.get(next));
            if (i == chunkNum) {
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
        System.out.println(new MapSplitList<String, String>(map, 1).getList());
        System.out.println(new MapSplitList<String, String>(map, 2).getList());
        System.out.println(new MapSplitList<String, String>(map, 3).getList());
        System.out.println(new MapSplitList<String, String>(map, 4).getList());
        System.out.println(new MapSplitList<String, String>(map, 5).getList());

        System.out.println("使用自定义函数处理");
        System.out.println(mapChunk(chunkMap, 1));
        System.out.println(mapChunk(chunkMap, 2));
        System.out.println(mapChunk(chunkMap, 3));
        System.out.println(mapChunk(chunkMap, 4));
        System.out.println(mapChunk(chunkMap, 5));
    }
}

