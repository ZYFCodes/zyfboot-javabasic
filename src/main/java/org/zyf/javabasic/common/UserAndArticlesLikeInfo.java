package org.zyf.javabasic.common;

import lombok.Data;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: like记录
 * @author: zhangyanfeng
 * @create: 2025-03-23 20:31
 **/
@Data
public class UserAndArticlesLikeInfo {
    private List<String> articlesForLike;
    private String userInfo;
}
