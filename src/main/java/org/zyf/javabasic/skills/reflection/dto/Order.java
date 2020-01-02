package org.zyf.javabasic.skills.reflection.dto;

import lombok.Data;

import java.security.Timestamp;

/**
 * 描述：订单
 *
 * @author yanfengzhang
 * @date 2020-01-02 16:55
 */
@Data
public class Order {
    /**
     * 订单创建时间
     */
    private Timestamp ctime;
    /**
     * 订单uuid
     */
    private String uuid;
    /**
     * 订单对应商品id
     */
    private Integer productId;
    /**
     * 订单总价，单位分
     */
    private Long price;
    /**
     * 购买商品数量
     */
    private Integer amount;
    /**
     * 出售人ID
     */
    private Integer sellerId;
    /**
     * 购买人ID
     */
    private Integer buyerId;
}
