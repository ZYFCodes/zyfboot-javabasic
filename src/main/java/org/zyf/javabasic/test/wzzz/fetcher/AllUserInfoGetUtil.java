package org.zyf.javabasic.test.wzzz.fetcher;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: zyfboot-javabasic
 * @description: r
 * @author: zhangyanfeng
 * @create: 2025-03-23 12:04
 **/
public class AllUserInfoGetUtil {

    public static List<AllUserInfo> allUserInfoList;
    public static Map<String,String> userNameMap = new HashMap<>();

    static {
        try {
            loadAllUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        allUserInfoList.forEach(allUserInfo -> {
            userNameMap.put(allUserInfo.getUserNum(), allUserInfo.getUserName());
        });
    }

    private static void loadAllUserInfo() throws IOException {
        //模拟配置后台结果
        InputStream inputStream = AllUserInfoGetUtil.class.getResourceAsStream("/all-userinfo.json");
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String attitudesStr = scanner.hasNext() ? scanner.next() : "";
        allUserInfoList = JSON.parseArray(attitudesStr, AllUserInfo.class);
        if (CollectionUtils.isEmpty(allUserInfoList)) {
            allUserInfoList =  Lists.newArrayList();
            return;
        }
    }

    public static List<AllUserInfo> getAllUserInfos(){
        return allUserInfoList;
    }

    public static String getUserName(String userNumber) {
        return userNameMap.getOrDefault(userNumber,"");
    }

    public static void main(String[] args) {
        List<AllUserInfo> allUserInfoList = getAllUserInfos();
        System.out.println(JSON.toJSON(allUserInfoList));
        String userNumber = "15847798961";
        String userName = getUserName( userNumber);
        System.out.println(userName);
    }
}
