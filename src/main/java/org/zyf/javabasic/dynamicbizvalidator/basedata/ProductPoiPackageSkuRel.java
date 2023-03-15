package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:40
 */
@Data
@Builder
public class ProductPoiPackageSkuRel {
    private long poiId;
    private long id;
    private int ctime;
    private int utime;
    private long hqPackageSpuId;
    private long poiPackageSpuId;
    private long hqPackageSkuId;
    private long poiPackageSkuId;
    private long hqSkuId;
    private long poiSkuId;
    private String pricingRule;
}
