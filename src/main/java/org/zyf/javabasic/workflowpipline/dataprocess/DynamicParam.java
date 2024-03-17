package org.zyf.javabasic.workflowpipline.dataprocess;

import lombok.Data;
import org.zyf.javabasic.workflowpipline.enums.OrgTypeEnum;
import org.zyf.javabasic.workflowpipline.enums.ValueTypeEnum;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 动态参数信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:28
 **/
@Data
public class DynamicParam {

    /**
     * 字段名称
     */
    private String key;

    /**
     * 字段值
     */
    private String value;

    /**
     * 值类型
     */
    private ValueTypeEnum valueType;

    /**
     * 组织类型
     */
    private OrgTypeEnum orgType;

    /**
     * 目标数据源类型
     */
    private List<String> targetSourceTypeList;
}


