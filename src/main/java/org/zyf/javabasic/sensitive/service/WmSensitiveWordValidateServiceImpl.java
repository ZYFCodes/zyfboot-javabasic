package org.zyf.javabasic.sensitive.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.sensitive.ValidatePair;
import org.zyf.javabasic.sensitive.base.SensitiveInfo;
import org.zyf.javabasic.sensitive.base.SensitiveObjectTypeEnum;
import org.zyf.javabasic.sensitive.base.SensitiveWhiteListDimensionEnum;
import org.zyf.javabasic.sensitive.base.ValidateResult;
import org.zyf.javabasic.sensitive.base.WordDomain;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  21:17
 */
@Slf4j
@Component
public class WmSensitiveWordValidateServiceImpl implements WmSensitiveWordWhiteListService {

    //    @Autowired
//    private PoiService poiService;
    @Autowired
    private BusinessIdentityService businessIdentityService;
//    @Autowired
//    private PoiBrandService poiBrandService;

    @Override
    public boolean isWhiteListBySensitiveWord(ValidateResult validateResult, ValidatePair pair, Long wmPoiId, Long spTagId, Long platformBrandId) {
        String sensitiveWord = validateResult.getSensitive();
        WordDomain wordDomain = validateResult.getWordDomain();
        if (null == wordDomain) {
            return false;
        }
        Map<String, String> logMap = Maps.newHashMap();
//        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.NO_HIT.getValue()));
//        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SENSITIVE_TYPE.getName(), String.valueOf(WhiteListHitSensitiveTypeEnum.NO_HIT.getValue()));
        boolean flag = isWhiteListBySensitiveWordId(logMap, wordDomain.getId(), wmPoiId, spTagId, platformBrandId);
        // 只有命中了，才会进行hive记录
//        if (flag || ConfigUtil.isPrintForDebug()) {
//            logMap.put(WhiteListMatchLoggerFieldEnum.CHECK_CONTENT.getName(), pair.getContent());
//            logMap.put(WhiteListMatchLoggerFieldEnum.CHECK_FIELD.getName(), String.valueOf(pair.getField()));
//            logMap.put(WhiteListMatchLoggerFieldEnum.VALIDATE_RULE.getName(), String.valueOf(validateResult.getRule().getCode()));
//            logMap.put(WhiteListMatchLoggerFieldEnum.SKU_ID.getName(), Tracer.getContext(WhiteListMatchLoggerFieldEnum.SKU_ID.getName()));
//            logMap.put(WhiteListMatchLoggerFieldEnum.SPU_ID.getName(), String.valueOf(spTagId));
//            logMap.put(WhiteListMatchLoggerFieldEnum.WM_POI_ID.getName(), String.valueOf(wmPoiId));
//            logMap.put(WhiteListMatchLoggerFieldEnum.SP_TAG_ID.getName(), Tracer.getContext(WhiteListMatchLoggerFieldEnum.SP_TAG_ID.getName()));
//            logMap.put(WhiteListMatchLoggerFieldEnum.SENSITIVE_WORD.getName(), sensitiveWord);
//            logMap.put(WhiteListMatchLoggerFieldEnum.SENSITIVE_WORD_ID.getName(), String.valueOf(wordDomain.getId()));
//            logMap.put(WhiteListMatchLoggerFieldEnum.SENSITIVE_CAT_ID_FIRST.getName(), String.valueOf(wordDomain.getFirstCategoryId()));
//            logMap.put(WhiteListMatchLoggerFieldEnum.SENSITIVE_CAT_ID_SECOND.getName(), String.valueOf(wordDomain.getSecondCategoryId()));
//            LogUtil.logWhiteListMatchInfoTrace("", logMap);
//        }
        return flag;
    }

