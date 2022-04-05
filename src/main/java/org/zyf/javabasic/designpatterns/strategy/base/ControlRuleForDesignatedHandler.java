package org.zyf.javabasic.designpatterns.strategy.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author yanfengzhang
 * @description 只返回指定类目信息
 * @date 2022/3/3  23:37
 */
@ControlRuleHandlerType(controlRuleType = OverrangeContants.BizScopeControlRule.ONLY)
@Service
public class ControlRuleForDesignatedHandler implements ControlRuleHandler {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 只返回指定类目信息
     *
     * @param cagegoryDealInfo 需要的处理的类目信息
     * @return
     */
    @Override
    public Collection<Long> getCagegoryIds(CagegoryDealInfo cagegoryDealInfo) throws Exception {
        return productCategoryService.fetchAllIdPathByCategoryId(cagegoryDealInfo.getBgCategoryIdsForBizScope());
    }
}
