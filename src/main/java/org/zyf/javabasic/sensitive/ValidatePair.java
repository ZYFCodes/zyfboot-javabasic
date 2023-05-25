package org.zyf.javabasic.sensitive;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:11
 */
@Data
public class ValidatePair {
    public String originalContent;
    public String content;
    public Integer field;

    public ValidatePair(String originalContent, String content, Integer field) {
        this.originalContent = originalContent;
        this.content = content;
        this.field = field;
    }

    public ValidatePair(String content, Integer field) {
        this.content = content;
        this.field = field;
    }
}
