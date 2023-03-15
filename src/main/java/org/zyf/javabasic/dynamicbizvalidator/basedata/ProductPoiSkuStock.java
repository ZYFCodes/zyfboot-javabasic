package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:50
 */
@Data
@Builder
public class ProductPoiSkuStock {
    private long poiId;
    private long poiSkuId;
    private long id;
    private int ctime;
    private int utime;
    private int stock;
    private int maxStock;
    private int autoRefresh;
    private int limitStock;
    private int stockLimit;
    private String operator;
}
