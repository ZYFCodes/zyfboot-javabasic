package org.zyf.javabasic.sensitive.base;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:24
 */
@Data
public class SensitiveInfo {

    private String businessIdentity;

    private volatile Map<Long, WordDomain> sensitiveWordIdMap = Maps.newHashMap();
    /**
     * 平台门店白名单
     */
    private volatile Set<Long> sensitiveWordPlatformPoiWhiteList = Sets.newHashSet();

    /**
     * 平台品牌白名单
     */
    private volatile Set<Long> sensitiveWordPlatformBrandWhiteList = Sets.newHashSet();

    /**
     * 单个敏感词对应的【品牌维度】白名单
     */
    private volatile HashMultimap<Long, Long> sensitiveWordBrandWhiteListMap = HashMultimap.create();
    /**
     * 单个敏感词分类对应的【品牌维度】白名单
     */
    private volatile HashMultimap<Long, Long> sensitiveCategoryBrandWhiteListMap = HashMultimap.create();

    /**
     * 单个敏感词对应的【商品后台类目维度】白名单
     */
    private volatile HashMultimap<Long, Long> sensitiveWordSpTagWhiteListMap = HashMultimap.create();
    /**
     * 单个敏感词分类对应的【商品后台类目维度】白名单
     */
    private volatile HashMultimap<Long, Long> sensitiveCategorySpTagWhiteListMap = HashMultimap.create();

    /**
     * 单个敏感词对应的【门店维度】白名单
     */
    private volatile HashMultimap<Long, Long> sensitiveWordPoiWhiteListMap = HashMultimap.create();
    /**
     * 单个敏感词分类对应的【门店维度】白名单
     */
    private volatile HashMultimap<Long, Long> sensitiveCategoryPoiWhiteListMap = HashMultimap.create();

    /**
     * 【排除门店】白名单
     * objectId : 敏感词Id 或者敏感词分类Id
     * objectType : objectType { SensitiveObjectTypeEnum}
     * outSideId: 门店品牌Id 或者后台关联类目的Id
     * dimension: 维度  { SensitiveWhiteListDimensionEnum}
     * obj:{objectId}:ot:{objectType}:out:{outSideId}:d:{dimension}
     */
    private volatile HashMultimap<String, Long> sensitiveExcludedPoiWhiteListMap = HashMultimap.create();


    public SensitiveInfo(String businessIdentity) {
        this.businessIdentity = businessIdentity;
    }

    private static final String EXCLUDE_LIST_STR = "obj:{objectId}:ot:{objectType}:out:{outSideId}:d:{dimension}";

    public static String formatExcludeListStr(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long outSideId, SensitiveWhiteListDimensionEnum dimensionEnum) {
        return EXCLUDE_LIST_STR.replace("{objectId}", String.valueOf(objectId))
                .replace("{objectType}", String.valueOf(objectTypeEnum.getValue()))
                .replace("{outSideId}", String.valueOf(outSideId))
                .replace("{dimension}", String.valueOf(dimensionEnum.getValue()));
    }
}
