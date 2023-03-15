package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:42
 */
@Data
@Builder
public class ProductPoiSpuAttr {
    private long poiId;
    private long poiSpuId;
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
    private String reserved1;
    private long hqSpuId;
    private double price;
    private short sellStatus;
    private ProductPoiSpuAttrExtFields extFields;
}
