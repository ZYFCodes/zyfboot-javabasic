package org.zyf.javabasic.dynamicbizvalidator.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.dynamicbizvalidator.basedata.*;
import org.zyf.javabasic.dynamicbizvalidator.content.PoiProductValidatorDataHolder;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 商品数据校验数据持有者查询整合
 * @date 2023/3/9  23:12
 */
@Service
public class PoiProductValidatorDataHolderFactory {

    @Resource
    private PoiProductVerifyQueryService poiProductVerifyQueryService;

    public PoiProductValidatorDataHolder getInstanceBySpu(long poiId, List<ProductPoiSpu> poiSpuList) {
        PoiProductValidatorDataHolder poiProductValidatorDataHolder = new PoiProductValidatorDataHolder();

        poiProductValidatorDataHolder.getSpuList().addAll(poiSpuList);
        /*构建spu依赖的数据*/
        buildSpuData(poiId, poiSpuList, poiProductValidatorDataHolder);

        /*构建sku依赖的数据*/
        List<ProductPoiSku> poiSkuList = poiSpuList.stream().map(ProductPoiSpu::getSkuList)
                .filter(CollectionUtils::isNotEmpty).flatMap(Collection::stream).collect(Collectors.toList());
        buildSkuData(poiId, poiSkuList, poiProductValidatorDataHolder);

        return poiProductValidatorDataHolder;
    }

    public void buildSpuData(long poiId, List<ProductPoiSpu> poiSpuList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        /*设置同名的spu->同名spu校验基础数据*/
        buildSpuDataForTagIdSpu(poiId, poiSpuList, poiProductValidatorDataHolder);
        /*本次待保存的sku列表*/
        buildSpuDataForSkuList(poiProductValidatorDataHolder, poiSpuList);
    }

    /**
     * 设置同名的spu
     */
    public void buildSpuDataForTagIdSpu(long poiId, List<ProductPoiSpu> poiSpuList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        Set<Long> tagIdSet = Sets.newHashSet();
        Set<String> spuNameSet = Sets.newHashSet();
        for (ProductPoiSpu poiSpu : poiSpuList) {
            if (CollectionUtils.isNotEmpty(poiSpu.getTagList())) {
                poiSpu.getTagList().forEach(tag -> tagIdSet.add(tag.getId()));
            }
            if (StringUtils.isNotBlank(poiSpu.getName())) {
                spuNameSet.add(poiSpu.getName());
            }
        }

        List<ProductPoiSpu> spuList = poiProductVerifyQueryService.getSameNamePoiSpuList(poiId, Lists.newArrayList(tagIdSet), Lists.newArrayList(spuNameSet));
        for (ProductPoiSpu spu : spuList) {
            if (CollectionUtils.isEmpty(spu.getTagList())) {
                continue;
            }

            for (ProductPoiTag poiTag : spu.getTagList()) {
                poiProductValidatorDataHolder.getTagIdToSpuMultimap().put(poiTag.getId(), spu);
            }
        }
    }

    /**
     * 设置sku
     */
    private void buildSpuDataForSkuList(PoiProductValidatorDataHolder poiProductValidatorDataHolder, List<ProductPoiSpu> poiSpuList) {
        for (ProductPoiSpu poiSpu : poiSpuList) {
            if (CollectionUtils.isEmpty(poiSpu.getSkuList())) {
                poiProductValidatorDataHolder.getSpuIdToSkuListMap().put(poiSpu.getId(), Collections.emptyList());
            }
            poiProductValidatorDataHolder.getSpuIdToSkuListMap().put(poiSpu.getId(), poiSpu.getSkuList());
        }
    }

    public void buildSkuData(long poiId, List<ProductPoiSku> poiSkuList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        Set<String> upcSet = Sets.newHashSet();
        Set<Long> idSet = Sets.newHashSet();
        Set<Long> packageIdSet = Sets.newHashSet();

        /*默认本次创建的商品中没有组包商品*/
        boolean hasPackage = false;
        for (ProductPoiSku poiSku : poiSkuList) {
            if (StringUtils.isNotEmpty(poiSku.getUpcCode())) {
                upcSet.add(poiSku.getUpcCode());
            }
            if (poiSku.getId() > 0) {
                idSet.add(poiSku.getId());
            }

            if (CollectionUtils.isNotEmpty(poiSku.getPoiPackageSkuRelList())) {
                hasPackage = true;
                for (ProductPoiPackageSkuRel poiPackageSkuRel : poiSku.getPoiPackageSkuRelList()) {
                    packageIdSet.add(poiPackageSkuRel.getPoiSkuId());
                }
            }
        }

        buildSkuDataForUpcList(poiId, Lists.newArrayList(upcSet), poiProductValidatorDataHolder);
        buildSkuDataForSkuIdList(poiId, Lists.newArrayList(idSet), poiProductValidatorDataHolder);
        if (hasPackage) {
            buildSkuDataForPackage(poiId, Lists.newArrayList(packageIdSet), poiProductValidatorDataHolder);
        }
    }

