package org.zyf.javabasic.designpatterns.template.check;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/16  23:37
 */
public class AppealResult {
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
     * 申诉项处理结果
     */
    public List<String> appealResults;
}
