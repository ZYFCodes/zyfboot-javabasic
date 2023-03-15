package org.zyf.javabasic.dynamicbizvalidator.content;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.Data;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSku;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiSpu;
import org.zyf.javabasic.dynamicbizvalidator.basedata.ProductPoiTag;

import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 商品数据校验数据持有者
 * * 为尽量减少校验时查询过多数据，需要在校验之前，查询出所有的准备数据，放到这个类的实例中。
 * * 数据查询要尽量缩小范围，避免过多的查询与后续的循环对比。
 * @date 2023/3/9  23:08
 */
@Data
public class PoiProductValidatorDataHolder {
    /**
     * 描述：spu集合
     * 功能：验证spu名称本次保存列表唯一
     */
    private List<ProductPoiSpu> spuList = Lists.newArrayList();

    /**
     * 描述：店内分类id与商品名称集合的映射
     * 功能：验证商品名称店内分类下唯一
     */
    private Multimap<Long, ProductPoiSpu> tagIdToSpuMultimap = HashMultimap.create();

    /**
     * 描述：分类集合
     * 功能：验证店内分类名称唯一
     */
    private List<ProductPoiTag> tagList = Lists.newArrayList();

    /**
     * 描述：本次待保存的sku列表, 按spu的顺序组合
     * 功能：验证本次批量保存的sku是否有重复的数据，比如upc码
     */
    private Map<Long, List<ProductPoiSku>> spuIdToSkuListMap = Maps.newLinkedHashMap();

    /**
     * 描述：upc码对应的sku集合
     * 功能：做upc重复校验
     */
    private Multimap<String, ProductPoiSku> upcToSkuListMap = HashMultimap.create();

    /**
     * 描述：skuId对应的sku
     * 功能：对旧数据依赖的校验会用的，比如价格
     */
    private Map<Long, ProductPoiSku> skuIdToSkuMap = Maps.newHashMap();

    /**
     * 描述：packageSkuId对应的skuId
     * 功能：批量创建组包商品时，比较门店内有没有相同的组包商品
     */
    private Map<String, Long> skuIdListStrToPackageSkuMap = Maps.newHashMap();

    /**
     * 描述：skuId对应的组包关联的sku
     * 功能：校验组包关联的单品sku是否存在
     */
    private Map<Long, ProductPoiSku> skuIdToPackageRelSkuMap = Maps.newHashMap();

    /**
     * 描述：组包skuId对应的组包sku
     * 功能：校验组包关联的单品sku是否为组包商品
     */
    private Map<Long, ProductPoiSku> packageSkuIdToPackageSkuMap = Maps.newHashMap();
}
