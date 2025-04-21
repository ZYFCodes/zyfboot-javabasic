package org.zyf.javabasic.redis;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.test.wzzz.like.AllUserInfoArticleLikeGetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: RedisTestController
 * @author: zhangyanfeng
 * @create: 2025-04-18 12:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void tewt() {
        redisService.set("ee","1,2,3");

        System.out.println(redisService.get("ee"));

        redisService.set("ee","1,2,3,4");

        System.out.println(redisService.get("ee"));

        System.out.println(redisService.get("19221409194_likeKey"));
        System.out.println(redisService.get("19221409194_favoriteKey"));

    }

    @Test
    public void testToLikes() {
        Map<String, List<String>> likes = AllUserInfoArticleLikeGetUtil.getUserAndArticlesLikesMap();
        // 将 Map 的条目转换为 List
        List<Map.Entry<String,  List<String>>> entryList = new ArrayList<>(likes.entrySet());

        for (Map.Entry<String, List<String>> entry : entryList) {
            List<String> likesForUser = entry.getValue();
            if(CollectionUtils.isEmpty(likesForUser)){
                log.info("User:{} has no likes!!!!!!!!!!!!!!!!!!",entry.getKey());
                continue;
            }

            String likesForUserSkipNulls = Joiner.on(",").skipNulls().join(likesForUser);
            String likeKey = entry.getKey().concat("_").concat("likeKey");
            redisService.set(likeKey,likesForUserSkipNulls);
            log.info("User:{} put likes to redis!!! likes.sise={}",entry.getKey(),likesForUser.size());
        }

    }
}
