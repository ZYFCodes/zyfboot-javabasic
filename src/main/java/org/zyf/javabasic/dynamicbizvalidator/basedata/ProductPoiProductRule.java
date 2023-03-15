package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:55
 */
@Data
@Builder
public class ProductPoiProductRule {
    public long id;
    public long poiId;
    public ProductPoiRuleType ruleType;
    public String ruleDetail;
    public long similarRuleCode;
    public long ctime;
    public long utime;
    public String reserved1;

    enum ProductPoiRuleType {
        LIMIT_SALE(1),
        STOCK_CLEAN(2),
        GROUP_ORDER(3),
        CYCLE_PURCHASE(4);

        private final int value;

        private ProductPoiRuleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static ProductPoiRuleType findByValue(int value) {
            switch (value) {
                case 1:
                    return LIMIT_SALE;
                case 2:
                    return STOCK_CLEAN;
                case 3:
                    return GROUP_ORDER;
                case 4:
                    return CYCLE_PURCHASE;
                default:
                    return null;
            }
        }
    }
}
