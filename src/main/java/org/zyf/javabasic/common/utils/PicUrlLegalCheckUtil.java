package org.zyf.javabasic.common.utils;

import com.github.houbb.heaven.util.lang.StringUtil;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/15  23:45
 */
public class PicUrlLegalCheckUtil {
    public static  boolean checkLegaPicUrl(String picUrl) {
        if (StringUtil.isNotBlank(picUrl)) {
            String illegalString =  "vip|alibaba|bytedance";
            String[] illegals = illegalString.split("\\|");
            for (String illegal : illegals) {
                if (picUrl.contains(illegal)) {
                    return false;
                }
            }
        }
        return true;
    }
}
