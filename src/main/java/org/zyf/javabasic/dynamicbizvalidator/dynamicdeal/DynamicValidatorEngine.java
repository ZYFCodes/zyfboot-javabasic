package org.zyf.javabasic.dynamicbizvalidator.dynamicdeal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.PropertyConstraintMappingContext;
import org.hibernate.validator.cfg.context.PropertyTarget;
import org.hibernate.validator.cfg.defs.AssertTrueDef;
import org.hibernate.validator.cfg.defs.DecimalMaxDef;
import org.hibernate.validator.cfg.defs.DecimalMinDef;
import org.hibernate.validator.cfg.defs.LengthDef;
import org.hibernate.validator.cfg.defs.NotBlankDef;
import org.hibernate.validator.cfg.defs.RangeDef;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfig;
import org.zyf.javabasic.dynamicbizvalidator.annotation.DynamicConfigGroup;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductPackageVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductSkuVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.content.ProductSpuVerifyContent;
import org.zyf.javabasic.dynamicbizvalidator.enums.DynamicConfigType;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 商品平台校验引擎
 * 规定：每个业务标识拥有一个改类的实例
 * @date 2023/3/9  23:03
 */
@Slf4j
public class DynamicValidatorEngine {
    /**
     * 各业务方标识所对应的Validator
     */
    public final static Map<String, Validator> bizValidatorMap = Maps.newConcurrentMap();
    /**
     * 各业务方标识是否有数据唯一性校验
     * 如果存在数据唯一性校验，是需要查询数据库的。
     */
    public final static Map<String, Boolean> bizUniqueFlagMap = Maps.newConcurrentMap();

    /**
     * 初始化业务动态配置数据校验内容信息
     *
     * @param bizInfo       业务详细内容：
     *                      比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                      商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                      外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                      备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @param configContent
     */
    public static void init(final String bizInfo, final JSONObject configContent) {
        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(new LocaleMessageInterpolator(new ResourceBundleMessageInterpolator(), Locale.SIMPLIFIED_CHINESE));
        ConstraintMapping constraintMapping = configuration.createConstraintMapping();

        initBizConstraint(bizInfo, ProductSpuVerifyContent.class, constraintMapping, configContent);
        initBizConstraint(bizInfo, ProductSkuVerifyContent.class, constraintMapping, configContent);
        initBizConstraint(bizInfo, ProductPackageVerifyContent.class, constraintMapping, configContent);

        bizValidatorMap.put(bizInfo, configuration.addMapping(constraintMapping).buildValidatorFactory().getValidator());
    }

