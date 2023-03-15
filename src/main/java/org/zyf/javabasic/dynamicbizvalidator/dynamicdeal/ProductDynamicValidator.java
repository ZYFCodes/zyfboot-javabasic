package org.zyf.javabasic.dynamicbizvalidator.dynamicdeal;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductPackageVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductSkuVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductSpuVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.model.VerifyResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 商品动态校验内容
 * @date 2023/3/9  23:53
 */
@Service
@Log4j2
public class ProductDynamicValidator {

    /**
     * 具体商品spu校验
     *
     * @param currentBizKey            业务详细内容：
     *                                 *                比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                                 *                商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                                 *                外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                                 *                备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @param productSpuVerifyContents 商品spu校验信息数据
     * @return 校验结果
     */
    public List<VerifyResult> spuVerify(String currentBizKey, List<ProductSpuVerifyContent> productSpuVerifyContents) {
        /*1.校验内容为空则直接返回结果即可*/
        if (CollectionUtils.isEmpty(productSpuVerifyContents)) {
            return Lists.newArrayList();
        }

        List<VerifyResult> resultList = Lists.newArrayList();
        Validator validator = DynamicValidatorEngine.currentBizValidator(currentBizKey);
        /*门店下所有商品基本数据校验*/
        productSpuVerifyContents.forEach(productSpuVerifyContent -> {
            Set<ConstraintViolation<ProductSpuVerifyContent>> validateResult = validator.validate(productSpuVerifyContent);
            resultList.add(buildVerifyResult(validateResult, ProductSpuVerifyContent.class));
        });

        return resultList;
    }

    /**
     * 商品sku校验
     *
     * @param currentBizKey            业务详细内容：
     *                                 *                比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                                 *                商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                                 *                外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                                 *                备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @param productSkuVerifyContents 商品sku校验信息数据
     * @return 校验结果
     */
    public List<VerifyResult> skuVerify(String currentBizKey, List<ProductSkuVerifyContent> productSkuVerifyContents) {
        if (CollectionUtils.isEmpty(productSkuVerifyContents)) {
            return Lists.newArrayList();
        }

        List<VerifyResult> resultList = Lists.newArrayList();
        Validator validator = DynamicValidatorEngine.currentBizValidator(currentBizKey);
        productSkuVerifyContents.forEach(productSkuVerifyContent -> {
            Set<ConstraintViolation<ProductSkuVerifyContent>> validateResult = validator.validate(productSkuVerifyContent);
            resultList.add(buildVerifyResult(validateResult, ProductSkuVerifyContent.class));
        });

        return resultList;
    }

    /**
     * 组包商品校验
     *
     * @param currentBizKey                业务详细内容：
     *                                     *                比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                                     *                商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                                     *                外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                                     *                备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @param productPackageVerifyContents 组包商品校验信息数据
     * @return 校验结果
     */
    public List<VerifyResult> packageVerify(String currentBizKey, List<ProductPackageVerifyContent> productPackageVerifyContents) {
        if (CollectionUtils.isEmpty(productPackageVerifyContents)) {
            return Lists.newArrayList();
        }
        Validator validator = DynamicValidatorEngine.currentBizValidator(currentBizKey);
        List<Set<ConstraintViolation<ProductPackageVerifyContent>>> resultList = Lists.newArrayList();
        productPackageVerifyContents.forEach(productPackageVerifyContent -> {
            resultList.add(validator.validate(productPackageVerifyContent));
        });
        List<VerifyResult> verifyResultList = this.buildVerifyResult(resultList, ProductPackageVerifyContent.class);
        return verifyResultList;
    }

    /**
     * 批量构建标准的校验结果
     */
    private <T> List<VerifyResult> buildVerifyResult(List<Set<ConstraintViolation<T>>> constraintViolationList, Class<T> clazz) {
        List<VerifyResult> list = Lists.newArrayList();
        for (Set<ConstraintViolation<T>> set : constraintViolationList) {
            list.add(this.buildVerifyResult(set, clazz));
        }
        return list;
    }

    /**
     * 构建标准的校验结果
     */
    private <T> VerifyResult buildVerifyResult(Set<ConstraintViolation<T>> constraintViolationSet, Class<T> clazz) {
        if (CollectionUtils.isEmpty(constraintViolationSet)) {
            return VerifyResult.builder().illegal(false).propertyMessageMap(Maps.<String, String>newHashMap()).build();
        }

        VerifyResult verifyResult = VerifyResult.builder().illegal(true).propertyMessageMap(Maps.<String, String>newHashMap()).build();
        for (ConstraintViolation<T> constraintViolation : constraintViolationSet) {
            new ConstraintViolationTransformFunction<>(clazz, verifyResult).apply(constraintViolation);
        }

        log.debug(verifyResult.toString());
        return verifyResult;
    }
}
