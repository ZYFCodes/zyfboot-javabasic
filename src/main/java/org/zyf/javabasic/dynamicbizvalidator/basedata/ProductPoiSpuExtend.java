package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:38
 */
@Data
@Builder
public class ProductPoiSpuExtend {
    private long poiId;
    private long poiSpuId;
    private long id;
    private int ctime;
    private int utime;
    private List<ProductPoiSpuExtendPV> pvList;
    private int extendType;
    private String reserved1;
    private long hqSpuId;
}
