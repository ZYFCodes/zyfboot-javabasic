package org.zyf.javabasic.workflowpipline.core;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.zyf.javabasic.workflowpipline.constants.PipelineConstant;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowRequest;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowResult;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 动态配置工作流执行上下文信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:19
 **/
@Data
public class WorkflowExecutionContext implements Serializable {

    private static final long serialVersionUID = 1180080154658968619L;

    /**
     * 上下文变量
     */
    private Map<String, Object> contextAttrs = Maps.newConcurrentMap();

    /**
     * 请求信息
     */
    private WorkflowRequest request = new WorkflowRequest();

    /**
     * 结果集信息
     */
    private WorkflowResult result = new WorkflowResult();


    public String getWorkflowExecutionConfig() {
        return getContextAttr(PipelineConstant.PIPELINE_CONFIG);
    }

    /**
     * 获取上下文属性
     */
    @SuppressWarnings("unchecked")
    public <T> T getContextAttr(String key) {
        if (MapUtils.isEmpty(contextAttrs)) {
            return null;
        }
        return (T) contextAttrs.get(key);
    }

    public void setContextAttr(String key, Object val) {
        if (MapUtils.isEmpty(contextAttrs)) {
            contextAttrs = Maps.newConcurrentMap();
        }
        contextAttrs.put(key, val);
    }

    /**
     * 获取业务动态工作流相关请求参数
     */
    public String getBizWorkflowConfig(String key) {
        return request.getExt(key);

    }

    public String getBizWorkflowConfig(String key, String defaultValue) {
        String value = getBizWorkflowConfig(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    public boolean getBizWorkflowParamBoolean(String key, boolean defaultValue) {
        try {
            String result = getBizWorkflowConfig(key);
            return Boolean.parseBoolean(result);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public int getSceneParamInt(String key, int defaultValue) {
        try {
            String result = getBizWorkflowConfig(key);
            return NumberUtils.toInt(result, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 过去指定配置信息
     * @param key 指定配置
     * @param defaultValue 默认值
     * @param scenarioIdentifier 用于标识特定场景或情景
     * @return
     */
    public String getBizWorkflowConfig(String key, String defaultValue, String scenarioIdentifier) {
        return StringUtils.defaultIfBlank(getBizWorkflowConfig(scenarioIdentifier.concat(key)), getBizWorkflowConfig(key, defaultValue));
    }

    public String getSceneConfigWithIdentifier(String key, String scenarioIdentifier) {
        return StringUtils.defaultIfBlank(getBizWorkflowConfig(scenarioIdentifier.concat(key)), getBizWorkflowConfig(key));
    }

    /**
     * 设置RecResult
     */
    public WorkflowExecutionContext result(WorkflowResult recResult) {
        this.result = recResult;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
