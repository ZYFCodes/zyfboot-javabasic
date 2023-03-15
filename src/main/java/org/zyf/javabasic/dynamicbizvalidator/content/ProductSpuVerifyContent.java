package org.zyf.javabasic.dynamicbizvalidator.content;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfig;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfigGroup;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyKey;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyMessage;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSpu;
import org.zyf.javabasic.dynamicbizvalidator.enums.DynamicConfigType;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 商品spu校验信息数据
 * @date 2023/3/8  23:45
 */
@Data
public class ProductSpuVerifyContent {
    protected long id;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.name.notnull", defaultConfigValue = "true", configType = DynamicConfigType.NOT_BLANK),
            @DynamicConfig(configName = "spu.name.length.max", defaultConfigValue = "10", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String name;

    @VerifyMessage("商品spu名称在该店内分类中已存在")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.name.uniqueInTag", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE)
    })
    protected boolean nameUniqueInTag = true;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.skus.size.min", defaultConfigValue = "0", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "spu.skus.size.max", defaultConfigValue = "10", configType = DynamicConfigType.RANGE_MAX)
    })
    protected Integer skuSize;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.skus.attrSku.size.min", defaultConfigValue = "0", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "spu.skus.attrSku.size.max", defaultConfigValue = "10", configType = DynamicConfigType.RANGE_MAX)
    })
    protected Integer attrSkuSize;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.spCategory.isNotNull", defaultConfigValue = "false", configType = DynamicConfigType.NUMBER_NOT_NULL)
    })
    protected long categoryId;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.piccontent.content.length.max", defaultConfigValue = "4096", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String picContent;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.unit.length.max", defaultConfigValue = "20", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String unit;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.description.length.max", defaultConfigValue = "4096", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String description;

    @VerifyKey("thirdPartyPrimaryId(sourceFoodCode)")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.thirdPartyPrimaryId.length.max", defaultConfigValue = "128", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String thirdPartyPrimaryId;

    @VerifyKey("组包使用场景")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "spu.package.use.scene.notnull", defaultConfigValue = "true", configType = DynamicConfigType.NOT_BLANK),
            @DynamicConfig(configName = "spu.package.use.scene.length.max", defaultConfigValue = "8", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String packageUseScene;

    /**
     * ProductSpuVerifyContents
     */
    public static List<ProductSpuVerifyContent> toProductSpuVerifyContents(List<ProductPoiSpu> spuList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductSpuVerifyContent> productSpuValidEntityList = Lists.newArrayListWithCapacity(spuList.size());
        for (int i = 0; i < spuList.size(); i++) {
            productSpuValidEntityList.add(toProductSpuVerifyContent(spuList.get(i), poiProductValidatorDataHolder, i));
        }

        return productSpuValidEntityList;
    }

    /**
     * 转换成ProductSpuVerifyContent
     */
    public static ProductSpuVerifyContent toProductSpuVerifyContent(ProductPoiSpu poiSpu, PoiProductValidatorDataHolder poiProductValidatorDataHolder, int indexOfBatch) {
        /*用于模拟*/
        ProductSpuVerifyContent productSpuVerifyContent = new ProductSpuVerifyContent();

        if (poiSpu.getHqSpuId() > 0 && StringUtils.isBlank(poiSpu.getName())) {
            /*总分模式下，商品名字是可以为空的，设置个任意不重复的值，防止非空校验*/
            productSpuVerifyContent.setName(poiSpu.getHqSpuId() + "_" + indexOfBatch);
        }
        productSpuVerifyContent.setPackageUseScene("热点商品售卖");

        return productSpuVerifyContent;
    }
}