    /**
     * 是否命中白名单
     *
     * @param logMap
     * @param sensitiveWordId 敏感词id
     * @param wmPoiId         门店id
     * @param spTagId         商品关联类目id
     * @return true - 命中白名单；false-未命中白名单
     */
    @Override
    public boolean isWhiteListBySensitiveWordId(Map<String, String> logMap, Long sensitiveWordId, Long wmPoiId, Long spTagId, Long platformBrandId) {
        Instant begin = Instant.now();
        try {
            if (sensitiveWordId < 1L) {
                return false;
            }
            // ====== 平台门店白名单 ======
            if (isPlatformWmPoiIdInWhiteList(wmPoiId)) {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SENSITIVE_TYPE.getName(), String.valueOf(WhiteListHitSensitiveTypeEnum.NONE.getValue()));
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.PLATFORM_WHITELIST.getValue()));
                return true;
            }

            // 1.  ====== 验证敏感词 ======
            boolean flag = isWhiteListByMulitDimension(logMap, sensitiveWordId, SensitiveObjectTypeEnum.SENSITIVE_WORD, wmPoiId, spTagId, platformBrandId);
            if (flag) {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SENSITIVE_TYPE.getName(), String.valueOf(WhiteListHitSensitiveTypeEnum.SENSITIVE_WORD.getValue()));
                return true;
            }
            // 2. ======  验证敏感词分类 ======
            if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
                return false;
            }
            WordDomain wordDomain = businessIdentityService.getSensitiveInfoByTracer().get().getSensitiveWordIdMap().get(sensitiveWordId);
            if (null == wordDomain) {
                return false;
            }
            long firstCategoryId = wordDomain.getFirstCategoryId();
            flag = isWhiteListByMulitDimension(logMap, firstCategoryId, SensitiveObjectTypeEnum.SENSITIVE_CATEGORY, wmPoiId, spTagId, platformBrandId);
            if (flag) {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SENSITIVE_TYPE.getName(), String.valueOf(WhiteListHitSensitiveTypeEnum.SENSITIVE_CATEGORY_FIRST.getValue()));
                return true;
            }
            long secondCategoryId = wordDomain.getSecondCategoryId();
            flag = isWhiteListByMulitDimension(logMap, secondCategoryId, SensitiveObjectTypeEnum.SENSITIVE_CATEGORY, wmPoiId, spTagId, platformBrandId);
            if (flag) {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SENSITIVE_TYPE.getName(), String.valueOf(WhiteListHitSensitiveTypeEnum.SENSITIVE_CATEGORY_SECOND.getValue()));
                return true;
            }
            return false;
        } finally {
//            if (ConfigUtil.isPrintRequestLog()) {
//                log.info("isWhiteListBySensitiveWordId ,sensitiveWordId:{}, wmPoiId:{}, spTagId:{}, it cost:{}ms", sensitiveWordId, wmPoiId, spTagId, Duration.between(begin, Instant.now()).toMillis());
//            }
        }
    }

    @Override
    public boolean isWhiteListByMulitDimension(Map<String, String> logMap, Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long wmPoiId, Long spTagId, Long platformBrandId) {
        // 1. ====== 敏感词内的门店维度白名单 ======
        if (isSensitiveWmPoiIdInWhiteList(objectId, objectTypeEnum, wmPoiId)) {
//            logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.WM_POI.getValue()));
            return true;
        }
        // 2. ====== 后台商品关联类目spTagId白名单校验 ======
        if (spTagId > 0L) {
            if (isSensitiveSpTagInWhiteListByMultiLevel(logMap, objectId, objectTypeEnum, spTagId, wmPoiId)) {
                return true;
            }
        }

        // 3.  ====== 品牌白名单校验 ======
        /*传递了品牌信息，则按以下内容进行分析*/
        if (null != platformBrandId && platformBrandId > 0L && isPlatformWmBrandIdInWhiteList(platformBrandId)) {
            /*如果此时传递中没有门店信息，则直接按品牌平台处理*/
            if (null == wmPoiId || wmPoiId <= 0L) {
                return true;
            }
            /*判断是否为排除名单*/
            boolean isExcluded = isSensitiveExcludedWmPoiIdInWhiteList(0L, SensitiveObjectTypeEnum.SENSITIVE_WORD, platformBrandId, SensitiveWhiteListDimensionEnum.PLATFORM_BRAND, wmPoiId);
            /*如果被排除在外了，那么就不是品牌白名单了。如果没有被排除在外，那么就是品牌白名单*/
            if (isExcluded) {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.PLATFORM_BRAND_EXCLUDED.getValue()));
            } else {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.PLATFORM_BRAND_WHITELIST.getValue()));
                return true;
            }
        }

        /*无效门店不进行下面的判断*/
        if (wmPoiId <= 0L) {
            return false;
        }

