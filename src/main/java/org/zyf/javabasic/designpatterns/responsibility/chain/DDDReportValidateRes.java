package org.zyf.javabasic.designpatterns.responsibility.chain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 领域驱动设计产物报告提交内容校验结果反馈
 * @date 2022/4/1  23:15
 */
@Data
@Builder
public class DDDReportValidateRes {
    /**
     * 报告是否符合产出 true-符合要求；false-不符合规范
     */
    private boolean legal;
    /**
     * 如果报告不符合要求，给出详细原因
     */
    private List<String> detailReasons;
}
