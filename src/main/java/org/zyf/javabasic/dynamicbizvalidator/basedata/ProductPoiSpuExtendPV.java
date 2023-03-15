package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:39
 */
@Data
@Builder
public class ProductPoiSpuExtendPV {
    private long id;
    private String code;
    private String name;
    private String value;
    private int valid;
    private long valueId;
    private int sequence;
    private int templateId;
    private int categoryId;
    private long parentValueId;
    private double score;
    private int level;
    private int isLeaf;
    private int propertySequence;
    private int valueSequence;
    private int type;
    private long parentPropertyId;
    private double price;
}
