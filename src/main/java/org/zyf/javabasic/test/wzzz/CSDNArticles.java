package org.zyf.javabasic.test.wzzz;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Sets;
import org.zyf.javabasic.common.Article;
import org.zyf.javabasic.test.wzzz.fetcher.AllUserInfoArticleGetUtil;

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
    private static final Map<Integer, Article> ARTICLES_DETAILS = new HashMap<>();
    public static final List<Article> ARTICLES_ONLY;
    private static final Map<Integer, Article> ARTICLES_ONLY_DETAILS = new HashMap<>();

    // 全局记录文章的被选次数
    private static final Map<Integer, Integer> articleSelectionCount = new HashMap<>();


    static {
        try (InputStream inputStream = CSDNComments.class.getClassLoader().getResourceAsStream("articleIds.json")) {
            if (inputStream == null) {
                throw new Exception("articleIds.json not found in resources");
            }
            Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            String articleInfo = scanner.hasNext() ? scanner.next() : "";
            List<Article> articleList = JSON.parseArray(articleInfo, Article.class);
            if (CollectionUtils.isEmpty(articleList)) {
                throw new Exception("articleList is empty!!!");
            }
            ARTICLES = articleList;

            ARTICLES.forEach(article -> {
                ARTICLES_DETAILS.put(article.getArticleId(), article);
            });

        } catch (Exception e) {
            throw new RuntimeException("Failed to load comments", e);
        }

        try (InputStream inputStream = CSDNComments.class.getClassLoader().getResourceAsStream("articleIds-only.json")) {
            if (inputStream == null) {
                throw new Exception("articleIds-only.json not found in resources");
            }
            Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            String articleInfo = scanner.hasNext() ? scanner.next() : "";
            List<Article> articleList = JSON.parseArray(articleInfo, Article.class);
//            if (CollectionUtils.isEmpty(articleList)) {
//                throw new Exception("articleList is empty!!!");
//            }
            ARTICLES_ONLY = articleList;

            ARTICLES_ONLY.forEach(article -> {
                ARTICLES_ONLY_DETAILS.put(article.getArticleId(), article);
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load comments", e);
        }
    }

    public static Set<Integer> articleIds() {
        return ARTICLES.stream().map(Article::getArticleId).collect(Collectors.toSet());
    }

    public static Set<Integer> articleIdsForOnly() {
        return ARTICLES_ONLY.stream().map(Article::getArticleId).collect(Collectors.toSet());
    }

    public static Set<String> articlesForOnly() {
        return ARTICLES_ONLY.stream().map(Article::getUrl).collect(Collectors.toSet());
    }

    public static List<Integer> articleIdList() {
        return ARTICLES.stream().map(Article::getArticleId).collect(Collectors.toList());
    }

    public static Article getArticleById(Integer id) {
        Article article = ARTICLES_DETAILS.get(id);
        if (Objects.nonNull(article)) {
            return article;
        }

        return ARTICLES_ONLY_DETAILS.get(id);
    }

    /**
     * 从 articleIds 集合中随机选取 randomNums 个文章 ID。
     *
     * @param randomNums 需要随机选取的数量
     * @return 随机选取的文章 ID 集合
     * @throws IllegalArgumentException 如果 randomNums 大于 articleIds 的数量
     */
    public static Set<Integer> getRandomArticleIds(int randomNums, Set<Integer> mandatoryIds) {
        Set<Integer> articleIds = articleIds();

        // -------- 1. 参数校验 --------
        if (mandatoryIds == null) {
            mandatoryIds = Collections.emptySet();
        }

        if (!articleIds.containsAll(mandatoryIds)) {
            throw new IllegalArgumentException("mandatoryIds 中包含不存在的 articleId");
        }

//        if (mandatoryIds.size() > randomNums) {
//            throw new IllegalArgumentException("mandatoryIds 数量不能大于 randomNums");
//        }

        if (randomNums > articleIds.size()) {
            throw new IllegalArgumentException("randomNums 不能大于 articleIds 的数量");
        }

        // -------- 2. 初始化计数器 --------
        for (Integer id : articleIds) {
            articleSelectionCount.putIfAbsent(id, 0);
        }

        // -------- 3. 先放入必选文章 --------
        Set<Integer> selectedArticles = new HashSet<>(mandatoryIds);

        // -------- 4. 构建剩余候选集 --------
        Set<Integer> remainingArticles = new HashSet<>(articleIds);
        remainingArticles.removeAll(mandatoryIds);

        int remainingCount = randomNums - selectedArticles.size();
        if (remainingCount <= 0) {
            // 只需要必选文章
            updateSelectionCount(selectedArticles);
            return selectedArticles;
        }

        // -------- 5. 构建权重池（仅针对剩余文章） --------
        List<Integer> weightedArticles = new ArrayList<>();
        for (Integer id : remainingArticles) {
            int weight = 10 - articleSelectionCount.get(id);
            weight = Math.max(weight, 1);
            for (int i = 0; i < weight; i++) {
                weightedArticles.add(id);
            }
        }

        // -------- 6. 随机补足 --------
        Collections.shuffle(weightedArticles);
        Iterator<Integer> iterator = weightedArticles.iterator();
        while (selectedArticles.size() < randomNums && iterator.hasNext()) {
            selectedArticles.add(iterator.next());
        }

        // -------- 7. 更新选中次数 --------
        updateSelectionCount(selectedArticles);

        return selectedArticles;
    }

    private static void updateSelectionCount(Set<Integer> selectedArticles) {
        for (Integer article : selectedArticles) {
            articleSelectionCount.put(article, articleSelectionCount.get(article) + 1);
        }
    }

    public static Set<Integer> getRandomArticleIdsForZhidingUser(String userIdentificationFlag, int randomNums) {
        Set<Integer> articleIds = ARTICLES_ONLY.stream()
                .filter(article -> article.getUrl().contains(userIdentificationFlag))
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

    public static Set<String> getArticleLinks(String urlPrefix) {
        Set<Integer> articleIds = articleIds();

        // 将 Set 转换为 List 以支持索引操作
        List<Integer> articleList = new ArrayList<>(articleIds);

        // 打乱列表以确保随机性
        Collections.shuffle(articleList);

        // 从打乱后的列表中选取前 randomNums 个元素，并拼接为完整链接
        Set<String> resultLinks = new HashSet<>();
        for (int i = 0; i < articleIds.size(); i++) {
            String link = urlPrefix + articleList.get(i);
            resultLinks.add(link);
        }

        return resultLinks;
    }

    /**
     * 获取给定文章ID在排序后的文章列表中的索引。
     *
     * @param articleId 要查找的文章ID
     * @return 文章ID在排序后的列表中的索引
     * @throws NoSuchElementException 如果文章ID不在列表中
     */
    public static int getIndex(Integer articleId) {
        List<Integer> articleList = articleIdList();

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
        Set<Integer> articleIds = AllUserInfoArticleGetUtil.getgetAllArticleIds();
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

    private static void getNeedReadArticleList() {
        List<String> needReadArticleList = new ArrayList<>();
        int limitRead = 1500;
        ARTICLES.forEach(article -> {
            Integer viewCount = article.getViewCount();
            String url = article.getUrl();
            if(viewCount<limitRead){
                needReadArticleList.add(url);
            }
        });

        System.out.println(String.format("阅读数小于%s的文章链接如下：\n", limitRead));
        needReadArticleList.forEach(content-> System.out.println(content));
    }


    public static void main(String[] args) {

        Set<String> articleIdsForComments = CSDNComments.COMMENTS.keySet();
        Set<String> articleIdsForArticles = articleIds().stream().map(String::valueOf).collect(Collectors.toSet());
        soutDiff(articleIdsForComments, articleIdsForArticles);
        System.out.println(articleIds());
        System.out.println(articleIds().contains("140538842"));
        System.out.println(articleIds().size());
        Set<Integer> mandatoryIds = new HashSet<>(Arrays.asList(140538842));
        System.out.println(getRandomArticleIds(30, mandatoryIds));
        System.out.println(getRandomArticleLinks(258, "https://blog.csdn.net/xiaofeng10330111/article/details/"));
        System.out.println(getIndex(139611703));


        System.out.println(articleIdsForOnly().size());
        System.out.println(getRandomArticleIdsForOthers(30));


        System.out.println();

        getNeedReadArticleList();
    }

    public static void soutDiff(Set<String> articleIdsForComments, Set<String> articleIdsForArticles) {
        System.out.println("diff start!");

        // 找到多出的（articleIdsForArticles 中有，而 articleIdsForComments 中没有）
        Set<String> extraInArticles = new HashSet<>(articleIdsForArticles);
        extraInArticles.removeAll(articleIdsForComments); // 移除在 articleIdsForComments 中存在的元素

        // 找到少出的（articleIdsForComments 中有，而 articleIdsForArticles 中没有）
        Set<String> missingInArticles = new HashSet<>(articleIdsForComments);
        missingInArticles.removeAll(articleIdsForArticles); // 移除在 articleIdsForArticles 中存在的元素

        // 打印出差异
        System.out.println("多出的 ID（在 articleIdsForArticles 中）：");
        extraInArticles.forEach(System.out::println);

        System.out.println("少出的 ID（在 articleIdsForComments 中）：");
        missingInArticles.forEach(System.out::println);
        System.out.println("diff end!");
    }
}

