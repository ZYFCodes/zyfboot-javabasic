package org.zyf.javabasic.designpatterns.strategy.base;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/4  23:29
 */
@Service
@Log4j2
public class BizService {

    private Map<Integer, ControlRuleHandler> brandControlRuleHandleMap;

    /**
     * 启动初始化各种类型的品牌控制规则处理类（通过ControlRuleHandlerType注解）
     *
     * @param brandControlRuleHandlers
     */
    @Autowired
    public void setBrandControlRuleHandleMap(List<ControlRuleHandler> brandControlRuleHandlers) {
        // 注入各种类型的品牌控制规则处理类
        brandControlRuleHandleMap = brandControlRuleHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), ControlRuleHandlerType.class).controlRuleType(),
                        v -> v, (v1, v2) -> v1));
    }

    /**
     * 根据控制规则获取实际类目配置
     *
     * @param overrangeBusinessScope 个性化干预配置
     * @param bgCategoryIds          实际现存配置
     * @return 最终结果
     * @throws Exception 业务异常
     */
    public Collection<Long> getCagegoryIdsByControlRule(OverrangeBusinessScope overrangeBusinessScope, Collection<Long> bgCategoryIds) throws Exception {
        if (null == overrangeBusinessScope && CollectionUtils.isEmpty(overrangeBusinessScope.getRelationCates())) {
            return Lists.newArrayList();
        }
        /*1.获取指定控制范围内容*/
        ControlRuleHandler controlRuleHandler = brandControlRuleHandleMap.get(overrangeBusinessScope.getControlRule());

        /*2.分析对应指定类目信息与原类目信息进行处理*/
        Collection<Long> bgCategoryIdsForBrand = overrangeBusinessScope.getRelationCates();

        CagegoryDealInfo cagegoryDealInfo = CagegoryDealInfo.builder()
                .bgCategoryIds(bgCategoryIds)
                .bgCategoryIdsForBizScope(bgCategoryIdsForBrand)
                .build();
        return controlRuleHandler.getCagegoryIds(cagegoryDealInfo);
    }
}
