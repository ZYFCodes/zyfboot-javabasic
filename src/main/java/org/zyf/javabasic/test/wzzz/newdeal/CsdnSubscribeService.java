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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: CsdnSubscribeService
 * @author: zhangyanfeng
 * @create: 2025-04-20 21:11
 **/
@Component
@Slf4j
public class CsdnSubscribeService {

    @Autowired
    private RedisService redisService;

    public void doSubscribes(String userIdentification, String cookie) {
        List<String> columnIds = Lists.newArrayList("12948277", "12948276", "12948267", "11646135",
                "12948280", "12953748",  "12940370", "12948284", "12948282", "12953489", "12948283", "12948279", "12948281", "8448196"
        );
        log.info("当前【{}】需要对{}篇专栏进行订阅操作行为,专栏ID为：{}", userIdentification, columnIds.size(), columnIds);
        String subscribeKey = userIdentification.concat("_").concat("subscribeKey");
        String subscribeStr = redisService.get(subscribeKey);
        List<String> subscribes = Lists.newArrayList();
        if (StringUtils.isNotBlank(subscribeStr)) {
            subscribes = Splitter.on(",").omitEmptyStrings().splitToList(subscribeStr);
        }
        AtomicInteger num = new AtomicInteger();
        List<String> operateSubscribes = Lists.newArrayList();
        operateSubscribes.addAll(subscribes);
        for (int i = 0; i < columnIds.size(); i++) {
            num.getAndIncrement();
            String columnId = columnIds.get(i);
            if (subscribes.contains(columnId)) {
                log.info("用户【{}】对专栏（当前序号：{}）：{} 已经进行订阅过了，可以直接跳过！！！", userIdentification, num.get(), columnId);
                continue;
            }
            // 获取当前日期的文件名
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("用户【{}】在{}对专栏（当前序号：{}）：{} 开始进行订阅操作！！！", userIdentification, currentDate, num.get(), columnId);
            String getInfo = doSubscribe(cookie, columnId);
            if (StringUtils.equalsIgnoreCase(getInfo, "urlResult is null")) {
                log.info("用户【{}】对专栏（当前序号：{}）：{} 开始进行订阅操作存在空返回，先对这篇专栏进行忽略！！！！", userIdentification, num.get(), columnId);
                continue;
            }
            if (!StringUtils.equalsIgnoreCase(getInfo, "订阅成功")) {
                log.info("用户【{}】对专栏（当前序号：{}）：{} 开始进行订阅操作微，先对这篇专栏进行忽略！！！！", userIdentification, num.get(), columnId);
                continue;
            }


            operateSubscribes.add(columnId);
            String currentDateEnd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("用户【{}】在{}对专栏{}（当前序号：{}）进行订阅操作,操作完成✅！", userIdentification, currentDateEnd, columnId, num.get());
        }
        //将订阅转为string
        String subscribesNowStr = Joiner.on(",").skipNulls().join(operateSubscribes);
        redisService.set(subscribeKey, subscribesNowStr);
    }

    public static String doSubscribe(String cookie, String columnId) {
        try {
            OkHttpClient client = new OkHttpClient();

            // 构造请求体（这里是 x-www-form-urlencoded，假设订阅参数是 articleId=144597752）
            RequestBody body = new FormBody.Builder()
                    .add("columnId", columnId)
                    .build();

            // 构造请求
            Request request = new Request.Builder()
                    .url("https://blog.csdn.net/phoenix/web/v1/subscribe/subscribe")
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

            SubscribeInfo subscribeInfo = urlResult.getData();
            if (subscribeInfo.getStatus()) {
                System.out.println("订阅成功=====================VVVVVVVVVVVVVVVVV============================：" + columnId);
            } else {
                Thread.sleep(300);
                System.out.println("未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，现在开始订阅：" + columnId);
                return doSubscribe(cookie, columnId);
            }

            Thread.sleep(300);
            response.close();
            return "订阅成功";
        } catch (Exception e) {
            System.out.println("doSubscribe error:" + e);
            e.printStackTrace();
            return "订阅失败";
        }
    }

    @Data
    static class SubscribeInfo {
        private String msg;
        private Boolean status = false;
    }

    @Data
    static class Result {
        private int code;
        private String message;
        private SubscribeInfo data;
    }
}
