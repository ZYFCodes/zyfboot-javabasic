package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:51
 */
@Data
@Builder
public class ProductPoiTag {
    private long poiId;
    private long id;
    private int ctime;
    private int utime;
    private String name;
    private int sequence;
    private String appTagCode;
    private String nodePath;
    private short level;
    private long parentId;
    private int buzType;
    private int isLeaf;
    private int tagType;
    private int topFlag;
    private String topFlagTime;
    private String description;
    private String categoryCode;
    private long hqTagId;
    private String extend;
    private int status;
    private String reserved1;
}
