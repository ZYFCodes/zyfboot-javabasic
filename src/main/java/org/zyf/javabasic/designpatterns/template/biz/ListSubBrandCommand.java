package org.zyf.javabasic.designpatterns.template.biz;

import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:33
 */
@Data
public class ListSubBrandCommand extends BaseCommand {
    @Min(value = 1, message = "parentId必须大于0")
    private int parentId;
    @Min(value = 1, message = "必须为有效品牌")
    private List<Long> brandIds;
}
