package org.zyf.javabasic.dynamicbizvalidator.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 校验源集合sourceList与校验结果集合verifyResultList保证顺序，即校验源元素与校验结果元素哦在同一索引位置
 * @date 2023/3/9  23:40
 */
@Data
@Slf4j
public class VerifyResultDetail<T> {
    /**
     * 校验源集合
     */
    private List<T> sourceList;
    /**
     * 校验结果集合
     */
    private List<VerifyResult> verifyResultList;
    /**
     * T的对象域变量校验结果
     */
    private List<Map<SubEntity, VerifyResultDetail>> verifyResultMapList = Lists.newArrayList();
    /**
     * 校验合法的对象集合
     */
    private List<VerifyEntity<T>> legalList = Lists.newArrayList();
    /**
     * 校验非法的对象集合
     */
    private List<VerifyEntity<T>> illegalList = Lists.newArrayList();

    private KeyGetter keyGetter;

    public VerifyResultDetail(List<T> sourceList, List<VerifyResult> verifyResultList, KeyGetter keyGetter) {
        if (sourceList == null || verifyResultList == null || keyGetter == null) {
            throw new IllegalArgumentException("sourceList == null || verifyResultList == null || ");
        }

        this.sourceList = sourceList;
        this.verifyResultList = verifyResultList;
        this.keyGetter = keyGetter;
    }

    public VerifyResultDetail<T> aggregation() {
        if (sourceList.size() != verifyResultList.size()) {
            log.warn("被校验集合与校验结果集合长度不一致, sourceList.size[{}] verifyResultList.size[{}]", sourceList.size(), verifyResultList.size());
            return this;
        }

        int size = sourceList.size();
        VerifyResult verifyResult;
        for (int i = 0; i < size; i++) {
            verifyResult = verifyResultList.get(i);

            if (verifyResult == null) {
                continue;
            }

            (verifyResult.isIllegal() ? illegalList : legalList).add(new VerifyEntity(i, sourceList.get(i), verifyResultList.get(i)));
        }

        return this;
    }

    public List<T> legalEntityList() {
        if (CollectionUtils.isEmpty(legalList)) {
            return Lists.newArrayList();
        }
        return Lists.transform(legalList, new Function<VerifyEntity, T>() {
            @Override
            public T apply(VerifyEntity input) {
                return (T) input.getEntity();
            }
        });
    }

    public void addVerifyResultMapList(SubEntity subEntity, VerifyResultDetail verifyResultDetail, int verifyResultListIndex) {
        Map<SubEntity, VerifyResultDetail> tempMap;
        if (this.getVerifyResultMapList().size() >= verifyResultListIndex + 1) {
            tempMap = this.getVerifyResultMapList().get(verifyResultListIndex);
        } else {
            tempMap = Maps.newHashMap();
            this.getVerifyResultMapList().add(tempMap);
        }

        tempMap.put(subEntity, verifyResultDetail);

        if (this.getVerifyResultList().size() >= verifyResultListIndex + 1 && verifyResultDetail.hasIllegal()) {
            this.getVerifyResultList().get(verifyResultListIndex).setIllegal(true);
        }


    }

    public boolean hasIllegal() {
        return CollectionUtils.isNotEmpty(this.illegalList);
    }

    public Map<String, String> errorMessage() {
        if (!this.hasIllegal()) {
            return Maps.newLinkedHashMap();
        }

        Map<String, String> resultMap = Maps.newLinkedHashMap();
        Map<String, String> tempMap;
        Map<SubEntity, VerifyResultDetail> verifyResultDetailMap;
        for (VerifyEntity<T> verifyEntity : illegalList) {
            tempMap = Maps.newLinkedHashMap();
            tempMap.putAll(verifyEntity.getVerifyResult().getPropertyMessageMap());
            if (this.getVerifyResultMapList().size() >= verifyEntity.getIndex() + 1) {
                verifyResultDetailMap = this.getVerifyResultMapList().get(verifyEntity.getIndex());

                if (verifyResultDetailMap != null) {
                    for (SubEntity key : verifyResultDetailMap.keySet()) {
                        tempMap.put(key.getDesc(), verifyResultDetailMap.get(key).errorMessage().toString());
                    }
                }
            }

            resultMap.put(keyGetter.key(verifyEntity.getEntity()), tempMap.toString());
        }

        return resultMap;
    }

    @Data
    @AllArgsConstructor
    public static class VerifyEntity<T> {
        int index;
        T entity;
        VerifyResult verifyResult;
    }

    public interface KeyGetter<T> {
        String key(T keySource);
    }

    @AllArgsConstructor
    @Getter
    public enum SubEntity {
        SKU("sku"), PICTURE("图片"), ATTR("售卖属性"), SPU_EXTEND("SPU扩展属性"), SKU_EXTEND("SKU扩展属性"), LABEL("标签"), BRAND("品牌"),
        SPU_GRAPHICDESCRIPTION("图文详情"), SPU_VIDEO("商品视频"), SPU_INTELLIGENTPRODUCTTAG("spu智能分类"), SPU_MULTIPRODUCTTAG("多分类"), SKU_RELATEPRODUCTLIBREQUIRED("强制关联标品"),
        ALLOWCUSTOMPRODUCT("自建商品"), SKU_WEIHG("重量"), SKU_UPC("sku upc"), PACKAGE_SKU_REL("组包信息");

        private String desc;
    }
}

