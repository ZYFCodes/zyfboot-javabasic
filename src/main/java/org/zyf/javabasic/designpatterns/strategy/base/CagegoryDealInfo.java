package org.zyf.javabasic.designpatterns.strategy.base;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

/**
 * @author yanfengzhang
 * @description 超范围经营类目处理整合信息
 * @date 2022/3/3  23:58
 */
@Data
@Builder
public class CagegoryDealInfo {
    /**
     * 平台当前配置的后台类目信息
     */
    private Collection<Long> bgCategoryIds;
    /**
     * 指定业务范围配置的后台类目信息
     */
    private Collection<Long> bgCategoryIdsForBizScope;
}
