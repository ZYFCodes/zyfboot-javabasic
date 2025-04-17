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
        List<String> randomCommonComents = getRandomComments(COMMENTS.get("commentComments"), 55);
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
        //comment = getRandomTimePrefix() + " " + comment ;
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
                // 返回第一种情况，只调用一个随机词
//                if (random.nextBoolean()) {
//                    // 返回 MoodWords
//                    return AncientChineseTime.getAncientTime() + "  " + CurrentMomentWords.getRandomCurrentMomentWord()+ "  " + MoodWords.getRandomMoodWord();
//                } else {
//                    // 返回 TimeWords
//                    return AncientChineseTime.getAncientTime() + "  " + CurrentMomentWords.getRandomCurrentMomentWord() + "  " +  TimeWords.getRandomTimeWord();
//                }
                return AncientChineseTime.getAncientTime();
            case 1:
                // 返回第二种情况，只调用一个随机词
//                if (random.nextBoolean()) {
//                    // 返回 MoodWords
//                    return TimeDescription.getRandomTimeDescription("", 0, true)
//                            + "  " + CurrentMomentWords.getRandomCurrentMomentWord()
//                            + "  " +  MoodWords.getRandomMoodWord();
//                } else {
//                    // 返回 TimeWords
//                    return TimeDescription.getRandomTimeDescription("", 0, true)
//                            + "  " + CurrentMomentWords.getRandomCurrentMomentWord();
////                            + "  " +  TimeWords.getRandomTimeWord();
//                }
                return TimeDescription.getRandomTimeDescription("", 0, true);
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
                "距今已", "时至今日", "现如今已有", "直至现在已过", "至今为止已有", "到今天有", "到此时已", "到此刻已",
                "迄今有", "至今有", "时至此刻已过", "迄今为止已有",  "至今",
                "到现在", "如今距离", "此时此刻距离", "从那时到现在", "过去至今", "到今天", "历经岁月",
                "随着时间推移",  "今来", "穿越时光", "穿越岁月已达",
                "跨越时空已", "经历", "横跨","时光荏苒已", "一晃",
                "转瞬之间", "岁月悠悠已", "风雨沧桑已", "经年累月已", "沧海桑田已", "岁月流转已", "历史沉淀已",
                "一晃眼", "不觉间已", "不知不觉",
                "时光轮转", "岁月长河已", "流年似水已", "经历风雨已", "时光匆匆已", "古今交替已", "风云变幻已",
                "人事更迭已", "星移斗转已", "日新月异已", "光阴荏苒已", "天地变迁已", "风起云涌已", "斗转星移已", "岁月蹉跎已",
                "历史脚步已", "岁月变迁已", "在岁月长河中已", "随着时光流逝已", "一转眼已", "昔日到今日已", "日积岁累已",
                "长年累月已", "千年流传已", "亘古至今已", "一直以来已", "历史跨越已", "岁月延续已", "经历漫长时间",
                "随着历史进程", "从远古到现代", "从古到今", "一路走来", "沧桑变化", "从历史中走来", "时间延续",
                "岁月更替", "年复一年", "代代相传", "经年往事", "时间见证"
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
        String articleId = "145668734";
        System.out.println(getComments(articleId));

        System.out.println(COMMENTS.keySet());
        System.out.println(COMMENTS.keySet().size());

        List<String> comments = getComments(articleId.toString());
        System.out.println(getRandomString(Integer.parseInt(articleId), comments));
    }
}
