package org.zyf.javabasic.designpatterns.strategy.base;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 返回需包含指定类目信息
 * @date 2022/3/3  23:33
 */
@ControlRuleHandlerType(controlRuleType = OverrangeContants.BizScopeControlRule.NEED)
@Service
public class ControlRuleForIncludeHandler implements ControlRuleHandler {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 需要在原类目基础上增加指定类目信息
     *
     * @param cagegoryDealInfo 需要的处理的类目信息
     * @return
     */
    @Override
    public Collection<Long> getCagegoryIds(CagegoryDealInfo cagegoryDealInfo) throws Exception {
        Collection<Long> bgCategoryIds = cagegoryDealInfo.getBgCategoryIds();
        Collection<Long> bgCategoryIdsForBizScope = cagegoryDealInfo.getBgCategoryIdsForBizScope();
        /*若两边没有配置返回为空*/
        if (CollectionUtils.isEmpty(bgCategoryIds) && CollectionUtils.isEmpty(bgCategoryIdsForBizScope)) {
            return bgCategoryIds;
        }

        /*将信息整合全部返回*/
        Collection<Long> finalCategoryIds = new java.util.ArrayList<Long>(Collections.EMPTY_SET);
        /*业务上找到联级关系全部的*/
        Set<Long> allCategorysForBizScope = new HashSet<>(productCategoryService.fetchAllIdPathByCategoryId(bgCategoryIdsForBizScope));
        finalCategoryIds.addAll(bgCategoryIds);
        finalCategoryIds.addAll(allCategorysForBizScope);
        return finalCategoryIds;
    }
}

