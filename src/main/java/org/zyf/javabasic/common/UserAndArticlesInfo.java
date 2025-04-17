package org.zyf.javabasic.common;

import lombok.Data;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 整合对应用户信息
 * @author: zhangyanfeng
 * @create: 2025-03-23 11:50
 **/
@Data
public class UserAndArticlesInfo {
    private List<Article> articles;
    private String userInfo;
}
