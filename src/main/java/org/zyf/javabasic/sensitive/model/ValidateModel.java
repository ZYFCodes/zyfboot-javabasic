package org.zyf.javabasic.sensitive.model;

import org.zyf.javabasic.sensitive.base.ValidateResult;
import org.zyf.javabasic.sensitive.base.WordDomain;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 模型接口
 * @date 2023/5/25  20:02
 */
public interface ValidateModel {

    /**
     * 初始化
     *
     * @return
     */
    public boolean init(List<WordDomain> words);


    /**
     * 敏感词校验
     *
     * @return
     */
    public List<ValidateResult> validate(String content, Long wmPoiId);
}
