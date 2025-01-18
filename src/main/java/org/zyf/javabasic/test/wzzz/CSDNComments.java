package org.zyf.javabasic.test.wzzz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.zyf.javabasic.common.Article;

import java.io.InputStream;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNComments
 * @author: zhangyanfeng
 * @create: 2024-09-28 18:56
 **/
public class CSDNComments {
    public static final Map<String, List<String>> COMMENTS;
    public static final List<String> limitedComments;
    private static final SecureRandom secureRandom = new SecureRandom();
    // 时间格式为 "yyyy-MM-dd HH:mm"
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = CSDNComments.class.getClassLoader().getResourceAsStream("comments.json")) {
            if (inputStream == null) {
                throw new Exception("comments.json not found in resources");
            }
            COMMENTS = objectMapper.readValue(inputStream, Map.class);
            limitedComments = findKeysWithLimitedComments(COMMENTS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load comments", e);
        }
    }

    public static List<String> getComments(String key) {
        List<String> comments = COMMENTS.getOrDefault(key, Lists.newArrayList());
        if (CollectionUtils.isEmpty(comments)) {
            //没有对应文章的评论时采用默认
            return COMMENTS.get("commentComments");
        }

        //有的话，查看文章是否在特殊列表中，如果时特殊列表则直接返回特殊的内容
        if (limitedComments.contains(key)) {
            return comments;
        }

        //非特殊的，随机增加十条共有的返回
        List<String> randomCommonComents = getRandomComments(COMMENTS.get("commentComments"), 2);
        comments.addAll(randomCommonComents);

        return comments;
    }

    public static List<String> getRandomComments(List<String> comments, int count) {
        // 打乱 comments 列表顺序
        Collections.shuffle(comments);
        // 获取前 count 条
        return comments.subList(0, Math.min(count, comments.size()));
    }

    public static List<String> findKeysWithLimitedComments(Map<String, List<String>> comments) {
        List<String> limitedCommentKeys = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : comments.entrySet()) {
            // 检查每个键对应的 List<String> 是否包含少于等于 5 条记录
            if (entry.getValue().size() <= 5) {
                limitedCommentKeys.add(entry.getKey());
            }
        }

        return limitedCommentKeys;
    }


    public static String getRandomString(Integer articleId, List<String> comments) {
        if (comments == null || comments.isEmpty()) {
            throw new IllegalArgumentException("The input comments cannot be null or empty.");
        }

        // 生成一个随机索引
        // size() 只会返回 0 到 size()-1
        int randomIndex = secureRandom.nextInt(comments.size());

        //增加随机性
        // 打乱列表以确保随机性
        Collections.shuffle(comments);

        //获取该评论，如果有%s的信息则替换为时间
        String comment = comments.get(randomIndex);
        if (comment.contains("%s")) {
            return String.format(comment, getCurrentBeijingTime());
        }

        //然后对评论进行干预处理
        return getRandomComment(articleId, comment);
    }

    private static String getRandomComment(Integer articleId, String comment) {
        //对里面的一些指定内容进行替换
        comment = replaceComment(articleId, comment);
        //随机增加一个时间前缀
        comment = comment + getRandomTimePrefix() + " ";
        StringBuilder sb = new StringBuilder(comment);
        //随机增加尾部信息
        sb.append("\n");
        sb.append(getRandomTimeEnd());

        return sb.toString();
    }

    private static String getRandomTimeEnd() {
        // 创建随机数生成器
        Random random = new Random();

        // 生成随机数 0, 1, 2 三种情况
        int choice = random.nextInt(3);

        // 根据随机数返回不同结果
        switch (choice) {
            case 0:
                // 返回第一种情况
                return AncientChineseTime.getAncientTime()
                        + "  " + CurrentMomentWords.getRandomCurrentMomentWord() + MoodWords.getRandomMoodWord() + "---" + TimeWords.getRandomTimeWord() + "？";
            case 1:
                // 返回第二种情况
                return TimeDescription.getRandomTimeDescription("", 0, true)
                        + "  " + CurrentMomentWords.getRandomCurrentMomentWord() + MoodWords.getRandomMoodWord() + "---" + TimeWords.getRandomTimeWord() + "？";
            case 2:
                // 返回空字符串
                return "";
            default:
                // 理论上不会到达这里
                return "";
        }
    }


    private static String getRandomTimePrefix() {
        // 创建随机数生成器
        Random random = new Random();

        // 随机选择返回其中一种结果
        if (random.nextBoolean()) {
            return TimeDescription.getRandomTimeDescription("", 0, true);
        } else {
            return " ";
        }
    }

    private static String replaceComment(Integer articleId, String comment) {
        Article article = CSDNArticles.getArticleById(articleId);
        if (Objects.isNull(article)) {
            return comment;
        }
        //对文章基本信息进行替换
        comment = comment.replace("[文章题目]", article.getTitle());
        comment = comment.replace("[文章标签]", CollectionUtils.isNotEmpty(article.getTags())
                ? article.getTags().toString()
                : article.getDescription());
        comment = comment.replace("[文章描述]", article.getDescription());
        comment = comment.replace("[发布时间]", getRandomComment(article.getPostTime()));

        return comment;
    }

    public static String getRandomComment(String postTime) {
        // 创建随机数生成器
        Random random = new Random();

        // 随机选择返回其中一种结果
        if (random.nextBoolean()) {
            // 返回 AncientChineseTime 的结果
            return AncientChineseTime.getAncientTimeFromString(postTime);
        } else {
            // 返回 TimeDescription 的结果
            return getRandomPrex() + TimeDescription.getRandomTimeDescription(postTime, 0, false);
        }
    }

    private static String getRandomPrex() {
        String[] TIME_DESCRIPTIONS = {
                "距今发布已", "时至今日发布有", "发布现如今已有", "直至现在发布已过", "至今为止发布已有", "从那时起发布已", "到今天发布有", "发布到此时已", "到此刻发布已",
                "迄今发布有", "发布至今有", "时至此刻发布已过", "发布迄今为止已有", "自发布以后", "自从发布", "自发布之后", "自发布至今",
                "从发布到现在", "发布现今", "如今距离发布", "此时此刻距离发布", "从发布那时到现在", "过去发布至今", "发布一直到今天", "发布历经岁月",
                "发布随着时间推移", "发布以来", "发布今来", "发布已久有", "发布流传至今", "从发布穿越时光", "发布穿越岁月已达",
                "从发布至今", "跨越发布时空已", "发布经历", "横跨发布有", "发布穿越已", "时光荏苒，发布已过", "一晃发布",
                "发布转瞬之间", "岁月悠悠发布已过", "风雨沧桑发布已过", "发布已过历史长河", "经年累月发布已过", "沧海桑田发布已过", "岁月流转发布已过", "历史沉淀发布已过",
                "一晃眼发布", "不觉间发布", "不知不觉发布", "在时间长河中发布", "在时间流逝中发布", "穿梭时空发布", "跨越千年发布", "跨越百年发布",
                "时光轮转", "岁月长河已发布", "流年似水已发布", "经历风雨已发布", "时光匆匆已发布", "历史传承已发布", "古今交替已发布", "风云变幻已发布",
                "人事更迭已发布", "星移斗转已发布", "日新月异已发布", "光阴荏苒已发布", "天地变迁已发布", "风起云涌已发布", "斗转星移已发布", "岁月蹉跎已发布",
                "历史脚步已发布", "岁月变迁已发布", "在岁月长河中已发布", "随着时光流逝已发布", "一转眼已发布", "昔日到今日已发布", "日积岁累已发布",
                "长年累月已发布", "千年流传已发布", "亘古至今已发布", "一直以来已发布", "历史跨越已发布", "岁月延续已发布", "发布经历漫长时间",
                "随着历史进程发布", "从远古到现代发布", "从古到今发布", "发布一路走来", "发布沧桑变化", "发布从历史中走来", "发布时间延续",
                "发布岁月更替", "发布年复一年", "发布代代相传", "发布经年往事", "发布时间见证"
        };

        // 随机选择一个时间描述
        Random random = new Random();
        return TIME_DESCRIPTIONS[random.nextInt(TIME_DESCRIPTIONS.length)];
    }


    /**
     * 获取当前北京时间，格式为 yyyy-MM-dd HH:mm
     *
     * @return 当前的北京时间字符串
     */
    public static String getCurrentBeijingTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return now.format(DATE_TIME_FORMATTER);
    }

    public static void main(String[] args) {
        String articleId = "144246733";
        System.out.println(getComments(articleId));

        System.out.println(COMMENTS.keySet());
        System.out.println(COMMENTS.keySet().size());

        List<String> comments = getComments(articleId.toString());
        System.out.println(getRandomString(Integer.parseInt(articleId), comments));
    }
}
