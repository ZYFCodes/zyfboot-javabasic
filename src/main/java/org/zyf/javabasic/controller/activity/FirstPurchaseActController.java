package org.zyf.javabasic.controller.activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyf.javabasic.aop.bizdeal.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.aop.bizdeal.service.verify.VerifyAopService;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/11/10  23:22
 */
@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Slf4j
@Api(value = "活动类控制器")
public class FirstPurchaseActController {
    @Resource
    private VerifyAopService verifyAopService;

    @PostMapping("/policy4")
    @ApiOperation(value = "新增或者更新自动续费活动信息")
    public String addOrUpdatePolicy(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.queryLimitTimeActivityDetail((long) 34);
        return "";
    }
}