//        Long brandId = poiBrandService.getPoiBrandId(wmPoiId);
        Long brandId = 33L;
//        logMap.put(WhiteListMatchLoggerFieldEnum.WM_POI_BRAND_ID.getName(), String.valueOf(brandId));
//
//        if (ConfigUtil.isPrintRequestLog()) {
//            log.info("brandCache, wmPoiId: {},brandId:{} ", wmPoiId, brandId);
//        }
        /*情况1：敏感词内的门店品牌维度白名单 (针对有效门店而言)*/
        if (null != brandId && brandId > 0L && isSensitiveBrandInWhiteList(objectId, objectTypeEnum, brandId)) {
            // 判断是否为排除名单
            boolean isExcluded = isSensitiveExcludedWmPoiIdInWhiteList(objectId, objectTypeEnum, brandId, SensitiveWhiteListDimensionEnum.WM_POI_BRAND, wmPoiId);
            // 如果被排除在外了，那么就不是品牌白名单了。
            // 如果没有被排除在外，那么就是品牌白名单
            if (isExcluded) {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.BRAND_EXCLUDED.getValue()));
            } else {
//                logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.BRAND.getValue()));
                return true;
            }
        }

        return false;
    }

    /**
     * 是否为平台白名单
     *
     * @param wmPoiId 门店id
     * @return true-门店在平台全局白名单中
     */
    @Override
    public boolean isPlatformWmPoiIdInWhiteList(Long wmPoiId) {
        if (wmPoiId <= 0L) {
            return false;
        }
//        if (!ConfigUtil.getIsPlatformWmPoiWhiteListSwitchOn()) {
//            return false;
//        }
        if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
            return false;
        }
        boolean flag = businessIdentityService.getSensitiveInfoByTracer().get().getSensitiveWordPlatformPoiWhiteList().contains(wmPoiId);
        if (flag) {
//            DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_PLATFORM_WHITE_LIST_LINE);
        }
        return flag;
    }

    /**
     * 是否为平台品牌白名单
     *
     * @param wmBrandId 品牌ID
     * @return true-是平台品牌
     */
    @Override
    public boolean isPlatformWmBrandIdInWhiteList(Long wmBrandId) {
        if (wmBrandId <= 0L) {
            return false;
        }
//        if (!ConfigUtil.getIsPlatformWmBrandWhiteListSwitchOn()) {
//            return false;
//        }
        if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
            return false;
        }
        boolean flag = businessIdentityService.getSensitiveInfoByTracer().get().getSensitiveWordPlatformBrandWhiteList().contains(wmBrandId);
        if (flag) {
//            DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_PLATFORM_BRAND_WHITE_LIST_LINE);
        }
        return flag;
    }


    /**
     * 是否为门店维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param wmPoiId        门店id
     * @return true - 门店纬度白名单 ； false - 非门店纬度白名单
     */
    @Override
    public boolean isSensitiveWmPoiIdInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long
            wmPoiId) {
        if (wmPoiId <= 0L || objectId <= 0L) {
            return false;
        }

//        if (!ConfigUtil.getIsWmPoiWhiteListSwitchOn()) {
//            return false;
//        }

        boolean flag = false;
        if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
            return false;
        }
        SensitiveInfo sensitiveInfo = businessIdentityService.getSensitiveInfoByTracer().get();
        Set<Long> poiWhites = sensitiveInfo.getSensitiveWordPoiWhiteListMap().get(objectId);
        Set<Long> categoryPoiWhites = sensitiveInfo.getSensitiveCategoryPoiWhiteListMap().get(objectId);
        if (objectTypeEnum == SensitiveObjectTypeEnum.SENSITIVE_WORD && CollectionUtils.isNotEmpty(poiWhites)) {
            flag = poiWhites.contains(wmPoiId);
        } else if (objectTypeEnum == SensitiveObjectTypeEnum.SENSITIVE_CATEGORY && CollectionUtils.isNotEmpty(categoryPoiWhites)) {
            flag = categoryPoiWhites.contains(wmPoiId);
        }

        if (flag) {
//            DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_POI_WHITE_LIST_LINE);
        }
        return flag;
    }

    /**
     * 是否为品牌维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param brandId        品牌id
     * @return true - 品牌纬度白名单 ； false - 非品牌纬度白名单
     */
    @Override
    public boolean isSensitiveBrandInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long brandId) {
        if (brandId <= 0L || objectId <= 0L) {
            return false;
        }
//        if (!ConfigUtil.getIsWmPoiBrandWhiteListSwitchOn()) {
//            return false;
//        }

        boolean flag = false;
        if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
            return false;
        }
        SensitiveInfo sensitiveInfo = businessIdentityService.getSensitiveInfoByTracer().get();
        Set<Long> brandWhites = sensitiveInfo.getSensitiveWordBrandWhiteListMap().get(objectId);
        Set<Long> categoryBrandWhites = sensitiveInfo.getSensitiveCategoryBrandWhiteListMap().get(objectId);
        if (objectTypeEnum == SensitiveObjectTypeEnum.SENSITIVE_WORD && CollectionUtils.isNotEmpty(brandWhites)) {
            flag = brandWhites.contains(brandId);
        } else if (objectTypeEnum == SensitiveObjectTypeEnum.SENSITIVE_CATEGORY && CollectionUtils.isNotEmpty(categoryBrandWhites)) {
            flag = categoryBrandWhites.contains(brandId);
        }

        if (flag) {
//                DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_BRAND_WHITE_LIST_LINE);
        }
        return flag;
    }

    /**
     * 是否为商品后台类目维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param spTagId        商品关联类目id
     * @return true - 商品后台类目维度白名单 ； false - 非商品后台类目维度白名单
     */
    @Override
    public boolean isSensitiveSpTagInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long spTagId) {
        if (spTagId <= 0L || objectId <= 0L) {
            return false;
        }
//            if (!ConfigUtil.getIsSpTagWhiteListSwitchOn()) {
//                return false;
//            }

        if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
            return false;
        }
        SensitiveInfo sensitiveInfo = businessIdentityService.getSensitiveInfoByTracer().get();
        Set<Long> spTagWhites = sensitiveInfo.getSensitiveWordSpTagWhiteListMap().get(objectId);
        Set<Long> categorySpTagWhites = sensitiveInfo.getSensitiveCategorySpTagWhiteListMap().get(objectId);
        boolean flag = false;
        if (objectTypeEnum == SensitiveObjectTypeEnum.SENSITIVE_WORD && CollectionUtils.isNotEmpty(spTagWhites)) {
            flag = spTagWhites.contains(spTagId);
        } else if (objectTypeEnum == SensitiveObjectTypeEnum.SENSITIVE_CATEGORY && CollectionUtils.isNotEmpty(categorySpTagWhites)) {
            flag = categorySpTagWhites.contains(spTagId);
        }
        if (flag) {
//                DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_SP_TAG_WHITE_LIST_LINE);
        }
        return flag;
    }

    /**
     * 后台商品关联类目spTagId白名单校验
     *
     * @param logMap         日志打印
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param spTagId        商品关联类目id
     * @param wmPoiId        门店id
     * @return true - 后台商品关联类目spTagId白名单 ； false - 非后台商品关联类目spTagId白名单
     */
    @Override
    public boolean isSensitiveSpTagInWhiteListByMultiLevel(Map<String, String> logMap, Long
            objectId, SensitiveObjectTypeEnum objectTypeEnum, Long spTagId, Long wmPoiId) {
        if (spTagId <= 0L || objectId <= 0L) {
            return false;
        }
        boolean flag;
        long parentId = spTagId;
        do {
            // 1. 先判断是否是类目白名单
            flag = isSensitiveSpTagInWhiteList(objectId, objectTypeEnum, parentId);
            if (flag) {
                // 2. 如果是，再判断是否是排除掉的名单
                // 判断是否为排除名单
                boolean isExcluded = isSensitiveExcludedWmPoiIdInWhiteList(objectId, objectTypeEnum, parentId, SensitiveWhiteListDimensionEnum.PRODUCT_SP_TAG, wmPoiId);
                // 如果被排除在外了，那么就不是关联类目白名单了
                // 如果没有被排除在外，那么就是关联类目白名单
                if (isExcluded) {
//                        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.SP_TAG_EXCLUDED.getValue()));
                } else {
//                        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.SP_TAG.getValue()));
//                        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SP_TAG_ID.getName(), String.valueOf(parentId));
                    return true;
                }
            }
            // 3. 3级找完找2级，2级找完找1级。
//                WmProductLibStandardProductCategory wmProductLibStandardProductCategory = FlushProductCategoryTask.spTagCategoryMap.get(parentId);
//                if (null == wmProductLibStandardProductCategory) {
//                    return false;
//                }
//                parentId = wmProductLibStandardProductCategory.getParentId();
        } while (parentId > 0L);

        return false;
    }

    /**
     * 是否为排除门店白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param outSideId      外部id，品牌id或者spTagid
     * @param dimensionEnum  SensitiveWhiteListDimensionEnum
     * @param wmPoiId        门店id
     * @return true - 排除门店白名单 ； false - 非排除门店白名单
     */
    @Override
    public boolean isSensitiveExcludedWmPoiIdInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long outSideId, SensitiveWhiteListDimensionEnum dimensionEnum, Long wmPoiId) {
        if (wmPoiId <= 0L) {
            return false;
        }
        /*如果不是全局白名单时，需要校验objectId*/
        if (dimensionEnum != SensitiveWhiteListDimensionEnum.PLATFORM_BRAND && objectId <= 0L) {
            return false;
        }
        if (dimensionEnum == SensitiveWhiteListDimensionEnum.WM_POI) {
            return false;
        }
//            if (!ConfigUtil.getIsExcludedWmPoiWhiteListSwitchOn()) {
//                return false;
//            }

        if (!businessIdentityService.getSensitiveInfoByTracer().isPresent()) {
            return false;
        }
        SensitiveInfo sensitiveInfo = businessIdentityService.getSensitiveInfoByTracer().get();
        String key = SensitiveInfo.formatExcludeListStr(objectId, objectTypeEnum, outSideId, dimensionEnum);
        Set<Long> excludedWmPoiIds = sensitiveInfo.getSensitiveExcludedPoiWhiteListMap().get(key);
        boolean flag = CollectionUtils.isNotEmpty(excludedWmPoiIds) && excludedWmPoiIds.contains(wmPoiId);

        if (flag) {
            if (dimensionEnum == SensitiveWhiteListDimensionEnum.WM_POI_BRAND) {
//                    DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_BRAND_EXCLUDED_WHITE_LIST_LINE);
            } else if (dimensionEnum == SensitiveWhiteListDimensionEnum.PRODUCT_SP_TAG) {
//                    DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_SP_TAG_EXCLUDED_WHITE_LIST_LINE);
            } else if (dimensionEnum == SensitiveWhiteListDimensionEnum.PLATFORM_BRAND) {
//                    DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_HIT_WHITE_LIST_CHART, DiggerConstants.SENSITIVE_HIT_PLATFORM_BRAND_EXCLUDED_WHITE_LIST_LINE);
            } else {
                log.warn("objectTypeEnum:{}", objectTypeEnum);
            }
        }
        return flag;
    }

    public static void logPlatformWhiteList(long wmPoiId) {
        Map<String, String> logMap = Maps.newHashMap();
//        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_SENSITIVE_TYPE.getName(), String.valueOf(WhiteListHitSensitiveTypeEnum.NONE.getValue()));
//        logMap.put(WhiteListMatchLoggerFieldEnum.HIT_WHITELIST_DIMENSION.getName(), String.valueOf(WhiteListHitDimensionEnum.PLATFORM_WHITELIST.getValue()));
//        logMap.put(WhiteListMatchLoggerFieldEnum.WM_POI_ID.getName(), String.valueOf(wmPoiId));
//        LogUtil.logWhiteListMatchInfoTrace("", logMap);
    }

}

