package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:48
 */
@Data
@Builder
public class ProductPoiSku {
    private long poiId;
    private long poiSpuId;
    private long hqSkuId;
    private long id;
    private int ctime;
    private int utime;
    private String upcCode;
    private String spec;
    private double price;
    private double oriPrice;
    private int minOrderCount;
    private int status;
    private int sellStatus;
    private String name;
    private String description;
    private String shippingTime;
    private int weight;
    private String weightUnit;
    private double groupPrice;
    private int stock;
    private int limitStock;
    private ProductPoiSkuStock skuStock;
    private String picture;
    private List<ProductPoiSpuPic> picList;
    private double boxNum;
    private double boxPrice;
    private ProductStockConfig productStockConfig;
    private List<ProductPoiSkuAttr> poiSkuAttrs;
    private LadderBoxPrice ladderBoxPrice;
    private String ladderBoxPriceJson;
    private long poiStandardId;
    private String reserved1;
    private List<ProductPoiSpuExtend> poiSpuExtends;
    private String sourceFoodCode;
    private int sequence;
    private int spuSequence;
    private String locatorCode;
    private List<ProductPoiPackageSkuRel> poiPackageSkuRelList;
    private String unit;
}
