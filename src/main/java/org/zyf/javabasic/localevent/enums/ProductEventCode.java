package org.zyf.javabasic.localevent.enums;

/**
 * @program: zyfboot-javabasic
 * @description: 商品类的本地事件码
 * @author: zhangyanfeng
 * @create: 2024-04-27 19:09
 **/
public enum ProductEventCode {

    /** 创建商品 */
    CREATE_PRODUCT("CREATE_PRODUCT", "创建商品"),

    /** 更新商品信息 */
    UPDATE_PRODUCT("UPDATE_PRODUCT", "更新商品信息"),

    /** 删除商品 */
    DELETE_PRODUCT("DELETE_PRODUCT", "删除商品"),

    /** 将商品加入购物车 */
    ADD_TO_CART("ADD_TO_CART", "将商品加入购物车"),

    /** 从购物车移除商品 */
    REMOVE_FROM_CART("REMOVE_FROM_CART", "从购物车移除商品"),

    /** 生成订单 */
    GENERATE_ORDER("GENERATE_ORDER", "生成订单"),

    /** 取消订单 */
    CANCEL_ORDER("CANCEL_ORDER", "取消订单"),

    /** 支付订单 */
    PAY_ORDER("PAY_ORDER", "支付订单"),

    /** 退款订单 */
    REFUND_ORDER("REFUND_ORDER", "退款订单"),

    ;

    private final String eventCode;
    private final String description;

    ProductEventCode(String eventCode, String description) {
        this.eventCode = eventCode;
        this.description = description;
    }

    /**
     * 获取事件码
     *
     * @return 事件码
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * 获取事件描述
     *
     * @return 事件描述
     */
    public String getDescription() {
        return description;
    }
}

