package org.zyf.javabasic.dynamicbizvalidator.service;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSku;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSpu;
import org.zyf.javabasic.dynamicbizvalidator.content.PoiProductValidatorDataHolder;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductPackageVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductSkuVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductSpuVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.dynamicdeal.ProductDynamicValidator;
import org.zyf.javabasic.dynamicbizvalidator.model.VerifyResult;
import org.zyf.javabasic.dynamicbizvalidator.model.VerifyResultDetail;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 门店商品数据动态校验处理
 * @date 2023/3/9  23:37
 */
@Service
public class PoiProductDynamicValidatorService {

    @Resource
    private PoiProductValidatorDataHolderFactory poiProductValidatorDataHolderFactory;
    @Resource
    private ProductDynamicValidator productDynamicValidator;

    private final VerifyResultDetail.KeyGetter<ProductPoiSpu> spuKeyGetter = new VerifyResultDetail.KeyGetter<ProductPoiSpu>() {
        @Override
        public String key(ProductPoiSpu keySource) {
            return String.format("poiId[%d] spuId[%d] spuName[%s]", keySource.getPoiId(), keySource.getId(), keySource.getName());
        }
    };

    private final VerifyResultDetail.KeyGetter<ProductPoiSku> skuKeyGetter = new VerifyResultDetail.KeyGetter<ProductPoiSku>() {
        @Override
        public String key(ProductPoiSku keySource) {
            return String.format("poiId[%d] spuId[%d] skuId[%d] skuSpec[%s]", keySource.getPoiId(), keySource.getPoiSpuId(), keySource.getId(), keySource.getSpec());
        }
    };

    private final VerifyResultDetail.KeyGetter<ProductPoiPackageSkuRel> packageKeyGetter = new VerifyResultDetail.KeyGetter<ProductPoiPackageSkuRel>() {
        @Override
        public String key(ProductPoiPackageSkuRel keySource) {
            return String.format("packageSkuId[%d] skuId[%s]", keySource.getPoiPackageSkuId(), keySource.getPoiSkuId());
        }
    };

    /**
     * 门店商品spu动态校验处理
     */
    public VerifyResultDetail<ProductPoiSpu> spuVerify(long poiId, String currentBizKey, List<ProductPoiSpu> poiSpuList) {
        if (CollectionUtils.isEmpty(poiSpuList)) {
            return new VerifyResultDetail<>(poiSpuList, Lists.newArrayList(), spuKeyGetter).aggregation();
        }

        /*查询必要的数据*/
        PoiProductValidatorDataHolder poiProductValidatorDataHolder = poiProductValidatorDataHolderFactory.getInstanceBySpu(poiId, poiSpuList);
        /*spu的校验*/
        List<ProductSpuVerifyContent> productSpuVerifyContents = ProductSpuVerifyContent.toProductSpuVerifyContents(poiSpuList, poiProductValidatorDataHolder);
        List<VerifyResult> verifyResultList = productDynamicValidator.spuVerify(currentBizKey, productSpuVerifyContents);

        /*其他信息的校验*/
        VerifyResultDetail<ProductPoiSpu> verifyResultDetail = new VerifyResultDetail<>(poiSpuList, verifyResultList, spuKeyGetter);
        for (int i = 0; i < poiSpuList.size(); i++) {
            ProductPoiSpu poiSpu = poiSpuList.get(i);
            /*SKU*/
            if (CollectionUtils.isNotEmpty(poiSpu.getSkuList())) {
                VerifyResultDetail<ProductPoiSku> skuVerifyResultDetail = this.skuVerify(poiId, currentBizKey, poiSpu.getSkuList(), poiProductValidatorDataHolder, poiSpu.getIsPackage() == 1);
                verifyResultDetail.addVerifyResultMapList(VerifyResultDetail.SubEntity.SKU, skuVerifyResultDetail, i);
            }
        }
        return verifyResultDetail.aggregation();
    }

