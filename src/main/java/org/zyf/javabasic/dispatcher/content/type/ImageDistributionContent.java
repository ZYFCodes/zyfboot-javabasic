package org.zyf.javabasic.dispatcher.content.type;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

/**
 * @program: zyfboot-javabasic
 * @description: 图片分发内容信息
 * @author: zhangyanfeng
 * @create: 2024-04-21 15:36
 **/
@Data
public class ImageDistributionContent extends DistributionContent {

    private static final long serialVersionUID = 1643435689225119915L;
    /**
     * 图片链接指向的地址信息
     */
    private String imgLink = "";

    /**
     * 扩展参数
     */
    private String param = "";

    /**
     * 判断图片分发内容是否有效
     *
     * @return 如果图片分发内容有效返回 true，否则返回 false
     */
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(imgLink);
    }

    /**
     * 获取分发类型
     *
     * @return 分发类型
     */
    public DistributionType getDistributionType() {
        return DistributionType.IMAGE;
    }

    /**
     * 返回对象的字符串表示形式
     *
     * @return 对象的字符串表示形式
     */
    @Override
    public String toString() {
        return "ImageDistributionContent{" +
                "imgLink='" + imgLink + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
