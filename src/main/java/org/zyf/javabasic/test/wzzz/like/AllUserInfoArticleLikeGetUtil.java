package org.zyf.javabasic.test.wzzz.like;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.common.UserAndArticlesLikeInfo;
import org.zyf.javabasic.test.wzzz.CSDNUserInfos;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 喜欢统计
 * @author: zhangyanfeng
 * @create: 2025-03-23 20:36
 **/
public class AllUserInfoArticleLikeGetUtil {
    private static List<UserAndArticlesLikeInfo> userAndArticlesLikeInfos = Lists.newArrayList();
    private static Map<String, List<String>> userAndArticlesLikesMap = new HashMap<>();

    static {
        InputStream inputStream = AllUserInfoArticleLikeGetUtil.class.getResourceAsStream("/all-userinfo-articles-like.json");
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String attitudesStr = scanner.hasNext() ? scanner.next() : "";
        userAndArticlesLikeInfos = JSON.parseArray(attitudesStr, UserAndArticlesLikeInfo.class);
        if (CollectionUtils.isEmpty(userAndArticlesLikeInfos)) {
            System.out.println("userAndArticlesInfos is empty!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            userAndArticlesLikesMap = new HashMap<>();
        }

        userAndArticlesLikeInfos.forEach(userAndArticlesInfo -> {
            userAndArticlesLikesMap.put(userAndArticlesInfo.getUserInfo(), userAndArticlesInfo.getArticlesForLike());
        });
    }

    public static boolean isLike(String userIdentification, Integer articleId) {
        List<String> likeList = userAndArticlesLikesMap.get(userIdentification);
        if (CollectionUtils.isEmpty(likeList)) {
            return false;
        }
        return likeList.contains(articleId.toString());
    }

    public static List<String> getLikeeds(String userIdentification) {
        List<String> likeList = userAndArticlesLikesMap.get(userIdentification);
        if (CollectionUtils.isEmpty(likeList)) {
            return Lists.newArrayList();
        }
        return likeList;
    }

    public static Map<String, List<String>> getUserAndArticlesLikesMap(){
        return userAndArticlesLikesMap;
    }

    public static void main(String[] args) {
        System.out.println(userAndArticlesLikeInfos.size());
        System.out.println(JSON.toJSONString(userAndArticlesLikeInfos));
        AtomicInteger index = new AtomicInteger(1);
        // 创建 AtomicInteger 来跟踪循环次数
        AtomicInteger num = new AtomicInteger(1);
        userAndArticlesLikesMap.forEach((userId, likes) -> {
            Set<String> likeSets = new HashSet<>(likes);
            System.out.println("编号 " + index.getAndIncrement() + "--用户:" + userId + " 点赞文章个数为： " + likeSets.size()
                    + isNonUserInfoDesired(userId, num));
        });
    }

    public static String isNonUserInfoDesired(String userInfo, AtomicInteger num) {
        if (CSDNUserInfos.userNewInfoNonvaild.containsKey(userInfo)) {
            num.incrementAndGet();
            return "，该账号无法登录，进行排除，userInfo=" + userInfo + ", 其无效排序号为：" + num;
        }

        return "";
    }
}
