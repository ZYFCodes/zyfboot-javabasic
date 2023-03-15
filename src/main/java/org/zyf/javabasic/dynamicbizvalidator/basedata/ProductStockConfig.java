package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:56
 */
@Data
@Builder
public class ProductStockConfig {
    private long skuId;
    private List<Integer> type;
    private LimitSyncStockDomain limitSyncStock;
    private SyncNextDayStockDomain syncNextDay;
    private ProductPoiRuleStockAppliedRange appliedRange;

    @Data
    class LimitSyncStockDomain {
        public boolean limitSyncStock;
        public String schedule;
    }

    @Data
    class SyncNextDayStockDomain {
        public boolean syncNextDayStock;
        public int syncCount;
    }

    enum ProductPoiRuleStockAppliedRange {
        ALL(1),
        PART(2);

        private final int value;

        private ProductPoiRuleStockAppliedRange(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static ProductPoiRuleStockAppliedRange findByValue(int value) {
            switch (value) {
                case 1:
                    return ALL;
                case 2:
                    return PART;
                default:
                    return null;
            }
        }
    }
}
