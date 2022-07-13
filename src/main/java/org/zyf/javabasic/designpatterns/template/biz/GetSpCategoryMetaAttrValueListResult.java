package org.zyf.javabasic.designpatterns.template.biz;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/11  21:16
 */
@Data
public class GetSpCategoryMetaAttrValueListResult extends BaseResult {

    private static final long serialVersionUID = 7133983181744630771L;

    List<ScSpCategoryMetaAttrValueVo> spCategoryMetaAttrValueList = Lists.newArrayList();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ScSpCategoryMetaAttrValueVo> getSpCategoryMetaAttrValueList() {
        return spCategoryMetaAttrValueList;
    }

    public void setSpCategoryMetaAttrValueList(List<ScSpCategoryMetaAttrValueVo> spCategoryMetaAttrValueList) {
        this.spCategoryMetaAttrValueList = spCategoryMetaAttrValueList;
    }
}
