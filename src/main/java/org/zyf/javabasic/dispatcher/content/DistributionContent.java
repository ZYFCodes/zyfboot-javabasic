package org.zyf.javabasic.dispatcher.content;

import lombok.Data;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 分发内容基本信息
 * 包含了分发内容的基本信息，如内容字符串、起始和结束位置等。
 * @author: zhangyanfeng
 * @create: 2024-04-21 12:37
 **/
@Data
public abstract class DistributionContent implements Serializable {

    private static final long serialVersionUID = -4265287482149733430L;

    /**
     * 分发内容的字符串表示
     */
    private String contentString = "";

    /**
     * 分发内容在原始文本中的起始位置
     */
    private int startOffset;

    /**
     * 分发内容在原始文本中的结束位置
     */
    private int endOffset;

    /**
     * 数据类型（用于前端展示）
     */
    private String dataType;

    /**
     * 判断分发内容是否有效
     *
     * @return 如果分发内容有效返回 true，否则返回 false
     */
    public abstract boolean isValid();

    /**
     * 判断是否为机构分发类型
     *
     * @return 如果是机构分发返回 true，否则返回 false
     */
    public boolean isMechanism() {
        return false;
    }

    /**
     * 获取分发类型
     *
     * @return 分发分发
     */
    public DistributionType getDistributionType() {
        return null;
    }

    /**
     * 获取机构分发 ID
     *
     * @return 机构分发 ID
     */
    public String getMechanismId() {
        return null;
    }

    /**
     * 获取机构分发名称
     *
     * @return 机构分发名称
     */
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "DistributionContent [contentString=" + contentString + ", startOffset=" + startOffset
                + ", endOffset=" + endOffset + "]";
    }
}