package org.zyf.javabasic.dynamicbizvalidator.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiPackageSkuRel;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSku;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSpu;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiTag;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:24
 */
@Service
public class PoiProductVerifyQueryService {

    /**
     * 模拟：通过门店和对应分类以及spu名称查询对应的商品spu数据
     * 店内分类id与商品名称集合的映射
     */
    public List<ProductPoiSpu> getSameNamePoiSpuList(long poiId, List<Long> tagIdList, List<String> spuNameList) {

        ProductPoiTag productPoiTag = ProductPoiTag.builder().poiId(poiId).id(11L).name("商家优选推荐").build();
        ProductPoiSpu productPoiSpu = ProductPoiSpu.builder().id(23L).poiId(poiId).name("张彦峰优选商品").tagList(Lists.newArrayList(productPoiTag)).build();
        return Lists.newArrayList(productPoiSpu);
    }

    /**
     * 模拟：通过门店和对应upc查询对应的商品sku数据
     */
    public List<ProductPoiSku> getSkuListByUpcList(long poiId, List<String> upcList) {
        ProductPoiSku productPoiSku1 = ProductPoiSku.builder().id(2L).name("张彦峰优选商品规格2").poiId(poiId).upcCode("2345676543212345432123").build();
        ProductPoiSku productPoiSku2 = ProductPoiSku.builder().id(1L).name("张彦峰优选商品规格1").poiId(poiId).upcCode("2345676535432455432123").build();
        return Lists.newArrayList(productPoiSku1, productPoiSku2);
    }

    /**
     * 模拟：通过门店和对应id查询对应的商品sku数据
     */
    public List<ProductPoiSku> getSkuListByIdList(long poiId, List<Long> idList, boolean needStock) {
        ProductPoiSku productPoiSku1 = ProductPoiSku.builder().id(2L).name("张彦峰优选商品规格2").poiId(poiId).upcCode("2345676543212345432123").build();
        ProductPoiSku productPoiSku2 = ProductPoiSku.builder().id(1L).name("张彦峰优选商品规格1").poiId(poiId).upcCode("2345676535432455432123").build();
        return Lists.newArrayList(productPoiSku1, productPoiSku2);
    }

    /**
     * 模拟：通过门店和对应id查询对应的组合商品数据
     */
    public List<ProductPoiPackageSkuRel> getPackageSkuRelListBySkuIdList(long poiId, List<Long> skuIdList) {
        ProductPoiPackageSkuRel productPoiPackageSkuRel1 = ProductPoiPackageSkuRel.builder().poiId(poiId).id(34L).poiSkuId(1L).poiPackageSkuId(1L).build();
        ProductPoiPackageSkuRel productPoiPackageSkuRel2 = ProductPoiPackageSkuRel.builder().poiId(poiId).id(36L).poiSkuId(2L).poiPackageSkuId(2L).build();
        return Lists.newArrayList(productPoiPackageSkuRel1, productPoiPackageSkuRel2);
    }

    public List<ProductPoiPackageSkuRel> getPackageSkuRelListByPackageSkuIdList(long poiId, List<Long> packageSkuIdList) {
        ProductPoiPackageSkuRel productPoiPackageSkuRel1 = ProductPoiPackageSkuRel.builder().poiId(poiId).id(34L).poiSkuId(1L).poiPackageSkuId(1L).build();
        ProductPoiPackageSkuRel productPoiPackageSkuRel2 = ProductPoiPackageSkuRel.builder().poiId(poiId).id(36L).poiSkuId(2L).poiPackageSkuId(2L).build();
        return Lists.newArrayList(productPoiPackageSkuRel1, productPoiPackageSkuRel2);
    }
}
