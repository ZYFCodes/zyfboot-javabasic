package org.zyf.javabasic.dynamicbizvalidator.content;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfig;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfigGroup;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyKey;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyMessage;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.enums.DynamicConfigType;

import java.util.Collections;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 组包商品校验信息数据
 * @date 2023/3/8  23:40
 */
@Data
public class ProductPackageVerifyContent {
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "package.sku.count.min", defaultConfigValue = "1", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "package.sku.count.max", defaultConfigValue = "10000", configType = DynamicConfigType.RANGE_MAX),

    })
    @VerifyKey("组包商品数量")
    private int count;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "package.sku.discount.min", defaultConfigValue = "0", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "package.sku.discount.max", defaultConfigValue = "10", configType = DynamicConfigType.RANGE_MAX)
    })
    @VerifyKey("组包商品的折扣")
    private double discount;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "package.name.uniqueInTag", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE)
    })
    @VerifyMessage("组包内有商品不存在，不支持创建")
    private boolean existSku = true;

    @VerifyMessage("您设置的组包包含组包商品，不支持创建")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "package.sku.notExistPackageSku", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE)
    })
    private boolean notExistPackageSku = true;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "package.sku.pricingRule", defaultConfigValue = "true", configType = DynamicConfigType.NOT_BLANK)
    })
    private String pricingRule;

    public static List<ProductPackageVerifyContent> toProductPackageVerifyContents(List<ProductPoiPackageSkuRel> poiPackageSkuRelList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        if (CollectionUtils.isEmpty(poiPackageSkuRelList)) {
            return Collections.emptyList();
        }

        List<ProductPackageVerifyContent> productPackageVerifyContents = Lists.newArrayListWithCapacity(poiPackageSkuRelList.size());

        ProductPackageVerifyContent productPackageVerifyContent;
        for (ProductPoiPackageSkuRel poiPackageSkuRel : poiPackageSkuRelList) {
            productPackageVerifyContent = new ProductPackageVerifyContent();
            productPackageVerifyContents.add(productPackageVerifyContent);

            productPackageVerifyContent.setPricingRule(poiPackageSkuRel.getPricingRule());

            String pricingRule = poiPackageSkuRel.getPricingRule();
            if (StringUtils.isBlank(pricingRule)) {
                continue;
            }
            ProductPackageSkuRel packageSkuRel = JSONObject.parseObject(pricingRule, ProductPackageSkuRel.class);
            productPackageVerifyContent.setCount(packageSkuRel.getCount());
            productPackageVerifyContent.setDiscount(packageSkuRel.getDiscount());

            productPackageVerifyContent.setExistSku(poiProductValidatorDataHolder.getSkuIdToPackageRelSkuMap().containsKey(poiPackageSkuRel.getPoiSkuId()));

            productPackageVerifyContent.setNotExistPackageSku(!(poiProductValidatorDataHolder.getPackageSkuIdToPackageSkuMap().containsKey(poiPackageSkuRel.getPoiSkuId())));
        }

        return productPackageVerifyContents;
    }
}
