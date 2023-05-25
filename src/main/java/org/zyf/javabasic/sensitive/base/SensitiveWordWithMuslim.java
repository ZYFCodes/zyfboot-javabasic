package org.zyf.javabasic.sensitive.base;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  19:58
 */
@Data
@Builder
public class SensitiveWordWithMuslim {
    public int type;
    public String sensitiveWord;
}
