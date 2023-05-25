package org.zyf.javabasic.sensitive.service;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  21:06
 */

import org.zyf.javabasic.sensitive.ValidatePair;
import org.zyf.javabasic.sensitive.base.SensitiveObjectTypeEnum;
import org.zyf.javabasic.sensitive.base.SensitiveWhiteListDimensionEnum;
import org.zyf.javabasic.sensitive.base.ValidateResult;

import java.util.Map;

/**
 * @author linjianbin02
 * 11:01 AM 2020/5/26
 */
public interface WmSensitiveWordWhiteListService {
    /**
     * 全流程白名单判断 平台维度+门店维度+门店品牌维度+后台类目维度
     *
     * @param wmPoiId        门店id
     * @param spTagId        商品关联类目id
     * @param validateResult 校验结果
     * @return boolean
     */
    boolean isWhiteListBySensitiveWord(ValidateResult validateResult, ValidatePair pair, Long wmPoiId, Long spTagId, Long platformBrandId);

    /**
     * 全流程白名单判断 平台维度+门店维度+门店品牌维度+后台类目维度
     *
     * @param wmPoiId         门店id
     * @param spTagId         商品关联类目id
     * @param sensitiveWordId 敏感词id
     * @return boolean
     */
    boolean isWhiteListBySensitiveWordId(Map<String, String> logMap, Long sensitiveWordId, Long wmPoiId, Long spTagId, Long platformBrandId);

    /**
     * 全流程白名单判断 平台维度+门店维度+门店品牌维度+后台类目维度
     *
     * @param wmPoiId        门店id
     * @param spTagId        商品关联类目id
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @return boolean
     */
    boolean isWhiteListByMulitDimension(Map<String, String> logMap, Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long wmPoiId, Long spTagId, Long platformBrandId);


    /**
     * 是否为平台白名单
     *
     * @param wmPoiId 门店id
     * @return boolean
     */
    boolean isPlatformWmPoiIdInWhiteList(Long wmPoiId);

    /**
     * 是否为平台品牌白名单
     *
     * @param wmPoiId 门店id
     * @return boolean
     */
    boolean isPlatformWmBrandIdInWhiteList(Long wmPoiId);

    /**
     * 是否为门店维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param wmPoiId        门店id
     * @return boolean
     */
    boolean isSensitiveWmPoiIdInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long wmPoiId);

    /**
     * 是否为品牌维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param brandId        品牌id
     * @return boolean
     */
    boolean isSensitiveBrandInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long brandId);

    /**
     * 是否为商品后台类目维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param spTagId        商品关联类目id
     * @return boolean
     */
    boolean isSensitiveSpTagInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long spTagId);

    /**
     * 多层级
     * 商品后台类目维度白名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param spTagId        商品关联类目id
     * @return boolean
     */
    boolean isSensitiveSpTagInWhiteListByMultiLevel(Map<String, String> logMap, Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long spTagId, Long wmPoiId);

    /**
     * 是否为排除名单
     *
     * @param objectId       敏感词id或者敏感词分类id
     * @param objectTypeEnum SensitiveObjectTypeEnum
     * @param outSideId      外部id，品牌id或者spTagid
     * @param dimensionEnum  SensitiveWhiteListDimensionEnum
     * @param wmPoiId        门店id
     * @return boolean
     */
    boolean isSensitiveExcludedWmPoiIdInWhiteList(Long objectId, SensitiveObjectTypeEnum objectTypeEnum, Long outSideId, SensitiveWhiteListDimensionEnum dimensionEnum, Long wmPoiId);

}

