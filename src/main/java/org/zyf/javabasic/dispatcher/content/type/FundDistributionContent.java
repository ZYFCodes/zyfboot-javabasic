package org.zyf.javabasic.dispatcher.content.type;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

/**
 * @program: zyfboot-javabasic
 * @description: 基金机构内容分发数据
 * @author: zhangyanfeng
 * @create: 2024-04-21 14:40
 **/
@Data
public class FundDistributionContent extends DistributionContent {
    private static final long serialVersionUID = 8978907822086878625L;
    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金产品ID
     */
    private String productId;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 基金类型
     */
    private String fundType;

    /**
     * 判断基金分发内容是否有效
     *
     * @return 如果基金分发内容有效返回 true，否则返回 false
     */
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(fundCode) && StringUtils.isNotBlank(fundName)
                && StringUtils.isNotBlank(fundType) && StringUtils.isNotBlank(productId);
    }

    /**
     * 判断是否为机构分发类型
     *
     * @return 如果是机构分发返回 true，否则返回 false
     */
    public boolean isMechanism() {
        return true;
    }

    /**
     * 获取分发类型
     *
     * @return 分发分发
     */
    public DistributionType getDistributionType() {
        return DistributionType.FUND;
    }

    /**
     * 获取机构分发 ID
     *
     * @return 机构分发 ID
     */
    public String getMechanismId() {
        return productId;
    }

    /**
     * 获取机构分发名称
     *
     * @return 机构分发名称
     */
    public String getName() {
        return fundName;
    }

    /**
     * 返回基金分发内容的字符串表示形式
     *
     * @return 基金分发内容的字符串表示形式
     */
    @Override
    public String toString() {
        return "FundDistributionContent{" +
                "fundCode='" + fundCode + '\'' +
                ", productId='" + productId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", fundType='" + fundType + '\'' +
                '}';
    }
}