    /**
     * 初始化具体业务校验规则信息
     *
     * @param bizInfo           业务详细内容：
     *                          *                比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                          *                商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                          *                外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                          *                备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @param clazz             具体业务校验数据
     * @param constraintMapping 映射内容
     * @param configContent     配置内容
     */
    private static void initBizConstraint(final String bizInfo, final Class clazz, final ConstraintMapping constraintMapping,
                                          final JSONObject configContent) {
        PropertyTarget propertyTarget = constraintMapping.type(clazz);

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (ArrayUtils.isEmpty(declaredAnnotations)) {
                continue;
            }

            for (Annotation declaredAnnotation : declaredAnnotations) {
                if (DynamicConfigGroup.class.equals(declaredAnnotation.annotationType())) {
                    DynamicConfigGroup dynamicConfigGroup = (DynamicConfigGroup) declaredAnnotation;
                    DynamicConfig[] config = dynamicConfigGroup.config();
                    Map<DynamicConfigType, DynamicConfig> dynamicConfigMap = Maps.newHashMap();
                    if (ArrayUtils.isNotEmpty(config)) {
                        for (DynamicConfig dynamicConfig : config) {
                            dynamicConfigMap.put(dynamicConfig.configType(), dynamicConfig);
                        }
                        setPropertyConstraint(propertyTarget, field.getName(), dynamicConfigMap, configContent);
                    }

                    break;
                }
            }

        }
        bizUniqueFlagMap.put(bizInfo, Boolean.TRUE);
    }

    /**
     * 设置单个属性的规则
     *
     * @param propertyTarget   具体设置规则
     * @param fieldName        具体作用字段
     * @param dynamicConfigMap 动态配置项
     * @param configContent    配置内容
     */
    private static void setPropertyConstraint(PropertyTarget propertyTarget, String fieldName,
                                              Map<DynamicConfigType, DynamicConfig> dynamicConfigMap,
                                              JSONObject configContent) {
        PropertyConstraintMappingContext property = propertyTarget.property(fieldName, ElementType.FIELD);

        /* 是否为空字符的校验 */
        if (dynamicConfigMap.containsKey(DynamicConfigType.NOT_BLANK)) {
            if (getConfigValue(dynamicConfigMap.get(DynamicConfigType.NOT_BLANK), configContent, Boolean.class)) {
                property.constraint(new NotBlankDef());
            }
        }

        /*字符长度的校验*/
        LengthDef lengthDef = null;
        if (dynamicConfigMap.containsKey(DynamicConfigType.LENGTH_MIN)) {
            lengthDef = new LengthDef().min(getConfigValue(dynamicConfigMap.get(DynamicConfigType.LENGTH_MIN), configContent, Integer.class));
        }
        if (dynamicConfigMap.containsKey(DynamicConfigType.LENGTH_MAX)) {
            if (lengthDef == null) {
                lengthDef = new LengthDef();
            }
            lengthDef.max(getConfigValue(dynamicConfigMap.get(DynamicConfigType.LENGTH_MAX), configContent, Integer.class));
        }
        if (lengthDef != null) {
            property.constraint(lengthDef);
        }

        /*数字范围的校验*/
        RangeDef rangeDef = null;
        if (dynamicConfigMap.containsKey(DynamicConfigType.RANGE_MIN)) {
            rangeDef = new RangeDef().min(getConfigValue(dynamicConfigMap.get(DynamicConfigType.RANGE_MIN), configContent, Integer.class));
        }
        if (dynamicConfigMap.containsKey(DynamicConfigType.RANGE_MAX)) {
            if (rangeDef == null) {
                rangeDef = new RangeDef();
            }
            rangeDef.max(getConfigValue(dynamicConfigMap.get(DynamicConfigType.RANGE_MAX), configContent, Integer.class));
        }
        if (rangeDef != null) {
            property.constraint(rangeDef);
        }

        /*是否是true的校验*/
        if (dynamicConfigMap.containsKey(DynamicConfigType.IS_TRUE)
                && getConfigValue(dynamicConfigMap.get(DynamicConfigType.IS_TRUE), configContent, Boolean.class)) {
            property.constraint(new AssertTrueDef());
        }

        /*是否是必填数值的校验*/
        if (dynamicConfigMap.containsKey(DynamicConfigType.NUMBER_NOT_NULL)
                && getConfigValue(dynamicConfigMap.get(DynamicConfigType.NUMBER_NOT_NULL), configContent, Boolean.class)) {
            property.constraint(new RangeDef().min(1));
        }

        /*是否为规定的含小数取值的校验*/
        if (dynamicConfigMap.containsKey(DynamicConfigType.DEECIMAL_MIN)) {
            property.constraint(new DecimalMinDef().value(getConfigValue(dynamicConfigMap.get(DynamicConfigType.DEECIMAL_MIN), configContent, String.class)));
        }
        if (dynamicConfigMap.containsKey(DynamicConfigType.DEECIMAL_MAX)) {
            property.constraint(new DecimalMaxDef().value(getConfigValue(dynamicConfigMap.get(DynamicConfigType.DEECIMAL_MIN), configContent, String.class)));
        }
    }

    /**
     * 解析配置值
     *
     * @param dynamicConfig 动态配置
     * @param configContent 配置内容
     * @param clazz         具体格式
     */
    private static <T> T getConfigValue(DynamicConfig dynamicConfig, JSONObject configContent, Class<T> clazz) {
        try {
            if (configContent.containsKey(dynamicConfig.configName())) {
                return configContent.getObject(dynamicConfig.configName(), clazz);
            }
            return TypeUtils.cast(dynamicConfig.defaultConfigValue(), clazz, ParserConfig.getGlobalInstance());
        } catch (Exception e) {
            log.error("DynamicValidatorEngine getConfigValue exception,dynamicConfigName={},configContent={}", dynamicConfig.configName(), configContent);
            return null;
        }
    }

    /**
     * 当前业务详细的动态配置校验
     *
     * @param currentBizKey 业务详细内容：
     *                      *                比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                      *                商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                      *                外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                      *                备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @return 当前业务详细的动态配置校验内容
     */
    public static Validator currentBizValidator(String currentBizKey) {
        Validator currentValidator = bizValidatorMap.get(currentBizKey);
        if (Objects.isNull(currentValidator)) {
            throw new IllegalStateException(String.format("currentBizKey[%s] can't get legal currentValidator", currentBizKey));
        }
        return currentValidator;
    }

    /**
     * 当前业务详细的动态唯一性配置校验
     *
     * @param currentBizKey 业务详细内容：
     *                      *                比方说实际业务信息如：商超-水果-鲜切水果/果捞店、商超-宠物-宠物食品/用品店、商超-超市便利-小型超市、商超-超市便利-便利店、商超-超市便利-大型超市/卖场、
     *                      *                商超-数码家电-电脑数码店、医药-医药健康-综合药店、医药-医药健康-营养保健品店、医药-医药健康-跨境综合药店、外卖-美食-中式正餐、外卖-美食-全球美食、外卖-甜点-西式点心、
     *                      *                外卖-甜点-生日蛋糕、外卖-甜点-水果捞、外卖-饮品-咖啡、外卖-饮品-纯茶/凉茶、外卖-饮品-奶茶、外卖-饮品-果汁等
     *                      *                备注：业务信息可以不断扩展进行精细化管控等处理，加大灵活性，业务细化后按细化规则配置和提取差异化动态配置校验
     * @return 当前业务详细的动态唯一性配置校验内容
     */
    public static boolean currentBizUniqueFlag(String currentBizKey) {
        Boolean currentUniqueFlag = bizUniqueFlagMap.get(currentBizKey);
        if (Objects.isNull(currentUniqueFlag)) {
            return false;
        }
        return currentUniqueFlag;
    }
}
