package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/21  16:21
 */
@Data
@Builder
public class WordRegular {
    /**
     * 主键id
     */
    private long id;
    /**
     * 正则配置类型
     */
    private int type;
    /**
     * 敏感词词条
     */
    @NotNull
    @Length(max = 2000)
    private String words;
}