    public void buildSkuDataForUpcList(long poiId, List<String> upcList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiSku> skuListByUpcs = poiProductVerifyQueryService.getSkuListByUpcList(poiId, upcList);
        if (CollectionUtils.isNotEmpty(skuListByUpcs)) {
            skuListByUpcs.forEach(sku -> poiProductValidatorDataHolder.getUpcToSkuListMap().put(sku.getUpcCode(), sku));
        }
    }

    public void buildSkuDataForSkuIdList(long poiId, List<Long> skuIdList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiSku> skuListByIds = poiProductVerifyQueryService.getSkuListByIdList(poiId, skuIdList, false);
        if (CollectionUtils.isNotEmpty(skuListByIds)) {
            skuListByIds.forEach(sku -> poiProductValidatorDataHolder.getSkuIdToSkuMap().put(sku.getId(), sku));
        }
    }

    public void buildSkuDataForPackage(long poiId, List<Long> skuIdList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiPackageSkuRel> poiPackageSkuRelList = poiProductVerifyQueryService.getPackageSkuRelListBySkuIdList(poiId, skuIdList);
        if (CollectionUtils.isNotEmpty(poiPackageSkuRelList)) {
            List<Long> packageSkuIdList = poiPackageSkuRelList.stream().map(ProductPoiPackageSkuRel::getPoiPackageSkuId).distinct().collect(Collectors.toList());
            poiPackageSkuRelList = poiProductVerifyQueryService.getPackageSkuRelListByPackageSkuIdList(poiId, packageSkuIdList);

            Map<Long, List<ProductPoiPackageSkuRel>> packageSkuIdMap = poiPackageSkuRelList.stream()
                    .collect(Collectors.groupingBy(ProductPoiPackageSkuRel::getPoiPackageSkuId));

            for (Map.Entry<Long, List<ProductPoiPackageSkuRel>> entry : packageSkuIdMap.entrySet()) {
                String packageSkuIdStr = toPackageSkuIdStr(entry.getValue());
                poiProductValidatorDataHolder.getSkuIdListStrToPackageSkuMap().put(packageSkuIdStr, entry.getKey());
            }
        }

        List<ProductPoiPackageSkuRel> poiPackageSkuRelList2 = poiProductVerifyQueryService.getPackageSkuRelListByPackageSkuIdList(poiId, skuIdList);
        if (CollectionUtils.isNotEmpty(poiPackageSkuRelList2)) {
            List<Long> packageSkuIdList = poiPackageSkuRelList2.stream().map(ProductPoiPackageSkuRel::getPoiPackageSkuId).distinct().collect(Collectors.toList());
            List<ProductPoiSku> packageSkuList = poiProductVerifyQueryService.getSkuListByIdList(poiId, packageSkuIdList, false);
            if (CollectionUtils.isNotEmpty(packageSkuList)) {
                for (ProductPoiSku packageSku : packageSkuList) {
                    poiProductValidatorDataHolder.getPackageSkuIdToPackageSkuMap().put(packageSku.getId(), packageSku);
                }
            }
        }

        List<ProductPoiSku> skuListByIds = poiProductVerifyQueryService.getSkuListByIdList(poiId, skuIdList, true);
        if (CollectionUtils.isNotEmpty(skuListByIds)) {
            skuListByIds.forEach(sku -> poiProductValidatorDataHolder.getSkuIdToPackageRelSkuMap().put(sku.getId(), sku));
        }

    }

    public static String toPackageSkuIdStr(List<ProductPoiPackageSkuRel> poiPackageSkuRelList) {
        TreeMap<Long, Integer> skuIdToCount = new TreeMap<>();
        for (ProductPoiPackageSkuRel poiPackageSkuRel : poiPackageSkuRelList) {
            String pricingRule = poiPackageSkuRel.getPricingRule();
            if (StringUtils.isBlank(pricingRule)) {
                skuIdToCount.put(poiPackageSkuRel.getPoiSkuId(), 0);
                continue;
            }
            ProductPackageSkuRel packageSkuRel = JSON.parseObject(pricingRule, ProductPackageSkuRel.class);
            skuIdToCount.put(poiPackageSkuRel.getPoiSkuId(), packageSkuRel.getCount());
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Integer> entry : skuIdToCount.entrySet()) {
            sb.append(entry.getKey()).append("[").append(entry.getValue()).append("]");
        }

        return sb.toString();
    }
}
