package org.zyf.javabasic.sensitive.base;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  19:57
 */
@Data
@Builder
public class SensitiveWordWithType {
    public int type;
    public List<String> sensitiveWord;
}
