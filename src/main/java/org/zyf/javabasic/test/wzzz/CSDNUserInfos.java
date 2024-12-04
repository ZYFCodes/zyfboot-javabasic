package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: csdn账号整合
 * @author: zhangyanfeng
 * @create: 2024-11-19 22:20
 **/
public class CSDNUserInfos {
    public static final Map<String, String> userInfo = Maps.newHashMap();
    public static final Map<String, String> userNewInfo = Maps.newHashMap();

    static {
        //18586128339   思亘er csdn20142014
        userInfo.put("18252060161", "csdn20142014");
        //18586128339   思亘er csdn20142014
        userInfo.put("18586128339", "csdn20142014");
        //15847798961   TopicOnline csdn20142014
        userInfo.put("15847798961", "csdn20142014");
        //13347179833    馨儿也会写代码啦 csdn20142014
        userInfo.put("13347179833", "csdn20142014");
        //13521362017    政伯 csdn20142014
        userInfo.put("13521362017", "csdn20142014");
        //13370136591    CloudZzzzzzz csdn20142014
        userInfo.put("13370136591", "csdn20142014");
        //13848271208  csdn20142014
        userInfo.put("13848271208", "csdn20142014");
        //1024171279@qq.com  cry123 陈荣岩
        userInfo.put("1024171279@qq.com", "cry123");
        //13624089959 wangxu@12345  是小旭啊
        userInfo.put("13624089959", "wangxu@12345");
        //15604910811  abc20242024   泰是我大哥
        userInfo.put("15604910811", "abc20242024");
        //18602465406 abc20252025 阿泰最暖心
        userInfo.put("18602465406", "abc20252025");
        //15604915152 abc20262026 高洪岩
        userInfo.put("15604915152", "abc20262026");
        //13998250413 abc20272027  方腾飞飞起来
        userInfo.put("13998250413", "abc20272027");
        //18642184846 abc20282028 芬达～
        userInfo.put("18642184846", "abc20282028");
        //PC端注册地址：https://passport.csdn.net/login?code=applets
        //15547246711     刘嘉 csdn20142014
        userInfo.put("15547246711", "csdn20142014");
        //17678001703    李子謇 csdn20142014
        userInfo.put("17678001703", "csdn20142014");
        //15560826537   柳家欣 csdn20142014
        userInfo.put("15560826537", "csdn20142014");
        //18547268447   刘嘉馨 csdn20142014
        userInfo.put("18547268447", "csdn20142014");
        //15047214489   苏荣 csdn20142014
        userInfo.put("15047214489", "csdn20142014");
        //15547292224   潘晨光 csdn20142014
        userInfo.put("15547292224", "csdn20142014");
        //13214967685   潘子旭 csdn20142014
        userInfo.put("13214967685", "csdn20142014");
        //15147258480   柳永利 csdn20142014
        userInfo.put("15147258480", "csdn20142014");
        //15048150450   程颐猛 csdn20142014
        userInfo.put("15048150450", "csdn20142014");
        //13015250347   张霄昀 csdn20142014
        userInfo.put("13015250347", "csdn20142014");
        //13319640252   成猛 csdn20142014
        userInfo.put("13319640252", "csdn20142014");
        //15847208659   张帆 csdn20142014
        userInfo.put("15847208659", "csdn20142014");
        //15124824144   刘丽娜 csdn20142014
        userInfo.put("15124824144", "csdn20142014");
        //18586169318   @@佳慧@@ csdn20142014
        userInfo.put("18586169318", "csdn20142014");
        //18648461677   贾慧啊 csdn20142014
        userInfo.put("18648461677", "csdn20142014");
        //18434063443 毕小龙 csdn20142014
        userInfo.put("18434063443", "csdn20142014");
        //15849249060  龙龙2028 csdn20142014
        userInfo.put("15849249060", "csdn20142014");
        //18648486708  徐威龙 csdn20142014
        userInfo.put("18648486708", "csdn20142014");
        //15540724495  刘飞跃 csdn20142014
        userInfo.put("15540724495", "csdn20142014");

        //新增加的账号
        //15141901140 小红  csdn20142014
        userNewInfo.put("15141901140", "csdn20142014");
        //18842306752 闯哥 csdn20142014
        userNewInfo.put("18842306752", "csdn20142014");
        //18766148225 超 csdn20142014
        userNewInfo.put("18766148225", "csdn20142014");
        //15637908500 鸿昌 csdn20142014
        userNewInfo.put("15637908500", "csdn20142014");
        //15670398290 鸿昌 csdn20142014
        userNewInfo.put("15670398290", "csdn20142014");
        //17538573025 鸿昌 csdn20142014
        userNewInfo.put("17538573025", "csdn20142014");
        //18638922808 阿珂 csdn20142014
        userNewInfo.put("18638922808", "csdn20142014");
        //18293145328 财财  xxx12301001
        userNewInfo.put("18293145328", "xxx12301001");
        // 14795997599 少婷 Chenshaoting1028
        userNewInfo.put("14795997599", "Chenshaoting1028");
        //18690325433 景峰 csdn20142014
        userNewInfo.put("18690325433", "csdn20142014");
        //17799189833 景峰 csdn20142014
        userNewInfo.put("17799189833", "csdn20142014");
        //17767414616 景峰 csdn20142014
        userNewInfo.put("17767414616", "csdn20142014");
        //13734716339 张慧芳 csdn20142014
        userNewInfo.put("13734716339", "csdn20142014");
        //13212141499 张亚鑫 csdn20142014
        userNewInfo.put("13212141499", "csdn20142014");
        //18840850877 吴翰禺 csdn20142014
        userNewInfo.put("18840850877", "csdn20142014");

    }

    public static Map<String, String> getUserInfo() {
        userInfo.putAll(userNewInfo);
        return userInfo;
    }

    public static void main(String[] args) {
        System.out.println(getUserInfo());
        System.out.println(getUserInfo().size());
    }
}
