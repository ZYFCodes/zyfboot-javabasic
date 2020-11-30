package org.zyf.javabasic.aop.complex.controller.activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyf.javabasic.aop.complex.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.aop.complex.service.verify.VerifyAopService;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/11/10  23:19
 */
@RestController
@RequestMapping(value = "/api/v1/activity/autorenewal", produces = {"application/json"})
@Slf4j
@Api(value = "活动类控制器")
public class AutoRenewalActController {
    @Resource
    private VerifyAopService verifyAopService;

    @PostMapping("/update")
    @ApiOperation(value = "新增或者更新自动续费活动信息")
    public String addOrUpdateAutoRenewalActivity(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.addOrUpdateAutoRenewalActivityDetail(autoRenewalActivityDto);
        return "";
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询自动续费活动信息")
    public String queryAutoRenewalActivity(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.queryLimitTimeActivityDetail((long) 34);
        return "";
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除自动续费活动信息")
    public String deleteAutoRenewalActivity(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.queryLimitTimeActivityDetail((long) 34);
        return "";
    }

    @PostMapping("/online")
    @ApiOperation(value = "删除自动续费活动信息")
    public String onlineAutoRenewalActivity(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.queryLimitTimeActivityDetail((long) 34);
        return "";
    }

    @PostMapping("/offline")
    @ApiOperation(value = "删除自动续费活动信息")
    public String offlineAutoRenewalActivity(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.queryLimitTimeActivityDetail((long) 34);
        return "";
    }
}
