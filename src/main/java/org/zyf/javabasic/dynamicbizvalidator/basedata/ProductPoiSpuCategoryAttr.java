package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:53
 */
@Data
@Builder
public class ProductPoiSpuCategoryAttr {
    private long poiId;
    private long poiSpuId;
    private long id;
    private int ctime;
    private int utime;
    private List<ProductPoiSpuGategoryAttrPV> pvList;
    private String reserved1;
    private long hqSpuId;
}
