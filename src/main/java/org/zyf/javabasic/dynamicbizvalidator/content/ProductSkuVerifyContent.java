package org.zyf.javabasic.dynamicbizvalidator.content;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.zyf.javabasic.common.utils.FormatUtils;
import org.zyf.javabasic.common.utils.FrequencyUtils;
import org.zyf.javabasic.common.utils.PackageProductUtils;
import org.zyf.javabasic.common.utils.PicUrlLegalCheckUtil;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfig;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfigGroup;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyKey;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyMessage;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSku;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSpu;
import org.zyf.javabasic.dynamicbizvalidator.dynamicdeal.ProductStaticValidator;
import org.zyf.javabasic.dynamicbizvalidator.enums.DynamicConfigType;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 商品sku校验信息数据
 * @date 2023/3/8  23:42
 */
@Data
public class ProductSkuVerifyContent {
    protected long id;
    protected long poiId;
    protected long spuId;
    protected String picture;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.spec.length.min", defaultConfigValue = "0", configType = DynamicConfigType.LENGTH_MIN),
            @DynamicConfig(configName = "sku.spec.length.max", defaultConfigValue = "10", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String spec;

    @VerifyMessage("价格超过上限")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.price.top", defaultConfigValue = "999", configType = DynamicConfigType.RANGE_MAX),
    })
    protected double price;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.stock.min", defaultConfigValue = "-9999999", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "sku.stock.max", defaultConfigValue = "9999999", configType = DynamicConfigType.RANGE_MAX),
    })
    protected int stock;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.boxNum.min", defaultConfigValue = "0", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "sku.boxNum.max", defaultConfigValue = "100", configType = DynamicConfigType.RANGE_MAX),
    })
    protected double boxNum;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.boxPrice.min", defaultConfigValue = "0", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "sku.boxPrice.max", defaultConfigValue = "100", configType = DynamicConfigType.RANGE_MAX),
    })
    protected double boxPrice;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.upc_code.isNotNull", defaultConfigValue = "false", configType = DynamicConfigType.NOT_BLANK),
    })
    protected String upc;

    @VerifyKey("thirdPartyPrimaryId(sourceFoodCode)")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.thirdPartyPrimaryId.length.max", defaultConfigValue = "128", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String thirdPartyPrimaryId;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.locatorCode.length.max", defaultConfigValue = "50", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String locatorCode;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.tagname.length.max", defaultConfigValue = "255", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String tagName;

    @VerifyMessage("可售时间不合法")
    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.shippingtimex.legal", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE),
    })
    protected boolean legalShippingTimeX = true;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.description.length.max", defaultConfigValue = "255", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String description;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.unit.length.max", defaultConfigValue = "20", configType = DynamicConfigType.LENGTH_MAX)
    })
    protected String unit;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.minOrderCount.min", defaultConfigValue = "0", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "sku.minOrderCount.max", defaultConfigValue = "50", configType = DynamicConfigType.RANGE_MAX),
    })
    protected int minOrderCount;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.weight.isNotNull", defaultConfigValue = "false", configType = DynamicConfigType.NUMBER_NOT_NULL)
    })
    protected long weight;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.upc_code.uniqueInPoi", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE),
    })
    @VerifyMessage("商品upc/ean编码在该店中已存在")
    protected boolean upcUniqueInPoi = true;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.price.range", defaultConfigValue = "100000", configType = DynamicConfigType.RANGE_MAX),
    })
    @VerifyMessage("价格修改幅度超过上限")
    protected double priceRange;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.price.frequency", defaultConfigValue = "100000", configType = DynamicConfigType.RANGE_MAX),
    })
    @VerifyMessage("价格修改频率超过上限")
    protected long priceFrequency;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.legalPicUrl", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE),
    })
    @VerifyMessage("图片地址不合法")
    protected boolean legalPicUrl = true;


    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.package.skus.size.min", defaultConfigValue = "2", configType = DynamicConfigType.RANGE_MIN),
            @DynamicConfig(configName = "sku.package.skus.size.max", defaultConfigValue = "100000", configType = DynamicConfigType.RANGE_MAX)
    })
    @VerifyKey("组包商品数量")
    @VerifyMessage("您设置的组包只包含一个商品，不支持设置")
    protected int packageSkuSize = 2;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.package.unique", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE),
    })
    @VerifyKey("组包店内唯一")
    @VerifyMessage("该组包中包含的商品及数量，与店铺中其他组包相同，不支持创建")
    protected boolean packageUniqueInPoi = true;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.package.legalPackagePrice", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE),
    })
    @VerifyKey("组包价格")
    @VerifyMessage("您设置的组包价与原价相同，不支持创建")
    protected boolean legalPackagePrice = true;

    @DynamicConfigGroup(config = {
            @DynamicConfig(configName = "sku.package.legalPackageStock", defaultConfigValue = "true", configType = DynamicConfigType.IS_TRUE),
    })
    @VerifyKey("组包库存")
    @VerifyMessage("您设置的组包库存有误，不支持创建")
    protected boolean legalPackageStock = true;

    public static List<ProductSkuVerifyContent> toProductSkuVerifyContents(List<ProductPoiSku> poiSkuList, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        if (CollectionUtils.isEmpty(poiSkuList)) {
            return Collections.emptyList();
        }

        List<ProductSkuVerifyContent> productSkuVerifyContents = Lists.newArrayListWithCapacity(poiSkuList.size());
        for (int i = 0; i < poiSkuList.size(); i++) {
            productSkuVerifyContents.add(toProductSkuVerifyContent(poiSkuList.get(i), i, poiProductValidatorDataHolder));
        }
        return productSkuVerifyContents;
    }

    public static ProductSkuVerifyContent toProductSkuVerifyContent(ProductPoiSku sku, int spuIndex, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        /*用于模拟*/
        ProductSkuVerifyContent productSkuVerifyContent = new ProductSkuVerifyContent();
        productSkuVerifyContent.setPoiId(sku.getPoiId());
        productSkuVerifyContent.setSpuId(sku.getPoiSpuId());

        /*总分商品特殊处理*/
        if (sku.getHqSkuId() > 0) {
            if (StringUtils.isBlank(sku.getUpcCode())) {
                productSkuVerifyContent.setUpc(sku.getHqSkuId() + "");
            }

            if (sku.getWeight() == 0) {
                productSkuVerifyContent.setWeight(1);
            }
        }

        /*upc唯一校验处理*/
        productSkuVerifyContent.setUpcUniqueInPoi(skuUpcUniqueInPoi(sku.getId(), sku.getHqSkuId(), sku.getUpcCode(), spuIndex, poiProductValidatorDataHolder));
        /*可售时间合法性校验*/
        productSkuVerifyContent.setLegalShippingTimeX(ProductStaticValidator.shippingTimeXCheck(sku.getShippingTime()));
        /*图片合法性校验*/
        productSkuVerifyContent.setLegalPicUrl(PicUrlLegalCheckUtil.checkLegaPicUrl(productSkuVerifyContent.getPicture()));
        /*验证价格*/
        processSkuPrice(productSkuVerifyContent, poiProductValidatorDataHolder.getSkuIdToSkuMap());
        /*组包商品关联单品的总数*/
        processPackageSkuSize(productSkuVerifyContent, sku);
        // 组包商品店内唯一
        productSkuVerifyContent.setPackageUniqueInPoi(packageSkuUniqueInPoi(sku, spuIndex, poiProductValidatorDataHolder));

        ImmutablePair<Boolean, Boolean> legalPackagePriceAndStockPair = legalPackagePriceAndStock(sku, poiProductValidatorDataHolder);
        // 组包价格设置的是否合法
        productSkuVerifyContent.setLegalPackagePrice(legalPackagePriceAndStockPair.getLeft());
        // 组包库存设置的是否合法
        productSkuVerifyContent.setLegalPackageStock(legalPackagePriceAndStockPair.getRight());

        return productSkuVerifyContent;
    }

    /**
     * 验证sku的upc码店内唯一
     */
    public static boolean skuUpcUniqueInPoi(long skuId, long hqSkuId, String upcCode, int indexOfSpu, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        /*upc唯一性校验，1、先检查数据库中是否有重复的upc 2、再校验本次批量插入的商品中有没有相同的upc*/
        boolean upcUniqueInPoi = CollectionUtils.isEmpty(existUpcInPoi(skuId, hqSkuId, upcCode, poiProductValidatorDataHolder));
        if (upcUniqueInPoi) {
            upcUniqueInPoi = CollectionUtils.isEmpty(existUpcInBatch(upcCode, indexOfSpu, poiProductValidatorDataHolder));
        }
        return upcUniqueInPoi;
    }

    /**
     * 校验upc是否重复
     */
    public static List<ProductPoiSku> existUpcInPoi(long skuId, long hqSkuId, String upcCode, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiSku> resultList = Lists.newArrayList();
        if (StringUtils.isBlank(upcCode)) {
            return resultList;
        }

        /*查询当前upc是否已经存在*/
        Collection<ProductPoiSku> skuCollection = poiProductValidatorDataHolder.getUpcToSkuListMap().get(upcCode);
        /*为空表示upc在店内不存在*/
        if (CollectionUtils.isEmpty(skuCollection)) {
            return resultList;
        }

        for (ProductPoiSku existSku : skuCollection) {
            /*更新自己不算重复*/
            if (skuId > 0 && existSku.getId() == skuId) {
                continue;
            }
            /*hqSkuId相同也是更新自己， 总分的逻辑*/
            if (hqSkuId > 0 && existSku.getHqSkuId() == hqSkuId) {
                continue;
            }
            /*相同spu下的sku可以upc重复*/
            if (skuId == existSku.getPoiSpuId()) {
                continue;
            }
            /*其他情况就属于upc重复了*/
            resultList.add(existSku);
        }
        return resultList;
    }

    /**
     * 校验在同一次创建的sku中有没有重复的upc
     * 规则：当同时传多个相同UPC，且在不同SPU下时，则按照提交允许仅允许第一个创建成功
     */
    public static List<ProductPoiSku> existUpcInBatch(String upcCode, int indexOfSpu, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiSku> resultList = Lists.newArrayList();
        if (StringUtils.isBlank(upcCode)) {
            return resultList;
        }

        Map<Long, List<ProductPoiSku>> spuIdToSkuListMap = poiProductValidatorDataHolder.getSpuIdToSkuListMap();
        if (MapUtils.isEmpty(spuIdToSkuListMap)) {
            return resultList;
        }

        int index = 0;
        for (Map.Entry<Long, List<ProductPoiSku>> longListEntry : spuIdToSkuListMap.entrySet()) {
            if (indexOfSpu <= index++) {
                break;
            }

            List<ProductPoiSku> skuList = longListEntry.getValue();
            for (ProductPoiSku poiSku : skuList) {
                if (upcCode.equals(poiSku.getUpcCode())) {
                    resultList.add(poiSku);
                }
            }
        }

        return resultList;
    }

    /**
     * 处理价格修复幅度和价格修改频率
     */
    public static void processSkuPrice(ProductSkuVerifyContent productSkuVerifyContent, Map<Long, ProductPoiSku> skuMap) {
        ProductPoiSku oldPoiSku = skuMap.get(productSkuVerifyContent.getId());
        if (oldPoiSku == null || oldPoiSku.getPrice() == productSkuVerifyContent.getPrice()) {
            return;
        }

        if (oldPoiSku.getPrice() == 0) {
            oldPoiSku.setPrice(0.01);
        }
        productSkuVerifyContent.setPriceRange(Math.abs((productSkuVerifyContent.getPrice() / skuMap.get(productSkuVerifyContent.getId()).getPrice() - 1) * 100));
        /*查询最后修改时间，先模拟吧*/
        /*long lastModifyTime = poiProductVerifyQueryService.getSkuPriceLastModifyTime(productSkuVerifyContent.getId());*/
        long lastModifyTime = 72L;
        if (lastModifyTime != 0) {
            productSkuVerifyContent.setPriceFrequency(FrequencyUtils.getPriceFrequency(FormatUtils.unixTime(), lastModifyTime));
        }
    }

    public static void processPackageSkuSize(ProductSkuVerifyContent productSkuVerifyContent, ProductPoiSku sku) {
        List<ProductPoiPackageSkuRel> poiPackageSkuRelList = sku.getPoiPackageSkuRelList();
        if (CollectionUtils.isEmpty(poiPackageSkuRelList)) {
            return;
        }

        int packageSkuSize = 0;
        for (ProductPoiPackageSkuRel poiPackageSkuRel : poiPackageSkuRelList) {
            String pricingRule = poiPackageSkuRel.getPricingRule();
            if (StringUtils.isBlank(pricingRule)) {
                continue;
            }

            ProductPackageSkuRel packageSkuRel = JSONObject.parseObject(pricingRule, ProductPackageSkuRel.class);
            packageSkuSize += packageSkuRel.getCount();
        }

        productSkuVerifyContent.setPackageSkuSize(packageSkuSize);
    }

    public static boolean packageSkuUniqueInPoi(ProductPoiSku poiSku, int indexOfSpu, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        /*组包唯一性校验，1、先检查数据库中是否有重复的组包 2、再校验本次批量插入的商品中有没有相同的组包*/
        boolean packageUniqueInPoi = CollectionUtils.isEmpty(existPackageSkuInPoi(poiSku, poiProductValidatorDataHolder));
        if (packageUniqueInPoi) {
            packageUniqueInPoi = CollectionUtils.isEmpty(existPackageSkuInBatch(poiSku, indexOfSpu, poiProductValidatorDataHolder));
        }
        return packageUniqueInPoi;
    }

    /**
     * 校验在门店内有没有重复的组包
     */
    public static List<Long> existPackageSkuInPoi(ProductPoiSku poiSku, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<Long> resultList = Lists.newArrayList();
        List<ProductPoiPackageSkuRel> poiPackageSkuRelList = poiSku.getPoiPackageSkuRelList();
        if (CollectionUtils.isEmpty(poiPackageSkuRelList)) {
            return Collections.emptyList();
        }

        /*转成唯一key*/
        String packageSkuIdStr = PackageProductUtils.toPackageSkuIdStr(poiPackageSkuRelList);

        Map<String, Long> packageSkuIdToSkuMap = poiProductValidatorDataHolder.getSkuIdListStrToPackageSkuMap();
        if (packageSkuIdToSkuMap.containsKey(packageSkuIdStr) && packageSkuIdToSkuMap.get(packageSkuIdStr) != poiSku.getId()) {
            resultList.add(packageSkuIdToSkuMap.get(packageSkuIdStr));
        }

        return resultList;
    }

    /**
     * 校验在同一次创建的sku中有没有重复的组包
     */
    public static List<ProductPoiSku> existPackageSkuInBatch(ProductPoiSku poiSku, int indexOfSpu, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiSku> resultList = Lists.newArrayList();
        List<ProductPoiPackageSkuRel> poiPackageSkuRelList = poiSku.getPoiPackageSkuRelList();
        if (CollectionUtils.isEmpty(poiPackageSkuRelList)) {
            return Collections.emptyList();
        }

        String packageSkuIdStr = PackageProductUtils.toPackageSkuIdStr(poiPackageSkuRelList);
        List<ProductPoiSpu> spuList = poiProductValidatorDataHolder.getSpuList();
        for (int i = 0; i < indexOfSpu; i++) {
            ProductPoiSpu poiSpu = spuList.get(i);
            if (poiSpu.getIsPackage() == 0) {
                continue;
            }

            List<ProductPoiSku> skuList = poiSpu.getSkuList();
            if (CollectionUtils.isEmpty(skuList)) {
                continue;
            }

            for (ProductPoiSku sku : skuList) {
                List<ProductPoiPackageSkuRel> tempPoiPackageSkuRelList = sku.getPoiPackageSkuRelList();
                if (CollectionUtils.isEmpty(tempPoiPackageSkuRelList)) {
                    continue;
                }

                String tempPackageSkuIdStr = PackageProductUtils.toPackageSkuIdStr(tempPoiPackageSkuRelList);
                if (Objects.equals(packageSkuIdStr, tempPackageSkuIdStr)) {
                    resultList.add(sku);
                }
            }
        }

        return resultList;
    }

    public static ImmutablePair<Boolean, Boolean> legalPackagePriceAndStock(ProductPoiSku poiSku, PoiProductValidatorDataHolder poiProductValidatorDataHolder) {
        List<ProductPoiPackageSkuRel> poiPackageSkuRelList = poiSku.getPoiPackageSkuRelList();
        if (CollectionUtils.isEmpty(poiPackageSkuRelList)) {
            return ImmutablePair.of(true, true);
        }

        double originPrice = 0;
        double discountPrice = 0;
        int stock = Integer.MAX_VALUE;

        Map<Long, ProductPoiSku> skuIdToPackageRelSkuMap = poiProductValidatorDataHolder.getSkuIdToPackageRelSkuMap();
        for (ProductPoiPackageSkuRel poiPackageSkuRel : poiPackageSkuRelList) {
            ProductPoiSku originSku = skuIdToPackageRelSkuMap.get(poiPackageSkuRel.getPoiSkuId());
            if (originSku == null) {
                continue;
            }

            if (StringUtils.isBlank(poiPackageSkuRel.getPricingRule())) {
                continue;
            }
            ProductPackageSkuRel packageSkuRel = JSONObject.parseObject(poiPackageSkuRel.getPricingRule(), ProductPackageSkuRel.class);

            double skuPrice = originSku.getPrice();
            DecimalFormat df = new DecimalFormat("#.00");
            originPrice += skuPrice * packageSkuRel.getCount();
            discountPrice += (Double.parseDouble(df.format(skuPrice * packageSkuRel.getDiscount() / 10)) * packageSkuRel.getCount());

            //库存
            if (originSku.getStock() != -1) {
                int packageSkuStock = (int) Math.floor(originSku.getStock() / packageSkuRel.getCount());
                stock = Math.min(stock, packageSkuStock);
            }
        }

        boolean legalPackagePrice = true;
        if (originPrice <= discountPrice || Math.abs(poiSku.getPrice() - discountPrice) >= 0.1) {
            legalPackagePrice = false;
        }

        boolean legalPackageStock = true;
        //无限库存
        if (stock == Integer.MAX_VALUE) {
            if (poiSku.getStock() != -1) {
                legalPackageStock = false;
            }
        } else {
            if (stock != poiSku.getStock()) {
                legalPackageStock = false;
            }
        }

        return ImmutablePair.of(legalPackagePrice, legalPackageStock);
    }
}
