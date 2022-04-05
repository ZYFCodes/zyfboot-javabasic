package org.zyf.javabasic.designpatterns.strategy.base;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 业务上个性化配置的规则
 * @date 2022/3/4  11:33
 */
@Data
@Builder
public class OverrangeBusinessScope {
    /**
     * 唯一标识
     */
    private long id;
    /**
     * 名称展示
     */
    private String name;
    /**
     * 类型：品牌或门店等，例如：1-品牌；2-门店
     */
    private int type;
    /**
     * 针对类型对应具体的影响范围
     * 例如类型为门店，则对应对应需要干预的门店ID：110、112、112、113等
     */
    private String effectRanges;
    /**
     * 控制规则：对原有配置的干预情况
     */
    private int controlRule;
    /**
     * 校验规则：对原有校验结果进行干预
     */
    private int validateRule;
    /**
     * 本次实际配置的类目信息
     */
    private List<Long> relationCates;
    /**
     * 创建时间
     */
    private long ctime;
    /**
     * 更新时间
     */
    private long utime;
    /**
     * 创建人
     */
    private String cname;
    /**
     * 修改人
     */
    private String uname;
}
