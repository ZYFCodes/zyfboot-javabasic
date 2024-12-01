package org.zyf.javabasic.test.csdn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNComments
 * @author: zhangyanfeng
 * @create: 2024-09-28 18:56
 **/
public class CSDNComments {
    public static final Map<String, List<String>> COMMENTS;
    public static final List<String> limitedComments;

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
        List<String> randomCommonComents = getRandomComments(COMMENTS.get("commentComments"), 8);
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

    public static void main(String[] args) {
        String articleId = "10536ee0860";
        System.out.println(getComments(articleId));

        System.out.println(COMMENTS.keySet());
        System.out.println(COMMENTS.keySet().size());
    }
}
