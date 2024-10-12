package org.zyf.javabasic.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.common.Article;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNArticles
 * @author: zhangyanfeng
 * @create: 2024-10-11 00:10
 **/
public class CSDNArticles {

    public static final List<Article> ARTICLES;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = CSDNComments.class.getClassLoader().getResourceAsStream("articleIds.json")) {
            if (inputStream == null) {
                throw new Exception("articleIds.json not found in resources");
            }
            Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            String  articleInfo = scanner.hasNext() ? scanner.next() : "";
            List<Article> articleList = JSON.parseArray(articleInfo, Article.class);
            if (CollectionUtils.isEmpty(articleList)) {
                throw new Exception("articleList is empty!!!");
            }
            ARTICLES = articleList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load comments", e);
        }
    }

    public static Set<Integer> articleIds(){
        return ARTICLES.stream().map(Article::getArticleId).collect(Collectors.toSet());
    }

    /**
     * 从 articleIds 集合中随机选取 randomNums 个文章 ID。
     *
     * @param randomNums 需要随机选取的数量
     * @return 随机选取的文章 ID 集合
     * @throws IllegalArgumentException 如果 randomNums 大于 articleIds 的数量
     */
    public static Set<Integer> getRandomArticleIds(int randomNums) {
        Set<Integer> articleIds = articleIds();
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
        String articleId = "105360860";
        System.out.println(articleIds());
        System.out.println(articleIds().size());
        System.out.println(getRandomArticleIds( 30));
    }
}

