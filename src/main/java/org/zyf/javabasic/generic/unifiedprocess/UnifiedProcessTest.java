package org.zyf.javabasic.generic.unifiedprocess;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyf.javabasic.aop.complex.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.generic.unifiedprocess.biz.activity.AutoRenewalActivityCreateCheck;
import org.zyf.javabasic.generic.unifiedprocess.biz.activity.AutoRenewalActivityDeleteCheck;
import org.zyf.javabasic.generic.unifiedprocess.biz.activity.AutoRenewalActivityUpdateCheck;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/2/10  22:24
 */
@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Slf4j
@Api(value = "泛型统一化流程控制器")
public class UnifiedProcessTest {
    @Autowired
    AutoRenewalActivityCreateCheck autoRenewalActivityCreateCheck;
    @Autowired
    AutoRenewalActivityUpdateCheck autoRenewalActivityUpdateCheck;
    @Autowired
    AutoRenewalActivityDeleteCheck autoRenewalActivityDeleteCheck;

    @PostMapping("/UnifiedProcessTest")
    @ApiOperation(value = "统一化校验流程处理测试")
    public String unifiedProcessTest(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("统一化校验流程处理测试:autoRenewalActivityDto:{}", autoRenewalActivityDto);
        log.info("------------新建自动续费相关检查统一化流程执行开始------------");
        CheckResponse autoRenewalActivityCreateCheckResult = autoRenewalActivityCreateCheck.checkProcess(autoRenewalActivityDto);
        if (null != autoRenewalActivityCreateCheckResult) {
            log.info("校验不通过，具体如下：{}", autoRenewalActivityCreateCheckResult);
        }
        log.info("------------新建自动续费相关检查统一化流程执行结束------------");

        log.info("------------更新自动续费相关检查统一化流程执行开始------------");
        CheckResponse autoRenewalActivityUpdateCheckResult = autoRenewalActivityUpdateCheck.checkProcess(autoRenewalActivityDto);
        if (null != autoRenewalActivityUpdateCheckResult) {
            log.info("校验不通过，具体如下：{}", autoRenewalActivityUpdateCheckResult);
        }
        log.info("------------更新自动续费相关检查统一化流程执行结束------------");

        log.info("------------删除自动续费相关检查统一化流程执行开始------------");
        CheckResponse autoRenewalActivityDeleteCheckResult = autoRenewalActivityDeleteCheck.checkProcess(autoRenewalActivityDto);
        if (null != autoRenewalActivityDeleteCheckResult) {
            log.info("校验不通过，具体如下：{}", autoRenewalActivityDeleteCheckResult);
        }
        log.info("------------删除自动续费相关检查统一化流程执行结束------------");
        return "执行结束";
    }
}
