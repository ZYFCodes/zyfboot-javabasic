package org.zyf.javabasic.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
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
    public static final List<Article> ARTICLES_ONLY;

    static {
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

        try (InputStream inputStream = CSDNComments.class.getClassLoader().getResourceAsStream("articleIds-only.json")) {
            if (inputStream == null) {
                throw new Exception("articleIds-only.json not found in resources");
            }
            Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            String  articleInfo = scanner.hasNext() ? scanner.next() : "";
            List<Article> articleList = JSON.parseArray(articleInfo, Article.class);
//            if (CollectionUtils.isEmpty(articleList)) {
//                throw new Exception("articleList is empty!!!");
//            }
            ARTICLES_ONLY = articleList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load comments", e);
        }
    }

    public static Set<Integer> articleIds(){
        return ARTICLES.stream().map(Article::getArticleId).collect(Collectors.toSet());
    }

    public static Set<String> articlesForOnly(){
        return ARTICLES_ONLY.stream().map(Article::getUrl).collect(Collectors.toSet());
    }

    public static List<Integer> articleIdList(){
        return ARTICLES.stream().map(Article::getArticleId).collect(Collectors.toList());
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

    /**
     * 从 articleIds 集合中随机选取 randomNums 个文章 ID，并与固定的 URL 前缀进行拼接，返回链接集合。
     *
     * @param randomNums 需要随机选取的数量
     * @param urlPrefix  固定的 URL 前缀
     * @return 包含完整链接的 Set<String> 集合
     * @throws IllegalArgumentException 如果 randomNums 大于 articleIds 的数量
     */
    public static Set<String> getRandomArticleLinks(int randomNums, String urlPrefix) {
        Set<Integer> articleIds = articleIds();
        // 检查边界条件
        if (randomNums > articleIds.size()) {
            throw new IllegalArgumentException("randomNums 不能大于 articleIds 的数量");
        }

        // 将 Set 转换为 List 以支持索引操作
        List<Integer> articleList = new ArrayList<>(articleIds);

        // 打乱列表以确保随机性
        Collections.shuffle(articleList);

        // 从打乱后的列表中选取前 randomNums 个元素，并拼接为完整链接
        Set<String> resultLinks = new HashSet<>();
        for (int i = 0; i < randomNums; i++) {
            String link = urlPrefix + articleList.get(i);
            resultLinks.add(link);
        }

        return resultLinks;
    }

    /**
     * 获取给定文章ID在排序后的文章列表中的索引。
     *
     * @param articleId   要查找的文章ID
     * @return 文章ID在排序后的列表中的索引
     * @throws NoSuchElementException 如果文章ID不在列表中
     */
    public static int getIndex(Integer articleId) {
        List<Integer> articleList =articleIdList();

        // 查找文章ID的索引
        int index = articleList.indexOf(articleId);

        // 如果找不到，抛出异常
        if (index == -1) {
            throw new NoSuchElementException("文章ID未找到");
        }

        return index;
    }

    /**
     * 从 articleIds 集合中随机选取 randomNums 个文章 ID。
     *
     * @param randomNums 需要随机选取的数量
     * @return 随机选取的文章 ID 集合
     * @throws IllegalArgumentException 如果 randomNums 大于 articleIds 的数量
     */
    public static Set<Integer> getRandomArticleIdsForOthers(int randomNums) {
        Set<Integer> articleIds = Sets.newHashSet(
                2,
                3
        );
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
        System.out.println(getRandomArticleLinks(30,"https://blog.csdn.net/xiaofeng10330111/article/details/"));
        System.out.println(getIndex(139611703));
    }
}

