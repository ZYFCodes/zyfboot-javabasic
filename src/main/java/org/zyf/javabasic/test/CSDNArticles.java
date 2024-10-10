package org.zyf.javabasic.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.common.Article;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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


    public static void main(String[] args) {
        String articleId = "105360860";
        System.out.println(articleIds());
        System.out.println(articleIds().size());
    }
}

