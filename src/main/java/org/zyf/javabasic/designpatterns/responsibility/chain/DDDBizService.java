package org.zyf.javabasic.designpatterns.responsibility.chain;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description 领域驱动设计报告交互服务
 * @date 2022/4/2  23:43
 */
@Service
public class DDDBizService {

    /**
     * 组织评审
     */
    public void organizationalReview(DDDProductReport dddProductReport) {
        /*1.构建链路形成实际检验链路*/
        DDDAnalysisHandler.Builder builder = new DDDAnalysisHandler.Builder();
        builder.add(new ReportIntegrityValidate()).add(new GlobalAnalysisSpecValidate())
                .add(new ArchitectureMappingValidate()).add(new DomainModelBuildValidate());

        System.out.println("===准备进行组织评审，对应领域驱动设计产物报告已提交，正在进行机审处理分析！===");
        /*2.先进行基本报告的分析，机审通过后在进行相关人员通知*/
        DDDReportValidateRes dddReportValidateRes = DDDReportValidateRes.builder().legal(true).detailReasons(Lists.newArrayList()).build();
        builder.build().validateDDDHandler(dddProductReport, dddReportValidateRes);
        if (!dddReportValidateRes.isLegal()) {
            System.out.println("===领域驱动设计产物报告已提交，但机审未通过，具体原因如下：===");
            dddReportValidateRes.getDetailReasons().forEach(System.out::println);
            System.out.println("===组织评审发起失败！请按机审结果进行修正相关内容重新发起！===");
            return;
        }

        /*3.通知相关人员评审*/
        System.out.println("===机审处理分析已通过，正在通知相关人员进行确认评审时间===");
        System.out.println("===相关人员进行确认评审时间已确定，已发送具体评审时间与地点！===");
    }
}
