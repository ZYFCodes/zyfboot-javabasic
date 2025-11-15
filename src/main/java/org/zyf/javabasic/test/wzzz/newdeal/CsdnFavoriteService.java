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
import org.zyf.javabasic.test.wzzz.like.CSDNFavoriteAPI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 收藏行为
 * @author: zhangyanfeng
 * @create: 2025-04-18 14:10
 **/
@Component
@Slf4j
public class CsdnFavoriteService {

    @Autowired
    private RedisService redisService;

    public void doFavorites(String userIdentification, String cookieString) {
        List<String> zyfUrl = Lists.newArrayList(CSDNArticles.getArticleLinks("https://blog.csdn.net/xiaofeng10330111/article/details/"));
        log.info("用户【{}】需要对{}篇文章进行对应的收藏行为.", userIdentification, zyfUrl.size());
        String favoriteKey = userIdentification.concat("_").concat("favoriteKey");
        String favoriteStr = redisService.get(favoriteKey);
        List<String> favorites = Lists.newArrayList();
        if (StringUtils.isNotBlank(favoriteStr)) {
            favorites = Splitter.on(",").omitEmptyStrings().splitToList(favoriteStr);
        }
        AtomicInteger num = new AtomicInteger();
        List<String> operateFavorites = Lists.newArrayList();
        operateFavorites.addAll(favorites);
        zyfUrl.forEach(zyArticlefUrl -> {
            num.getAndIncrement();
            String[] getIds = zyArticlefUrl.split("/");
            String sourceId = getIds[getIds.length - 1];
            if (operateFavorites.contains(sourceId)) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 已经进行收藏过了，可以直接跳过！！！", userIdentification, num.get(), sourceId);
                return;
            }
            // 获取当前日期的文件名
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("用户【{}】在{}对文章（当前序号：{}）：{} 开始进行收藏操作！！！", userIdentification, currentDate, num.get(), sourceId);
            String res = doFavorite(userIdentification, zyArticlefUrl, cookieString);
            if (StringUtils.containsAny(res, "请求异常")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 已经进行收藏存在异常，可以直接跳过！！！异常类型为：", userIdentification, num.get(), sourceId, res);
                return;
            }
            if (StringUtils.containsAny(res, "返回数据并不合法")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 已经进行收藏请求异常，可以直接跳过！！！{}", userIdentification, num.get(), sourceId, res);
                return;
            }
            if (StringUtils.containsAny(res, "收藏过了")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 已经进行收藏过了收藏过了收藏过了收藏过了收藏过了收藏过了收藏过了收藏过了收藏过了！！！", userIdentification, num.get(), sourceId);
                operateFavorites.add(sourceId);
                return;
            }
            if (StringUtils.containsAny(res, "收藏完成")) {
                log.info("用户【{}】对文章（当前序号：{}）：{} 已经进行收藏完成收藏完成收藏完成收藏完成收藏完成收藏完成收藏完成收藏完成收藏完成收藏完成！！！", userIdentification, num.get(), sourceId);
                operateFavorites.add(sourceId);
                return;
            }
        });
        //将收藏转为string
        String favoritesNowStr = Joiner.on(",").skipNulls().join(operateFavorites);
        redisService.set(favoriteKey, favoritesNowStr);
    }

    private static String doFavorite(String userIdentification, String urlAr, String cookie) {
        OkHttpClient client = new OkHttpClient();

        // 构建 URL
        HttpUrl url = HttpUrl.parse("https://mp-action.csdn.net/interact/wrapper/pc/favorite/v1/api/folderListWithCheck")
                .newBuilder()
                .addQueryParameter("url", urlAr)
                .build();

        // 构建请求
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "*/*")
                .addHeader("origin", "https://blog.csdn.net")
                .addHeader("referer", urlAr)
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("cookie", cookie) // 用真实的 Cookie 替换这里
                .build();

        // 发送请求
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            if (response.isSuccessful()) {
                System.out.println("Status: " + response.code());
                String responseBodyStr = response.body().string();  // 只能调用一次
                // System.out.println("Response Body:\n" + responseBodyStr);

                Result urlResult = JSON.parseObject(responseBodyStr, Result.class);
                if (urlResult == null || urlResult.getCode() != 200) {
                    System.out.println("返回数据并不合法");
                    return "返回数据并不合法";
                }

                FavoriteRes likeInfo = urlResult.getData();
                FavoriteDetail favoriteDetail = likeInfo.getResult().get(0);
                if (favoriteDetail.getIsFavorite()) {
                    //如果为true则收藏过了，直接返回
                    System.out.println("收藏过了====================");
                    return "收藏过了";
                }

                //没有收藏则收藏
                CSDNFavoriteAPI.call(userIdentification, cookie, urlAr, favoriteDetail.getID());

                Thread.sleep(200);

                //check( urlAr,  cookie);
                return "收藏完成";

            } else {
                System.out.println("Request failed: " + response.code());
                return "请求异常Request";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "请求异常IOException";
        } catch (Exception e) {
            e.printStackTrace();
            return "请求异常Exception";
        }
    }

    @Data
    public static class FavoriteRes {
        private Integer total;
        private List<FavoriteDetail> result;
    }

    @Data
    static class FavoriteDetail {
        private Integer ID;
        private Integer IsDefault;
        private String Description;
        private Integer FavoriteNum;
        private Boolean IsFavorite;
        private String Username;
    }

    @Data
    public static class Result {
        private int code;
        private String msg;
        private FavoriteRes data;
    }
}
