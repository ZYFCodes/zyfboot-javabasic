package org.zyf.javabasic.dynamicbizvalidator.enums;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/26  23:52
 */
public enum BrandTypeEnum {
    CITY(0, "城市", 1, BrandCategoryEnum.WM, 2),
    BIG_CHAIN(3, "大连锁", 4, BrandCategoryEnum.WM, 1),
    CRM(4, "供应链", 0, BrandCategoryEnum.WM, 4),
    SMALL_CHAIN(7, "CKA", 1, BrandCategoryEnum.WM, 3),
    LING_SHOU(5, "总部商超连锁", 12, BrandCategoryEnum.SG, 5),
    XIAN_GUO(6, "总部生鲜连锁", 12, BrandCategoryEnum.SG, 6),
    XIAN_HUA(8, "总部鲜花连锁", 12, BrandCategoryEnum.SG, 8),
    YAO_PIN(9, "总部药品连锁", 12, BrandCategoryEnum.SG, 9),
    MU_YING(10, "总部母婴连锁", 12, BrandCategoryEnum.SG, 10),
    MEI_ZHUANG(11, "总部美妆连锁", 12, BrandCategoryEnum.SG, 11),
    FU_SHI(12, "总部服饰鞋帽连锁", 12, BrandCategoryEnum.SG, 12),
    RI_YONG(13, "总部日用品连锁", 12, BrandCategoryEnum.SG, 13),
    FLOW_CHAIN(14, "流量连锁", 1, BrandCategoryEnum.WM, 14),
    SG_SMALL_CHAIN(15, "闪购小连锁", 12, BrandCategoryEnum.SG, 15);

    private int value;
    private String desc;
    @Deprecated
    private int orgSource;

    public int getOwnerType() {
        return ownerType;
    }

    private int ownerType;
    /**
     * 品牌类别（外卖||闪购）
     */
    private BrandCategoryEnum category;

    BrandTypeEnum(int value, String desc, int orgSource, BrandCategoryEnum category, int ownerType) {
        this.value = value;
        this.desc = desc;
        this.orgSource = orgSource;
        this.category = category;
        this.ownerType = ownerType;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 此字段废弃，品牌内部使用方法：com.sankuai.meituan.scmbrand.adapter.WmVirtualOrgServiceAdapter#getOrgSourceByBrandType(com.sankuai.meituan.scmbrand.thrift.constant.BrandTypeEnum)
     *
     * @return
     */
    @Deprecated
    public byte getOrgSource() {
        return (byte) orgSource;
    }

    public BrandCategoryEnum getCategory() {
        return category;
    }

    private static final ImmutableMap<Integer, BrandTypeEnum> BRAND_TYPE_IMMUTABLE_MAP = ImmutableMap.copyOf(Maps.uniqueIndex(Lists.newArrayList(BrandTypeEnum.values()), new Function<BrandTypeEnum, Integer>() {
        @Override
        public Integer apply(BrandTypeEnum input) {
            return input.getValue();//此处不可能为null，因此不做判空处理
        }
    }));

    public static BrandTypeEnum of(Integer value) {
        if (null == value) {
            return null;
        }
        return BRAND_TYPE_IMMUTABLE_MAP.get(value);
    }

    public static String getDescByVal(Integer value) {
        if (value == null) {
            return "";
        }
        for (BrandTypeEnum brandType : BrandTypeEnum.values()) {
            if (brandType.getValue() == value) {
                return brandType.getDesc();
            }
        }
        return "";
    }

    public static Map<Integer, BrandTypeEnum> getAllBrandType() {
        return BRAND_TYPE_IMMUTABLE_MAP;
    }

    public static List<BrandTypeEnum> get4ShanGouBrandLevelType(){
        return Lists.newArrayList(BrandTypeEnum.CITY,
                BrandTypeEnum.CRM,
                BrandTypeEnum.LING_SHOU,
                BrandTypeEnum.XIAN_GUO,
                BrandTypeEnum.XIAN_HUA,
                BrandTypeEnum.YAO_PIN,
                BrandTypeEnum.MU_YING,
                BrandTypeEnum.MEI_ZHUANG,
                BrandTypeEnum.FU_SHI,
                BrandTypeEnum.RI_YONG,
                BrandTypeEnum.SG_SMALL_CHAIN);
    }

    public static BrandTypeEnum typeOf(int type) {
        BrandTypeEnum[] enums = values();
        for(BrandTypeEnum item : enums){
            if(type == item.getValue()){
                return item;
            }
        }
        return null;
    }
}
