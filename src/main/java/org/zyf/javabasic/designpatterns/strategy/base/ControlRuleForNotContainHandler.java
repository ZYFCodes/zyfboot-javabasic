package org.zyf.javabasic.designpatterns.strategy.base;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 返回不得包含指定类目信息
 * @date 2022/3/3  23:13
 */
@ControlRuleHandlerType(controlRuleType = OverrangeContants.BizScopeControlRule.NO)
@Service
public class ControlRuleForNotContainHandler implements ControlRuleHandler {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 需要在原类目基础上去掉不得包含指定类目信息
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

        if (CollectionUtils.isEmpty(bgCategoryIds)) {
            return bgCategoryIds;
        }

        /*业务上找到联级关系全部的*/
        Set<Long> allCategorysForBizScope = new HashSet<>(productCategoryService.fetchAllIdPathByCategoryId(bgCategoryIdsForBizScope));
        return bgCategoryIds.stream().filter(bgCategoryId -> !allCategorysForBizScope.contains(bgCategoryId)).collect(Collectors.toList());
    }
}

