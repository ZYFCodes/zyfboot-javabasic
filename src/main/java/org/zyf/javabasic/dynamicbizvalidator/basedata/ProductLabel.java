package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:52
 */
@Data
@Builder
public class ProductLabel {
    private long id;
    private int ctime;
    private int utime;
    private String groupName;
    private int groupId;
    private int groupSequence;
    private String subAttr;
    private int subSequence;
    private String name;
    private int labelType;
    private String reserved1;
}
