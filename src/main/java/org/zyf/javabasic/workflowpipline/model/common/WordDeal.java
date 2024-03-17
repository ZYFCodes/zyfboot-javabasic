package org.zyf.javabasic.workflowpipline.model.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 文本内容分析结果
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:14
 **/
@Data
@Builder
public class WordDeal {
    /**
     * 关键词
     */
    private List<String> keyword;

    /**
     * 实体词
     */
    private List<String> entityWord;
}