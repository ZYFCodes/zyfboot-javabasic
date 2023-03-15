package org.zyf.javabasic.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiPackageSkuRel;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yanfengzhang
 * @description 组包的工具类
 * @date 2023/3/15  23:12
 */
public class PackageProductUtils {

    public static String toPackageSkuIdStr(List<ProductPoiPackageSkuRel> poiPackageSkuRelList) {
        TreeMap<Long, Integer> skuIdToCount = new TreeMap<>();
        for (ProductPoiPackageSkuRel poiPackageSkuRel : poiPackageSkuRelList) {
            String pricingRule = poiPackageSkuRel.getPricingRule();
            if (StringUtils.isBlank(pricingRule)) {
                skuIdToCount.put(poiPackageSkuRel.getPoiSkuId(), 0);
                continue;
            }
            ProductPackageSkuRel packageSkuRel = JSON.parseObject(pricingRule, ProductPackageSkuRel.class);
            skuIdToCount.put(poiPackageSkuRel.getPoiSkuId(), packageSkuRel.getCount());
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Integer> entry : skuIdToCount.entrySet()) {
            sb.append(entry.getKey()).append("[").append(entry.getValue()).append("]");
        }

        return sb.toString();
    }
}
