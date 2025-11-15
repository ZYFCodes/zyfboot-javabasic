package org.zyf.javabasic.test.wzzz.newdeal;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.redis.RedisService;
import org.zyf.javabasic.test.wzzz.CSDNArticles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 点赞行为
 * @author: zhangyanfeng
 * @create: 2025-04-18 13:35
 **/
@Component
@Slf4j
public class CsdnLikeService {

    @Autowired
    private RedisService redisService;

    public void doLikes(String userIdentification, String cookie) {
        List<Integer> articleIds = new ArrayList<>(CSDNArticles.articleIds());
        log.info("当前【{}】需要对{}篇文章进行分析操作点赞操作行为,文章ID为：{}",userIdentification, articleIds.size(), articleIds);
        String likeKey = userIdentification.concat("_").concat("likeKey");
        String likeStr = redisService.get(likeKey);
        List<String> likes = Lists.newArrayList();
        if (StringUtils.isNotBlank(likeStr)) {
            likes = Splitter.on(",").omitEmptyStrings().splitToList(likeStr);
        }
        AtomicInteger num = new AtomicInteger();
        List<String> operateLikes = Lists.newArrayList();
        operateLikes.addAll(likes);
        for (int i = 0; i < articleIds.size(); i++) {
            num.getAndIncrement();
            Integer articleId = articleIds.get(i);
            if (likes.contains(articleId.toString())) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 已经进行点赞过了，可以直接跳过！！！", userIdentification, num.get(), articleId.toString());
                continue;
            }
            // 获取当前日期的文件名
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("用户【{}】在{}对文章（当前序号：{}）：{} 开始进行点赞操作！！！", userIdentification, currentDate, num.get(), articleId);
            String getInfo = doLike(cookie, articleId.toString());
            if (StringUtils.containsAny(getInfo, "次数已达上限")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 开始进行点赞操作已达上限，对其他文章不在进行分析！！！", userIdentification, num.get(), articleId);
                break;
            }

            if (StringUtils.equalsIgnoreCase(getInfo, "urlResult is null")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 开始进行点赞操作存在空返回，先对这篇文章进行忽略！！！！", userIdentification, num.get(), articleId);
                continue;
            }

            if (!StringUtils.equalsIgnoreCase(getInfo, "点赞成功")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 开始进行点赞操作微，先对这篇文章进行忽略！！！！", userIdentification, num.get(), articleId);
                continue;
            }

            operateLikes.add(articleId.toString());
            String currentDateEnd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("用户【{}】在{}对文章{}（当前序号：{}）进行点赞操作,操作完成✅！", userIdentification, currentDateEnd, articleId, num.get());
        }
        //将点赞转为string
        String likesNowStr = Joiner.on(",").skipNulls().join(operateLikes);
        redisService.set(likeKey, likesNowStr);
    }

    public static String doLike(String cookie, String articleId) {
        try {
            OkHttpClient client = new OkHttpClient();

            // 构造请求体（这里是 x-www-form-urlencoded，假设点赞参数是 articleId=144597752）
            RequestBody body = new FormBody.Builder()
                    .add("articleId", articleId)
                    .build();

            // 构造请求
            Request request = new Request.Builder()
                    .url("https://blog.csdn.net//phoenix/web/v1/article/like")
                    .post(body)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                    .header("Origin", "https://blog.csdn.net")
                    //.header("Referer", "https://blog.csdn.net/2401_86609655/article/details/144597752?spm=1001.2014.3001.5502")
                    .header("X-Requested-With", "XMLHttpRequest")
                    // 注意：此处的 Cookie 很长，你可以只保留必要的几个认证字段（如 SESSION, UserToken）
                    .header("Cookie", cookie)
                    .build();

            // 发起请求
            Call call = client.newCall(request);

            Response response = call.execute();

            // 打印响应信息
            int code = 0;
            String responseBodyStr = "";
            if (response.isSuccessful()) {
                code = response.code();
                responseBodyStr = response.body().string();  // 只能调用一次
                System.out.println("请求成功！");
                System.out.println("响应码: " + code);
                System.out.println("响应体: " + responseBodyStr);
            } else {
                System.out.println("请求失败，状态码: " + code);
            }

            Result urlResult = JSON.parseObject(responseBodyStr, Result.class);
            if (urlResult == null || urlResult.getCode() != 200) {
                String describtionDetail = Objects.nonNull(urlResult) ? urlResult.getMessage() : "urlResult is null";
                System.out.println("返回数据并不合法:" + describtionDetail);
                return describtionDetail;
            }

            String describtion = urlResult.getMessage();
            if (StringUtils.containsAny(describtion, "今日点赞次数已达上限") && urlResult.getCode() == 400) {
                System.out.println(describtion);
                return describtion;
            }

            LikeInfo likeInfo = urlResult.getData();
            if (likeInfo.getStatus()) {
                System.out.println("点赞成功=====================VVVVVVVVVVVVVVVVV============================：" + articleId);
            } else {
                Thread.sleep(300);
                System.out.println("未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，现在开始点赞：" + articleId);
                return doLike(cookie, articleId);
            }

            Thread.sleep(300);
            response.close();
            return "点赞成功";
        } catch (Exception e) {
            System.out.println("doLike error:" + e);
            e.printStackTrace();
            return "点赞失败";
        }
    }

    @Data
    static class LikeInfo {
        private Integer like_num;
        private Boolean status = false;
    }

    @Data
    static class Result {
        private int code;
        private String message;
        private LikeInfo data;
    }
}