    /**
     * 门店商品sku动态校验处理
     */
    public VerifyResultDetail<ProductPoiSku> skuVerify(long poiId, String currentBizKey, List<ProductPoiSku> poiSkuList, PoiProductValidatorDataHolder poiProductValidatorDataHolder, boolean isPackage) {
        /*转成校验模型*/
        List<ProductSkuVerifyContent> productSkuVerifyContents = ProductSkuVerifyContent.toProductSkuVerifyContents(poiSkuList, poiProductValidatorDataHolder);

        /*sku的校验*/
        VerifyResultDetail<ProductPoiSku> poiSkuVerifyResultDetail = skuVerify(poiId, currentBizKey, poiSkuList, productSkuVerifyContents, isPackage);

        for (int i = 0; i < poiSkuList.size(); i++) {
            ProductPoiSku poiSku = poiSkuList.get(i);
            List<ProductPackageVerifyContent> productPackageVerifyContents = ProductPackageVerifyContent.toProductPackageVerifyContents(poiSku.getPoiPackageSkuRelList(), poiProductValidatorDataHolder);
            if (CollectionUtils.isEmpty(productPackageVerifyContents)) {
                continue;
            }

            VerifyResultDetail<ProductPoiPackageSkuRel> poiPackageSkuRelVerifyResultDetail = packageSkuRelVerify(poiId, currentBizKey, poiSku.getPoiPackageSkuRelList(), productPackageVerifyContents);
            poiSkuVerifyResultDetail.addVerifyResultMapList(VerifyResultDetail.SubEntity.PACKAGE_SKU_REL, poiPackageSkuRelVerifyResultDetail, i);
        }

        return poiSkuVerifyResultDetail.aggregation();
    }

    /**
     * 门店组包商品动态校验处理
     */
    public VerifyResultDetail<ProductPoiPackageSkuRel> packageSkuRelVerify(long poiId, String currentBizKey, List<ProductPoiPackageSkuRel> poiPackageSkuRelList, List<ProductPackageVerifyContent> productPackageVerifyContents) {
        if (CollectionUtils.isEmpty(productPackageVerifyContents)) {
            return new VerifyResultDetail<>(poiPackageSkuRelList, Lists.<VerifyResult>newArrayList(), packageKeyGetter);
        }

        List<VerifyResult> verifyResults = productDynamicValidator.packageVerify(currentBizKey, productPackageVerifyContents);
        return new VerifyResultDetail<>(poiPackageSkuRelList, verifyResults, packageKeyGetter).aggregation();
    }

    /**
     * sku校验
     */
    private VerifyResultDetail<ProductPoiSku> skuVerify(long poiId, String currentBizKey, List<ProductPoiSku> poiSkuList, List<ProductSkuVerifyContent> productSkuVerifyContents, boolean isPackage) {
        if (CollectionUtils.isEmpty(productSkuVerifyContents)) {
            return new VerifyResultDetail<>(poiSkuList, Lists.<VerifyResult>newArrayList(), skuKeyGetter);
        }

        List<VerifyResult> verifyResults = productDynamicValidator.skuVerify(currentBizKey, productSkuVerifyContents);
        VerifyResultDetail<ProductPoiSku> verifyResultDetail = new VerifyResultDetail<>(poiSkuList, verifyResults, skuKeyGetter);
        afterSkuVerify(verifyResultDetail, isPackage);
        return verifyResultDetail.aggregation();
    }

    public void afterSkuVerify(VerifyResultDetail<ProductPoiSku> skuVerifyResultDetail, boolean isPackage) {
        if (isPackage) {
            List<VerifyResult> verifyResultList = skuVerifyResultDetail.getVerifyResultList();
            for (VerifyResult verifyResult : verifyResultList) {
                verifyResult.getPropertyMessageMap().remove("upc");
                if (MapUtils.isEmpty(verifyResult.getPropertyMessageMap())) {
                    verifyResult.setIllegal(false);
                }
            }
        }
    }
}
