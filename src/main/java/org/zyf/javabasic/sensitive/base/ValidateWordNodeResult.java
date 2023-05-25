package org.zyf.javabasic.sensitive.base;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:15
 */
@Data
@Builder
public class ValidateWordNodeResult {
    /**
     * 命中的敏感词记录
     */
    private String sensitive;
    /**
     * 当isLast=true时，存储当前敏感词的信息 （此处的信息是在构建树时进行的操作，没有做过太多转换）
     */
    private String wordContent;
}
