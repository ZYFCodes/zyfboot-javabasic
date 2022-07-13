package org.zyf.javabasic.designpatterns.template.controller;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:32
 */
@Controller
@RequestMapping("/org/zyf")
@Slf4j
public class ZYFBizTemplateController {

    @RequestMapping("r/listSubBrand")
    @ResponseBody
    public BizResponse<ListSubBrandResult> listSubBrand(ListSubBrandCommand command, final BindingResult br)
            throws Exception {
        return new BizTemplateWithSpringValidator<ListSubBrandCommand, ListSubBrandResult>(command,
                new ListSubBrandResult(), br) {

            void brandValidator() {
                /*根据接口查看传入品牌是否是有效品牌*/
                List<Long> illegalBrands = Lists.newArrayList();
                if (CollectionUtils.isEmpty(command.getBrandIds())) {
                    return;
                }
                /*模拟调用返回无效品牌*/
                illegalBrands.addAll(command.getBrandIds());
                this.addValidation(CollectionUtils.isEmpty(illegalBrands), String.format("存在不合法品牌信息[%s]", command.getBrandIds()));
            }

            @Override
            public void doBefore() {
                /*1.对入参进行调整或默认填充，比方说command的基本必要参数未填写可默认指定处理*/
                /*2.对基本参数进行检查等*/
                this.addValidation(command.getParentId() > 0, "parentId必须大于0");
                this.brandValidator();
            }

            @Override
            public void doBiz() throws Exception {
                List<ListSubBrandResult.CRUXStandardProductLibBrand> brandList = Lists.newArrayList();
                result.convert2CruxBrand(brandList);
            }
        }.execute();
    }

    @RequestMapping(value = "r/getStandardCategoryTagValueList")
    @ResponseBody
    public BizResponse<GetSpCategoryMetaAttrValueListResult> getStandardCategoryTagValueList() throws Exception {
        return new BizWithoutCommandTemplate<GetSpCategoryMetaAttrValueListResult>(new GetSpCategoryMetaAttrValueListResult()) {
            @Override
            public void doBefore() {
            }

            @Override
            public void doBiz() throws Exception {
                /*模拟调用*/
                //CRUXStandardProductCategoryMetaAttrValueListResult thriftResult = cruxStandardProductCategoryThriftService.getCategoryMetaAttrValueListByMetacode(MetaAttrEnum.STANDARD_CATEGORY_TAG.getCode());
                CRUXStandardProductCategoryMetaAttrValueListResult thriftResult = new CRUXStandardProductCategoryMetaAttrValueListResult();
                if (thriftResult == null) {
                    throw new ZYFServerException(ErrorCode.DATA_NOT_FOUND.getCode(), ErrorCode.DATA_NOT_FOUND.getMsg());
                }
                if (CollectionUtils.isEmpty(thriftResult.getCategoryMetaAttrValueList())) {
                    return;
                }
                for (ScSpCategoryMetaAttrValue value : thriftResult.getCategoryMetaAttrValueList()) {
                    result.getSpCategoryMetaAttrValueList().add(new ScSpCategoryMetaAttrValueVo());
                }
            }
        }.execute();
    }

    @RequestMapping(value = "strategy/w/strategyDeploy", method = RequestMethod.POST)
    @ResponseBody
    public BizResponse<BaseResult> batchDeployStrategy(@RequestBody BatchDeployStrategyCommand command) throws Exception {
        return new BizWithoutResultTemplate<BatchDeployStrategyCommand>(command) {
            @Override
            public void doBefore() {
                addValidation(command.getProcessBizType() != null, "processBizType不能为null，请参考com.sankuai.meituan.tsp.product.bizconsistency.enums.BizTypeEnum");
                addValidation(command.getProcessDataType() != null, "processDataType不能为null，请参考com.sankuai.meituan.tsp.product.bizconsistency.enums.DataTypeEnum");
                addValidation(command.getOperation() != null, "operation不能为null，1-发布策略 2-撤销策略");
            }

            @Override
            public void doBiz() throws Exception {
                BizTypeEnum bizTypeEnum = BizTypeEnum.findByType(command.getProcessBizType());
                if (bizTypeEnum == null) {
                    throw new IllegalArgumentException(String.format("%d是非法的processBizType", command.getProcessBizType()));
                }

                DataTypeEnum dataTypeEnum = DataTypeEnum.findByType(command.getProcessDataType());
                if (dataTypeEnum == null) {
                    throw new IllegalArgumentException(String.format("%d是非法的processDataType", command.getProcessDataType()));
                }

                if (command.isDeploy()) {
//                    productBcpService.batchDeployStrategy(bizTypeEnum, dataTypeEnum, command.getStrategyIdList());
                } else if (command.isUndeploy()) {
//                    productBcpService.batchUndeployStrategy(bizTypeEnum, dataTypeEnum, command.getStrategyIdList());
                }
            }
        }.execute();
    }

    @AllArgsConstructor
    @Getter
    public enum BizTypeEnum {

        /**
         * 业务模型类型
         */
        PRODUCT_SUPPLY_SPU(1, "spuId", "商品供给业务SPU"),
        PRODUCT_SUPPLY_SKU(2, "skuId", "商品供给业务SKU"),
        PRODUCT_SUPPLY_TAG(3, "tagId", "商品供给业务TAG"),
        ;
        private int type;
        private String bizId;
        private String desc;

        @Nullable
        public static BizTypeEnum findByType(int type) {
            for (BizTypeEnum each : BizTypeEnum.values()) {
                if (each.getType() == type) {
                    return each;
                }
            }
            return null;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum DataTypeEnum {
        /**
         * 不一致问题数据类型
         */
        MYSQL_ONLY(1, "仅Mysql"),
        MYSQL_AND_ES(2, "Mysql和ES"),
        MYSQL_AND_TAIR(3, "Mysql和Tair"),
        MYSQL_AND_REDIS(4, "Mysql和Redis"),
        MYSQL_AND_MTSEARCH(5, "Mysql和大搜"),
        ;

        private int type;
        private String desc;

        public static DataTypeEnum findByType(int type) {
            for (DataTypeEnum each : DataTypeEnum.values()) {
                if (each.getType() == type) {
                    return each;
                }
            }
            return null;
        }

    }

}
