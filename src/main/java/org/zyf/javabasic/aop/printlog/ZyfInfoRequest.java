package org.zyf.javabasic.aop.printlog;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/13  23:56
 */
@Data
@Builder
public class ZyfInfoRequest {
    /**
     * 基本信息
     */
    private boolean needBasicInfo;
    /**
     * 博客信息
     */
    private boolean needBlogInfo;
}
