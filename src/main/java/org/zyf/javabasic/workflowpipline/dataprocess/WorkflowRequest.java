package org.zyf.javabasic.workflowpipline.dataprocess;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 流程处理请求基本信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:22
 **/
@Data
public class WorkflowRequest implements Serializable {

    private static final long serialVersionUID = 2624255353146380487L;

    private List<Item> items;

    private String scene;

    private boolean isTestData;

    private Map<String, String> ext = Maps.newConcurrentMap();

    private Map<String, String> extInfo;

    public Map<String, String> getExt() {
        return ext;
    }

    public String getExt(String key) {
        if (MapUtils.isEmpty(ext)) {
            return null;
        }
        return ext.get(key);
    }

    public void setExt(String key, String value) {
        this.ext.put(key, value);
    }

    /**
     * Clean ext.
     */
    public void cleanExt() {
        this.ext = Maps.newConcurrentMap();
    }

    /**
     * Clean pip line.
     */
    public void cleanPipLine() {
        if (MapUtils.isNotEmpty(this.ext)) {
            this.ext.remove("pipeline.config");
        }
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * Equals boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass().equals(this.getClass()) && EqualsBuilder.reflectionEquals(obj, this);

    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}

