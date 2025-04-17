package org.zyf.javabasic.test.wzzz.fetcher;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.common.Article;
import org.zyf.javabasic.common.UserAndArticlesInfo;
import org.zyf.javabasic.test.wzzz.CSDNUserInfos;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 所有用户对应的文章信息
 * @author: zhangyanfeng
 * @create: 2025-03-23 13:47
 **/
public class AllUserInfoArticleGetUtil {

    private static List<UserAndArticlesInfo> userAndArticlesInfos = Lists.newArrayList();
    private static Map<String, List<Article>> userAndArticlesMap = new HashMap<>();
    private static List<Article> userAllArticles = Lists.newArrayList();

    static {
        InputStream inputStream = AllUserInfoArticleGetUtil.class.getResourceAsStream("/all-userinfo-articles.json");
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String attitudesStr = scanner.hasNext() ? scanner.next() : "";
        userAndArticlesInfos = JSON.parseArray(attitudesStr, UserAndArticlesInfo.class);
        if (CollectionUtils.isEmpty(userAndArticlesInfos)) {
            System.out.println("userAndArticlesInfos is empty!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            userAndArticlesMap = new HashMap<>();
        }

        AtomicInteger num = new AtomicInteger();
        userAndArticlesInfos.forEach(userAndArticlesInfo -> {
            if(CSDNUserInfos.userNewInfoNonvaild.containsKey(userAndArticlesInfo.getUserInfo())){
                num.incrementAndGet();
                System.out.println(num + "该账号无法登录，进行排除，userInfo="+userAndArticlesInfo.getUserInfo());
                return;
            }
            userAndArticlesMap.put(userAndArticlesInfo.getUserInfo(), userAndArticlesInfo.getArticles());
            userAllArticles.addAll(userAndArticlesInfo.getArticles());
        });

    }

    public static List<Article> getArticles(String userInfo) {
        return userAndArticlesMap.getOrDefault(userInfo, Lists.newArrayList());
    }

    public static List<Article> getAllArticles() {
        return userAllArticles;
    }

    public static Set<Integer> getgetAllArticleIds() {
        return getAllArticles().stream().map(Article::getArticleId).collect(Collectors.toSet());
    }

    /**
     * 从 articleIds 集合中随机选取 randomNums 个文章 ID。
     *
     * @return 随机选取的文章 ID 集合
     * @throws IllegalArgumentException 如果 randomNums 大于 articleIds 的数量
     */
    public static Set<Integer> getRandomArticleIds(String userInfo) {
        String userName = AllUserInfoGetUtil.getUserName(userInfo);
        int randomNums = CSDNUserInfos.getRandomNumsForXH(userInfo);
        List<Article> articles = getAllArticles();
        if (CollectionUtils.isEmpty(articles)) {
            return Collections.emptySet();
        }

        Set<Integer> articleIds = articles.stream()
                .filter(s -> !s.getUrl().contains(userName))
                .map(Article::getArticleId)
                .collect(Collectors.toSet());
        // 检查边界条件
        if (randomNums > articleIds.size()) {
            throw new IllegalArgumentException("randomNums 不能大于 articleIds 的数量");
        }

        // 将 Set 转换为 List 以支持索引操作
        List<Integer> articleList = new ArrayList<>(articleIds);

        // 打乱列表以确保随机性
        Collections.shuffle(articleList);

        // 从打乱后的列表中选取前 randomNums 个元素
        return new HashSet<>(articleList.subList(0, randomNums));
    }

    /**
     * 从 articleIds 集合中随机选取 randomNums 个文章 ID。
     *
     * @return 随机选取的文章 ID 集合
     * @throws IllegalArgumentException 如果 randomNums 大于 articleIds 的数量
     */
    public static Set<Integer> getRandomZhiDingArticleIds(String userInfo, String zhiDingNum,int randomNums) {
        String zhiDingNumUserName = AllUserInfoGetUtil.getUserName(zhiDingNum);
        List<Article> articles = getAllArticles();
        if (CollectionUtils.isEmpty(articles)) {
            return Collections.emptySet();
        }

        Set<Integer> articleIds = articles.stream()
                .filter(s -> s.getUrl().contains(zhiDingNumUserName))
                .map(Article::getArticleId)
                .collect(Collectors.toSet());
        // 检查边界条件
        if (randomNums > articleIds.size()) {
            throw new IllegalArgumentException("randomNums 不能大于 articleIds 的数量");
        }

        // 将 Set 转换为 List 以支持索引操作
        List<Integer> articleList = new ArrayList<>(articleIds);

        // 打乱列表以确保随机性
        Collections.shuffle(articleList);

        // 从打乱后的列表中选取前 randomNums 个元素
        return new HashSet<>(articleList.subList(0, randomNums));
    }

    public static void main(String[] args) {
        String userInfo = "19260193307";
        String zhiDingNum="18131272195";
        List<Article> articles = getArticles(userInfo);
        System.out.println(userInfo + "======" + JSON.toJSONString(articles));
        System.out.println(getAllArticles().size());
        System.out.println(getRandomArticleIds(userInfo));
        System.out.println(getRandomZhiDingArticleIds( userInfo,  zhiDingNum, 20));
    }
}
