package org.zyf.javabasic.dynamicbizvalidator.enums;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/26  23:52
 */
public enum PoiOwnerType {
    ALL(0, "全部"),
    KA(1, "大连锁商家"),
    CKA(2, "小连锁商家"),
    CITY(3, "城市商家"),
    FLOW_CHAIN(4, "流量连锁商家");

    /**
     * 商家类型处理:0:全部 1:大连锁商家(KA)，2:小连锁商家(CKA)，3:城市商家，4:流量连锁商家
     */
    private Integer code;
    /**
     * 商家类型描述
     */
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    PoiOwnerType(Integer code) {
        this.code = code;
    }

    PoiOwnerType(Integer code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 根据商家类型处理code获取对应的商家类型
     *
     * @param code 商家类型处理code
     * @return
     */
    public static PoiOwnerType getEnumById(Integer code) {
        for (PoiOwnerType poiOwnerType : PoiOwnerType.values()) {
            if (poiOwnerType.getCode().equals(code)) {
                return poiOwnerType;
            }
        }
        return null;
    }

    /**
     * 根据商家类型处理描述获取对应的商家类型
     *
     * @param desc 商家类型处理描述
     * @return
     */
    public static PoiOwnerType getEnumByDesc(String desc) {
        for (PoiOwnerType poiOwnerType : PoiOwnerType.values()) {
            if (poiOwnerType.getDesc().equals(desc)) {
                return poiOwnerType;
            }
        }
        return null;
    }

    /**
     * 判断商家类型code是否在指定范围
     *
     * @param code 商家类型处理code
     * @return true-在指定范围
     */
    public static boolean isPoiOwnerType(Integer code) {
        if (null == code) {
            return false;
        }
        for (PoiOwnerType tempEnum : PoiOwnerType.values()) {
            if (tempEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ownerType 门店归属（owner_type）
     * *      * BIG_CHAIN(1, "大连锁"),
     * *      * CITY(2, "城市"),
     * *      * SMALL_CHAIN(3, "CKA"),
     * *      * CRM(4, "供应链"),
     * *      * LING_SHOU(5, "总部商超连锁"),
     * *      * SHENG_XIAN(6, "总部生鲜连锁"),
     * *      * XIAN_HUA(8, "总部鲜花连锁"),
     * *      * YAO_PIN(9, "总部药品连锁"),
     * *      * MU_YING(10, "总部母婴连锁"),
     * *      * MEI_ZHUANG(11, "总部美妆连锁"),
     * *      * FU_SHI(12, "总部服饰鞋帽连锁"),
     * *      * RI_YONG(13, "总部日用品连锁"),
     * *      * FLOW_CHAIN(14, "流量连锁");
     * *      *
     * *      * 对于未绑定品牌的门店，属于城市门店，其门店归属为 -1 （不存在）或 0（默认值）
     * *      *
     * *      * 与商家类型对应:0:全部 1:大连锁商家(KA)，2:小连锁商家(CKA)，3:城市商家，4:流量连锁商家。
     *
     * @param ownerType
     * @return
     */
    public static PoiOwnerType getPoiOwnerTypeeByBiz(int ownerType) {
        if (ownerType == BrandTypeEnum.BIG_CHAIN.getOwnerType()) {
            return PoiOwnerType.KA;
        }
        if (ownerType == BrandTypeEnum.SMALL_CHAIN.getOwnerType()) {
            return PoiOwnerType.CKA;
        }
        /*-1 是 没有绑定品牌，相当于散点，默认它是城市   https://km.sankuai.com/page/1116486676 */
        if (ownerType == BrandTypeEnum.CITY.getOwnerType() || ownerType == -1) {
            return PoiOwnerType.CITY;
        }

        if (ownerType == BrandTypeEnum.FLOW_CHAIN.getOwnerType()) {
            return PoiOwnerType.FLOW_CHAIN;
        }

        return PoiOwnerType.ALL;
    }
}
