package org.zyf.javabasic.controller.activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zyf.javabasic.aop.bizdeal.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.aop.bizdeal.entity.dto.LimitTimeActivityDto;
import org.zyf.javabasic.aop.bizdeal.service.verify.VerifyAopService;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description 活动类公用请求整理
 * @date 2020/11/10  23:31
 */
@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@Slf4j
@Api(value = "活动类控制器")
public class ActivityController {

    @Resource
    private VerifyAopService verifyAopService;

    @PostMapping("/policy")
    @ApiOperation(value = "新增或者更新自动续费活动信息")
    public String addOrUpdatePolicy(@RequestBody AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("新增或者更新自动续费活动信息,autoRenewalActivityDto:{}", autoRenewalActivityDto);
        verifyAopService.queryLimitTimeActivityDetail((long) 34);
        return "";
    }

    @DeleteMapping("/policy")
    @ApiOperation("通过活动id来删除活动信息")
    public String deletePolicy(@RequestParam("policyId") String policyId) throws Exception {

        return "";
    }

    @GetMapping("/policies")
    @ApiOperation("查询策略")
    public String getPolicies(@RequestParam(value = "activityId", defaultValue = "0") Integer activityId) {
        log.info("查询活动信息,activityId:{}", activityId);
        LimitTimeActivityDto limitTimeActivityDto = verifyAopService.queryLimitTimeActivityDetail((long) 34);
        log.info("limitTimeActivityDto=={}", limitTimeActivityDto);
        return "";
    }
}
