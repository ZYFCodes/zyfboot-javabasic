package org.zyf.javabasic.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.Getter;
import org.assertj.core.util.Lists;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/12  18:09
 */
public class WmPoiTagTest {

    public static void main(String[] args) {

        Map<Long, Set<WmPoiTagAndTree>> map = Maps.newHashMap();
        Set<WmPoiTagAndTree> wmPoiTagAndTrees = Sets.newHashSet();
        WmPoiTagAndTree wmPoiTagAndTree = new WmPoiTagAndTree();
        List<WmPoiCateDic> tagTree = Lists.newArrayList();
        WmPoiCateDic wmPoiCateDic1 = new WmPoiCateDic();
        wmPoiCateDic1.setId(1);
        WmPoiCateDic wmPoiCateDic2 = new WmPoiCateDic();
        wmPoiCateDic2.setId(2);
        tagTree.add(wmPoiCateDic1);
        tagTree.add(wmPoiCateDic2);
        tagTree.add(wmPoiCateDic1);
        wmPoiTagAndTree.setIs_primary(1);
        wmPoiTagAndTree.setTagTree(tagTree);

        WmPoiTagAndTree wmPoiTagAndTree1 = new WmPoiTagAndTree();
        List<WmPoiCateDic> tagTree1 = Lists.newArrayList();
        WmPoiCateDic wmPoiCateDic3 = new WmPoiCateDic();
        wmPoiCateDic1.setId(3);
        WmPoiCateDic wmPoiCateDic4 = new WmPoiCateDic();
        wmPoiCateDic2.setId(4);
        tagTree1.add(wmPoiCateDic3);
        tagTree1.add(wmPoiCateDic4);
        tagTree1.add(wmPoiCateDic4);
        wmPoiTagAndTree1.setIs_primary(2);
        wmPoiTagAndTree1.setTagTree(tagTree);


        wmPoiTagAndTrees.add(wmPoiTagAndTree);
        wmPoiTagAndTrees.add(wmPoiTagAndTree1);
        map.put(12L,wmPoiTagAndTrees);


        Map<Long, WmPoiTag> resultMap = Maps.newHashMap();
        Set<WmPoiTagAndTree> tagAndTreeSet;
        Set<Long> tagIds;
        WmPoiTag wmPoiTag;
        for (Map.Entry<Long, Set<WmPoiTagAndTree>> entry : map.entrySet()) {
            wmPoiTag = new WmPoiTag();
            tagAndTreeSet = entry.getValue();

            for (WmPoiTagAndTree tag : tagAndTreeSet) {
                if (tag.getIs_primary() != 1) {
                    tagIds = wmPoiTag.secondTagIdSet;
                } else {
                    tagIds = wmPoiTag.primaryTagIdSet;
                }
                List<WmPoiCateDic> tagTreea = tag.getTagTree();
                for (WmPoiCateDic cate : tagTreea) {
                    tagIds.add(cate.getId());
                }
            }
            resultMap.put(entry.getKey(), wmPoiTag);
        }

        System.out.println(resultMap);
        System.out.println(JSONObject.toJSONString(resultMap.get(12L)));
    }

    public static class WmPoiTag {
        /**
         * 主营品类
         */
        @Getter
        private LinkedHashSet<Long> primaryTagIdSet;
        /**
         * 辅营品类
         */
        @Getter
        private LinkedHashSet<Long> secondTagIdSet;
        /**
         * 全部经营品类
         */
        private Set<Long> allTagIdSet;

        private WmPoiTag() {
            primaryTagIdSet = Sets.newLinkedHashSet();
            secondTagIdSet = Sets.newLinkedHashSet();
        }

        // TODO 增加对象校验：如果这个门店没有主营品类，明确返回异常信息，避免再有人问，浪费自己的时间.

        private boolean isPrimaryTagId(long wmPoiTagId) {
            if (primaryTagIdSet == null) {
                return false;
            }

            return primaryTagIdSet.contains(wmPoiTagId);
        }

        /**
         * 获取全部相关门店经营品类，返回结果不保证有序。
         *
         * @return
         */
        public synchronized Set<Long> getAllTagIdSet() {
            if (allTagIdSet != null) {
                return allTagIdSet;
            }

            allTagIdSet = Sets.newHashSet();
            allTagIdSet.addAll(primaryTagIdSet);
//            if (productValidatorMccSwitchConfig.getBoolean("product_validator_overrange_operation_verify_contains_second_wmpoitag", true)) {
//                allTagIdSet.addAll(secondTagIdSet);
//            }
            return allTagIdSet;
        }
    }

    @Data
    public static class WmPoiTagAndTree{
        public long id;
        public long poi_id;
        public long wm_poi_id;
        public long tag_id;
        public int ctime;
        public int utime;
        public int valid;
        public long wm_poi_first_tag_dic_id;
        public int is_primary;
        public long dp_tag_id;
        public List<WmPoiCateDic> tagTree;
    }

    @Data
    public static class WmPoiCateDic{
        public long id;
        public long poi_id;
        public long wm_poi_id;
        public long tag_id;
        public int ctime;
        public int utime;
        public int valid;
        public long wm_poi_first_tag_dic_id;
        public int is_primary;
        public long dp_tag_id;
        public List<WmPoiCateDic> tagTree;
    }


}
