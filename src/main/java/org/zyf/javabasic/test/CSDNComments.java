package org.zyf.javabasic.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.io.InputStream;
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

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = CSDNComments.class.getClassLoader().getResourceAsStream("comments.json")) {
            if (inputStream == null) {
                throw new Exception("comments.json not found in resources");
            }
            COMMENTS = objectMapper.readValue(inputStream, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load comments", e);
        }
    }

    public static List<String> getComments(String key) {
        return COMMENTS.getOrDefault(key, Lists.newArrayList());
    }

    public static void main(String[] args) {
        String articleId = "105360860";
        System.out.println(getComments(articleId));
    }
}
