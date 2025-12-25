package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.zyf.javabasic.test.wzzz.fetcher.AllUserInfoArticleGetUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 随机评论数
 * @author: zhangyanfeng
 * @create: 2025-03-23 14:24
 **/
public class CSDNUserRandomCommentsNum {

    /**
     * 常规分配评论  : 只有主账号和分账号之分
     * @param userIdentification
     * @return
     */
    public static Set<Integer> getNormal(String userIdentification){
        int randomNums = CSDNUserInfos.getRandomNums(userIdentification);

        Set<Integer> articleIds = Sets.newHashSet();
        if (StringUtils.equalsIgnoreCase(userIdentification, "18252060161")) {
            //如果是本账号则评论其他文章
            articleIds = CSDNArticles.getRandomArticleIdsForOthers(randomNums);
           return  articleIds;
        }

        Set<Integer> mandatoryIds = new HashSet<>(Arrays.asList(
                155890223, 155428600,155428277,155396879,155390688,155389840,155389288,155387130,155386369,155385046,155137557,155133515,155131957,155077449,154913998,
                154911199,154345126,149880480,156132379));
        articleIds = CSDNArticles.getRandomArticleIds(randomNums, mandatoryIds);
        //增加几篇其他分账号的文章
        //articleIds.addAll(AllUserInfoArticleGetUtil.getRandomArticleIds(userIdentification));
        return articleIds;
    }

    /**
     * 获取指定账号进行评论  ：
     * @param userIdentification
     * @return
     */
    public static Set<Integer> getRandomZhiDingArticleIds(String userIdentification, String zhiDingNum){
        int randomNums = CSDNUserInfos.getRandomNums(userIdentification);

        Set<Integer> articleIds = Sets.newHashSet();
        if (StringUtils.equalsIgnoreCase(userIdentification, "18252060161")) {
            //如果是本账号则评论其他文章,不关心指定
            articleIds = CSDNArticles.getRandomArticleIdsForOthers(randomNums);
            return  articleIds;
        }

        //需要关心指定的文章
        articleIds = AllUserInfoArticleGetUtil.getRandomZhiDingArticleIds(zhiDingNum, zhiDingNum, randomNums);
        //增加几篇评论主账号的文章
        articleIds.addAll(CSDNArticles.getRandomArticleIds(3, null));
        return articleIds;
    }
}
