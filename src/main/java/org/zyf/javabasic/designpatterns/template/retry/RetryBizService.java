package org.zyf.javabasic.designpatterns.template.retry;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/15  00:20
 */
@Service
@Slf4j
public class RetryBizService {

    private static Map<Integer, String> aphorisms = Maps.newHashMap();

    static {
        aphorisms.put(1, "知人者智，自知者明。胜人者有力，自胜者强。——老子");
        aphorisms.put(2, "要知道对好事的称颂过于夸大，也会招来人们的反感轻蔑和嫉妒。——培根");
        aphorisms.put(5, "业精于勤，荒于嬉；行成于思，毁于随。——韩愈");
        aphorisms.put(7, "最大的骄傲于最大的自卑都表示心灵的最软弱无力。——斯宾诺莎");
        aphorisms.put(9, "知之者不如好之者，好之者不如乐之者。——孔子");
    }

    /**
     * 获取名言警句
     *
     * @return 名言警句
     */
    public String getAphorisms() throws Exception {
        int randomNumber = new Random().nextInt(10);
        String rersult = aphorisms.get(randomNumber);
        if (StringUtils.isBlank(rersult)) {
            throw new Exception("抱歉！系统异常，暂无数据可进行返回！randomNumber=" + randomNumber);
        }

        return rersult;
    }
}
