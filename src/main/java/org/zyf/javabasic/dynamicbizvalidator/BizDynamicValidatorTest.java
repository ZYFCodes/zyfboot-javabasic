package org.zyf.javabasic.dynamicbizvalidator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSku;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSpu;
import org.zyf.javabasic.dynamicbizvalidator.dynamicdeal.DynamicValidatorEngine;
import org.zyf.javabasic.dynamicbizvalidator.model.VerifyResultDetail;
import org.zyf.javabasic.dynamicbizvalidator.service.PoiProductDynamicValidatorService;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/8  23:30
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BizDynamicValidatorTest {

    @Autowired
    private PoiProductDynamicValidatorService poiProductDynamicValidatorService;

    @Before
    public void init(){
        /*假设闪购商超B端上单业务编码的业务标识为sdq1234rewsddsf,实际上这个应该是用户在配置侧填写完成实际触发到对应的应用方*/
        String bizInfo = "sdq1234rewsddsf";
        JSONObject configContent = new JSONObject();
        /*对应业务spu信息动态校验要求*/
        configContent.put("spu.name.notnull", true);
        configContent.put("spu.name.length.max", 10);
        configContent.put("spu.name.uniqueInTag", true);
        configContent.put("spu.skus.size.min", 1);
        configContent.put("spu.skus.size.max", 30);
        configContent.put("spu.skus.attrSku.size.min", 0);
        configContent.put("spu.skus.attrSku.size.max", 15);
        configContent.put("spu.spCategory.isNotNull", true);
        configContent.put("spu.piccontent.content.length.max", 12);
        configContent.put("spu.unit.length.max", 30);
        configContent.put("spu.description.length.max", 100);
        configContent.put("spu.thirdPartyPrimaryId.length.max", 100);
        configContent.put("spu.package.use.scene.notnull", true);
        configContent.put("spu.package.use.scene.length.max", 1);
        /*对应业务sku信息动态校验要求*/
        configContent.put("sku.spec.length.min", 1);
        configContent.put("sku.spec.length.max", 10);
        configContent.put("sku.price.top", 100);
        configContent.put("sku.stock.min", 0);
        configContent.put("sku.stock.max", 9999999);
        configContent.put("sku.boxNum.min", 1);
        configContent.put("sku.boxNum.max", 100);
        configContent.put("sku.boxPrice.min", 0);
        configContent.put("sku.boxPrice.max", 100);
        configContent.put("sku.upc_code.isNotNull", false);
        configContent.put("sku.thirdPartyPrimaryId.length.max", 100);
        configContent.put("sku.locatorCode.length.max", 10);
        configContent.put("sku.tagname.length.max", 20);
        configContent.put("sku.description.length.max", 300);
        configContent.put("sku.unit.length.max", 100);
        configContent.put("sku.minOrderCount.min", 1);
        configContent.put("sku.minOrderCount.max", 100);
        configContent.put("sku.weight.isNotNull", true);
        configContent.put("sku.shippingtimex.legal", true);
        configContent.put("sku.upc_code.uniqueInPoi", true);
        configContent.put("sku.price.range", 1000);
        configContent.put("sku.price.frequency", 100);
        configContent.put("sku.legalPicUrl", true);
        configContent.put("sku.package.skus.size.min", 2);
        configContent.put("sku.package.unique", true);
        configContent.put("sku.package.legalPackagePrice", true);
        configContent.put("sku.package.legalPackageStock", true);
        /*组包商品校验要求*/
        configContent.put("package.sku.count.min", 1);
        configContent.put("package.sku.count.max", 30);
        configContent.put("package.sku.discount.min", 0);
        configContent.put("package.sku.discount.max", 50);
        configContent.put("package.name.uniqueInTag", true);
        configContent.put("package.sku.notExistPackageSku", true);
        configContent.put("package.sku.pricingRule", true);

        DynamicValidatorEngine.init(bizInfo, configContent);
    }

    /**
     * 商品spu
     */
    @Test
    public void spuVerify() {
        try {
            long poiId = 11L;
            String currentBizKey = "sdq1234rewsddsf";
            List<ProductPoiSku> skuList = Lists.newArrayList(ProductPoiSku.builder()
                    .poiId(poiId)
                    .name("层层围珠玑，团团锦绣簇。")
                    .upcCode("rfgfrds87ytghjkjnbvfghjn")
                    .description("营销活动日志流量增长")
                    .picture("https://zyfcodes.blog.csdn.vip.net/?type=blog")
                     .build(),
                     ProductPoiSku.builder()
                    .poiId(poiId)
                    .name("桃花一簇开无主，可爱深红爱浅红？")
                    .upcCode("pokmnbvghjmnbv098u7ytghj")
                    .description("标签管理系统业务能力全景梳理")
                    .picture("https://zyfcodes.blog.csdn.net/article/details/105148032?spm=1001.2014.3001.5502").build());

            ProductPoiSpu productPoiSpu = ProductPoiSpu.builder()
                    .id(23L)
                    .name("校验商品名称（要求最大长度是30000000）")
                    .description("金典相册收藏：不是相如怜赋客，争教容易见文君。洛阳亲友如相问，一片冰心在玉壶。越陌度阡，枉用相存。酒伴来相命，开尊共解酲。")
                    .categoryId(900003027L)
                    .unit("份")
                    .skuList(skuList)
                    .sourceFoodCode("43erijhgf")
                    .picContent("张彦峰相关相册分类").build();
            List<ProductPoiSpu> poiSpuList = Lists.newArrayList(productPoiSpu);
            VerifyResultDetail<ProductPoiSpu> getVerifyResultDetail = poiProductDynamicValidatorService.spuVerify(poiId, currentBizKey, poiSpuList);
            log.info("门店商品校验结果：{}", JSON.toJSONString(getVerifyResultDetail.aggregation()));
        } catch (Exception e) {
            log.error("门店商品校验结果失败，错误信息：", e);
        }

    }
}
