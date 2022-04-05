package org.zyf.javabasic.designpatterns.template.check;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 申诉事项内容
 * @date 2022/3/16  23:37
 */
@Data
@Builder
public class AppealMatters {
    /**
     * 主键;主键
     */
    public Long id;
    /**
     * 申诉记录编号
     */
    public String code;
    /**
     * 申诉人
     */
    public String declarant;
    /**
     * 申诉类型
     */
    public Integer type;
    /**
     * 申诉项
     */
    public List<String> appealItems;
}
