package org.zyf.javabasic.aop.bizdeal.entity.dto;

import lombok.Data;

/**
 * @program: zyfboot-javabasic
 * @description: 用户基本信息
 * @author: zhangyanfeng
 * @create: 2025-04-18 13:10
 **/
@Data
public class CsdnUserInfo {
    private String userIdentification;
    private String pwdOrVerifyCode;
}
