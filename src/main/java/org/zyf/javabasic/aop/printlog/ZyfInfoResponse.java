package org.zyf.javabasic.aop.printlog;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/13  23:56
 */
@Data
@Builder
public class ZyfInfoResponse {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年纪
     */
    private int age;
    /**
     * 身高：cm
     */
    private float height;
    /**
     * 体重：kg
     */
    private float weight;
    /**
     * 博客信息展示
     */
    private List<String> blogShowcases;
}
