package org.zyf.javabasic.dispatcher.content.type;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

/**
 * @program: zyfboot-javabasic
 * @description: 股票机构内容分发数据
 * @author: zhangyanfeng
 * @create: 2024-04-21 14:48
 **/
@Data
public class StockDistributionContent extends DistributionContent {

    private static final long serialVersionUID = -8249743340967686013L;

    /**
     * 股票代码及市场信息
     */
    private String stockCodeMarket;

    /**
     * 股票 ID，由系统分配
     */
    private String stockId;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票数据类型
     */
    private String stockType;

    /**
     * 判断股票分发内容是否有效
     *
     * @return 如果股票分发内容有效返回 true，否则返回 false
     */
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(stockCodeMarket) && StringUtils.isNotBlank(stockName)
                && StringUtils.isNotBlank(stockId) && StringUtils.isNotBlank(stockType);
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
        return DistributionType.STOCK;
    }

    /**
     * 获取机构分发 ID
     *
     * @return 机构分发 ID
     */
    public String getMechanismId() {
        return stockId;
    }

    /**
     * 获取机构分发名称
     *
     * @return 机构分发名称
     */
    public String getName() {
        return stockName;
    }

    /**
     * 返回股票分发内容的字符串表示形式
     *
     * @return 股票分发内容的字符串表示形式
     */
    @Override
    public String toString() {
        return "StockDistributionContent{" +
                "stockCodeMarket='" + stockCodeMarket + '\'' +
                ", stockId='" + stockId + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stockType='" + stockType + '\'' +
                ", dataType='" + getDataType() + '\'' +
                '}';
    }
}
