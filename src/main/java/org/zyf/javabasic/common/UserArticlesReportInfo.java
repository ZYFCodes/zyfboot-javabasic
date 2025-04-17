package org.zyf.javabasic.common;

import lombok.Data;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 整合文章信息
 * @author: zhangyanfeng
 * @create: 2025-03-23 11:53
 **/
@Data
public class UserArticlesReportInfo {
    private List<UserAndArticlesInfo> userAndArticlesInfos;
}
