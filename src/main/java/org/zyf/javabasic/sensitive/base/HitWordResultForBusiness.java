package org.zyf.javabasic.sensitive.base;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 业务身份命中敏感词结果
 * @date 2021/10/14  15:21
 */
@Data
@Builder
public class HitWordResultForBusiness {
    /**
     * 命中的敏感词:true-命中；false-未命中
     */
    private boolean isHit;
    /**
     * 命中的敏感词具体信息
     */
    private WordDomain hitWord;
}
