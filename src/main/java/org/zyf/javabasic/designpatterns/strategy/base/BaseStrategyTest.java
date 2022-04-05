package org.zyf.javabasic.designpatterns.strategy.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/4  23:53
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseStrategyTest {

    @Autowired
    private BizService bizService;

    @Test
    public void testBizFunction() throws Exception {
        log.info("业务场景1---业务原配置类目内容为（1，2，3），现在个性化处理配置需要在原类目基础上增加以下类目（4，5）");
        Long[] bgCategoryIdInfo1 = {1L, 2L, 3L};
        Collection<Long> bgCategoryIds1 = Arrays.asList(bgCategoryIdInfo1);
        Long[] relationCateInfo1 = {4L, 5L};
        List<Long> relationCates1 = Arrays.asList(relationCateInfo1);
        OverrangeBusinessScope overrangeBusinessScope1 = OverrangeBusinessScope.builder()
                .controlRule(OverrangeContants.BizScopeControlRule.NEED)
                .relationCates(relationCates1).build();
        log.info("业务场景1情况下最终得到的业务类目为：{}", bizService.getCagegoryIdsByControlRule(overrangeBusinessScope1, bgCategoryIds1));

        log.info("业务场景2---业务原配置类目内容为（1，2，3），现在个性化处理配置需要在原类目基础上不能包含以下类目（1，2）");
        Long[] bgCategoryIdInfo2 = {1L, 2L, 3L};
        Collection<Long> bgCategoryIds2 = Arrays.asList(bgCategoryIdInfo2);
        Long[] relationCateInfo2 = {1L, 2L};
        List<Long> relationCates2 = Arrays.asList(relationCateInfo2);
        OverrangeBusinessScope overrangeBusinessScope2 = OverrangeBusinessScope.builder()
                .controlRule(OverrangeContants.BizScopeControlRule.NO)
                .relationCates(relationCates2).build();

        log.info("业务场景2情况下最终得到的业务类目为：{}", bizService.getCagegoryIdsByControlRule(overrangeBusinessScope2, bgCategoryIds2));

        log.info("业务场景3---业务原配置类目内容为（1，2，3, 4, 5），现在个性化处理配置需要不在意原有配置，当前只能售卖类目为（2，5）");
        Long[] bgCategoryIdInfo3 = {1L, 2L, 3L, 4L, 5L};
        Collection<Long> bgCategoryIds3 = Arrays.asList(bgCategoryIdInfo3);
        Long[] relationCateInfo3 = {2L, 5L};
        List<Long> relationCates3 = Arrays.asList(relationCateInfo3);
        OverrangeBusinessScope overrangeBusinessScope3 = OverrangeBusinessScope.builder()
                .controlRule(OverrangeContants.BizScopeControlRule.ONLY)
                .relationCates(relationCates3).build();

        log.info("业务场景3情况下最终得到的业务类目为：{}", bizService.getCagegoryIdsByControlRule(overrangeBusinessScope3, bgCategoryIds3));

    }
}
