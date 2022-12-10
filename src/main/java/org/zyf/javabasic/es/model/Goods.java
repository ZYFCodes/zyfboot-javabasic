package org.zyf.javabasic.es.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yanfengzhang
 * @description 商品数据模型 简化版
 * @date 2022/12/7  23:19
 */
@Data
public class Goods {
    /**
     * 商品编号
     */
    private Long id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品库存
     */
    private Integer stock;
    /**
     * 商品销售数量
     */
    private Integer saleNum;
    /**
     * 商品分类
     */
    private String categoryName;
    /**
     * 商品品牌
     */
    private String brandName;
    /**
     * 上下架状态
     */
    private Integer status;
    /**
     * 说明书
     */
    private String spec;
    /**
     * 商品创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
