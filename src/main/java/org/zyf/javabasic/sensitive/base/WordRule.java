package org.zyf.javabasic.sensitive.base;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:52
 */
@Data
public class WordRule {
    private long id;
    private long wordId;
    private String poiTagId;
    private String poiRegionId;
    private String field;
    private int nlp;
    private String remark;
    private int type;
    private int scene;
    private int merchantType;
}
