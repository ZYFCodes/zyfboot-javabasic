package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:36
 */
@Data
@Builder
public class ProductPoiSkuAttr {
    private long poiId;
    private long poiSkuId;
    private long id;
    private int ctime;
    private int utime;
    private String name;
    private String value;
    private long nameId;
    private long valueId;
    private short no;
    private int mode;
    private int valueSequence;
    private long poiSpuId;
    private long poiSpuAttrId;
    private String reserved1;
}
