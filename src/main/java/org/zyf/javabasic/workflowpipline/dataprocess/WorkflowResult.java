package org.zyf.javabasic.workflowpipline.dataprocess;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.zyf.javabasic.common.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 处理结果信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:40
 **/
@Data
public class WorkflowResult implements Serializable {

    private static final long serialVersionUID = 1227668421930594583L;

    private List<Item> items;

    private List<User> users;


    private Map<String, String> extAttrs;

    public void addExtAttr(String key, String value) {
        if (MapUtils.isEmpty(this.extAttrs)) {
            this.extAttrs = Maps.newHashMap();
        }

        this.extAttrs.put(key, value);
    }

    public String getExtAttr(String key) {
        if (MapUtils.isEmpty(this.extAttrs)) {
            return null;
        }
        return (String) this.extAttrs.get(key);
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass().equals(this.getClass()) && EqualsBuilder.reflectionEquals(obj, this);

    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
