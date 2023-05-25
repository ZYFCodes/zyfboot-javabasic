package org.zyf.javabasic.common.utils;

import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.base.BCConvert;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:11
 */
public class CommonUtils {
    /**
     * 大写转化为小写 全角转化为半角
     *
     * @param src
     * @return
     */
    public static int charConvert(char src) {
        int r = BCConvert.qj2bj(src);
        return (r >= 'A' && r <= 'Z') ? r + 32 : r;
    }
}
