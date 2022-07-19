package org.zyf.javabasic.generic.unifiedprocess.biz.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.bizdeal.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.generic.unifiedprocess.CheckResponse;
import org.zyf.javabasic.generic.unifiedprocess.CheckStatusEnum;
import org.zyf.javabasic.generic.unifiedprocess.UnifiedProcessCheckStrategy;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/2/9  22:20
 */
@Service
@Slf4j
public class AutoRenewalActivityCreateCheck extends UnifiedProcessCheckStrategy<AutoRenewalActivityDto> {

    @Override
    public CheckResponse checkBasicParam(AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("创建流程校验1：检查基本信息是否正确");
        Long id = autoRenewalActivityDto.getId();
        if (null == id || id <= 0) {
            CheckStatusEnum idError = CheckStatusEnum.ACTIVITY_ID_ERROR;
            return CheckResponse.error(idError.getValue(), idError.getDesc());
        }

        return null;
    }

    @Override
    public CheckResponse checkValid(AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("创建流程校验2：检查基本有效性是否正确");
        Integer activityType = autoRenewalActivityDto.getActivityType();
        if (activityType.toString().equals(ActivityBizConstants.ActivityBizType.AUTO_RENEMAL)) {
            CheckStatusEnum paramError = CheckStatusEnum.ACTIVITY_PARAM_ERROR;
            return CheckResponse.error(paramError.getValue(), paramError.getDesc());
        }

        return null;
    }

    @Override
    public CheckResponse checkConflict(AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("创建流程校验3：检查关联冲突是否正确");
        return null;
    }

    @Override
    public CheckResponse checkVerifyCode(AutoRenewalActivityDto autoRenewalActivityDto) throws Exception {
        log.info("创建流程校验4：检查基本验证码是否正确");
        return null;
    }
}
