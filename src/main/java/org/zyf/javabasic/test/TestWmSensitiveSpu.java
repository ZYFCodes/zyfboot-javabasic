package org.zyf.javabasic.test;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/28  18:59
 */
public class TestWmSensitiveSpu {
    public static void main(String[] args) {
        BatchContainsSensitiveWordSpuCommand command = new BatchContainsSensitiveWordSpuCommand();
        List<WmSensitiveSpu> spus = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            WmSensitiveSpu wmSensitiveSpu = new WmSensitiveSpu();
            List<WmSensitiveSku> skuList = Lists.newArrayList();
            for (int j = 0; j < 10; j++) {
                WmSensitiveSku wmSensitiveSku = new WmSensitiveSku();
                wmSensitiveSku.setId(i + j);
                skuList.add(wmSensitiveSku);
            }
            wmSensitiveSpu.setSkuList(skuList);
            spus.add(wmSensitiveSpu);
        }
        command.setSpus(spus);
        System.out.println(command);

        for (WmSensitiveSpu wmSensitiveSpu : command.getSpus()) {
            /*如果单个spu下的sku数量超过限制，直接截短只处理指定的sku数量*/
            if (CollectionUtils.isNotEmpty(wmSensitiveSpu.getSkuList()) && wmSensitiveSpu.getSkuList().size() > 2) {
                List<WmSensitiveSku> validSkus = wmSensitiveSpu.getSkuList().stream().limit(2).collect(Collectors.toList());
                wmSensitiveSpu.setSkuList(validSkus);
            }
        }

        System.out.println(command);
    }

    @Data
    static class BatchContainsSensitiveWordSpuCommand {
        public List<WmSensitiveSpu> spus;
    }

    @Data
    static class WmSensitiveSpu {
        public List<WmSensitiveSku> skuList;
    }

    @Data
    static class WmSensitiveSku {
        public long id;
    }
}
