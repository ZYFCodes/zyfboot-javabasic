package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:03
 */
@Data
@Builder
public class ProductPackageSkuRel {
    private long spuId;
    private int count;
    private double discount;
    private int sequence;
    private int isMaster;
    private double skuPrice;
}
