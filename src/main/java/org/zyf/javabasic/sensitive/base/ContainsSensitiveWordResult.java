package org.zyf.javabasic.sensitive.base;

import lombok.Data;

import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:56
 */
@Data
public class ContainsSensitiveWordResult {
    public boolean illegal;
    public String content;
    public Set<String> sensitiveWordSet;
    public Set<String> sensitiveWordInWhiteListSet;
    public Set<SensitiveWordWithType> sensitiveWordWithTypeSet;
    public Set<SensitiveWordWithMuslim> sensitiveWordWithMuslimSet;
    public Set<SensitiveWordWithQua> sensitiveWordWithQueSet;

    public ContainsSensitiveWordResult(boolean illegal, String content, Set<String> sensitiveWordSet, Set<String> sensitiveWordInWhiteListSet, Set<SensitiveWordWithType> sensitiveWordWithTypeSet, Set<SensitiveWordWithMuslim> sensitiveWordWithMuslimSet, Set<SensitiveWordWithQua> sensitiveWordWithQueSet) {
        this.illegal = illegal;
        this.content = content;
        this.sensitiveWordSet = sensitiveWordSet;
        this.sensitiveWordInWhiteListSet = sensitiveWordInWhiteListSet;
        this.sensitiveWordWithTypeSet = sensitiveWordWithTypeSet;
        this.sensitiveWordWithMuslimSet = sensitiveWordWithMuslimSet;
        this.sensitiveWordWithQueSet = sensitiveWordWithQueSet;
    }
}
