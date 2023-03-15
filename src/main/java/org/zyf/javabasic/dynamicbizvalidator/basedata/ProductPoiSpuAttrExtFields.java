package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:43
 */
@Data
@Builder
public class ProductPoiSpuAttrExtFields {
    private int minRequiredCount;
    private int maxRequiredCount;
    private String excludeAttr;
}
