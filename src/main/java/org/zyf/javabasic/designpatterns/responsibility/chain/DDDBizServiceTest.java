package org.zyf.javabasic.designpatterns.responsibility.chain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/2  23:52
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DDDBizServiceTest {
    @Autowired
    private DDDBizService dddBizService;

    @Test
    public void testDDDBizService() {
        DDDProductReport dddProductReport = DDDProductReport.builder()
                .globalAnalysisSpec(GlobalAnalysisSpec.builder().valueNeeds("利益相关者 + 系统愿景 + 系统范围（当前状态、未来状态、业务目标）")
                        .businessNeeds("业务描述与子领域规划分析（核心域、支撑域、通用域）")
                        .businessProcess("核心业务流程 + 主要业务流程")
                        .subFieldAnalysis("业务场景+业务服务（编号、名称、描述、触发事件、基本流程、替代流程、验收标准）").build())
                .architectureMappingScheme(ArchitectureMappingScheme.builder()
                        .systemContext("系统上下文").applicationArchitecture("业务组件 + 应用架构视图").businessrchitecture("业务组件 + 业务架构视图")
                        .subFieldAnalysis("核心域、支撑域、通用域").build())
                .domainModelBuildProduct(DomainModelBuildProduct.builder().build()).build();

        dddBizService.organizationalReview(dddProductReport);
    }
}
