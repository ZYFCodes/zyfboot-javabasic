package org.zyf.javabasic.workflowpipline.dataprocess;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 处理内容项信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:23
 **/
@Data
public class Item implements Serializable {

    private static final long serialVersionUID = -2116526396160568209L;

    /**
     * 内容id
     */
    private String itemId;

    /**
     * 内容类型
     */
    private String itemType;

    /**
     * 数据
     */
    private Object data;

    /**
     * 动态字段
     */
    private List<DynamicParam> dynamicParams;

    /**
     * 附加信息
     */
    private Map<String, Object> ext;


    public Object getExtInfo(String key) {
        if (MapUtils.isNotEmpty(ext)) {
            return ext.get(key);
        }
        return null;
    }

    public void putExt(Map map) {
        if (MapUtils.isEmpty(map)) {
            return;
        }
        if (MapUtils.isEmpty(ext)) {
            ext = Maps.newHashMap();
        }
        ext.putAll(map);
    }

    public void putExt(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        if (MapUtils.isEmpty(ext)) {
            ext = Maps.newHashMap();
        }
        ext.put(key, value);
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

    /**
     * 根据类型转换
     *
     * @param source 源数据
     * @param clazz  目标类型
     * @param <T>    泛型
     * @return 转换后的数据
     */
    public static <T> T getData(Object source, Class<T> clazz) {

        if (Objects.isNull(source)) {
            return null;
        }
        if (clazz.isInstance(source)) {
            return (T) source;
        }

        Object parse = JSON.parse(JSON.toJSONString(source));
        JSONObject jsonObject = JSONObject.parseObject(parse.toString());
        return jsonObject.toJavaObject(clazz);
    }
}

