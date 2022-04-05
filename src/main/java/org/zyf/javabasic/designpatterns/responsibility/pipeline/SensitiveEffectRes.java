package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 最终生效的敏感词
 * @date 2022/4/5  17:21
 */
@Builder
@Data
public class SensitiveEffectRes {
    /**
     * 用户输入文本原稿
     */
    private String content;
    /**
     * 是否命中敏感词
     */
    private Boolean isHit;
    /**
     * 生效的敏感词
     */
    private List<SensitiveWord> hitWords;
}
