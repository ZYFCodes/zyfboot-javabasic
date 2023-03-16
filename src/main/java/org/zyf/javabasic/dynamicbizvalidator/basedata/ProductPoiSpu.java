package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:41
 */
@Data
@Builder
public class ProductPoiSpu {
    private long id;
    private int ctime;
    private int utime;
    private long poiId;
    private long hqSpuId;
    private String name;
    private short isPackage;
    private long categoryId;
    private int sequence;
    private String unit;
    private String picContent;
    private String shippingTime;
    private short status;
    private short bizAuditStatus;
    private String description;
    private long videoId;
    private int version;
    private List<ProductPoiSpuAttr> attrList;
    private List<ProductPoiSpuExtend> extendList;
    private List<ProductPoiSpuPic> picList;
    private ProductPoiVideo video;
    private List<ProductPoiTag> tagList;
    private List<ProductLabel> labelList;
    private List<ProductPoiSku> skuList;
    private long spId;
    private String reserved1;
    private String sourceFoodCode;
    private List<ProductPoiSpuCategoryAttr> categoryAttrList;
    private List<ProductPoiProductRule> ruleList;
    private int monthSaled;
}
