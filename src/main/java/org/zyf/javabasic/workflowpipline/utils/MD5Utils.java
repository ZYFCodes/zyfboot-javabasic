package org.zyf.javabasic.workflowpipline.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @program: zyfboot-javabasic
 * @description: md5
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:33
 **/
public final class MD5Utils {

    public static String md5(String text) {
        try {
            return DigestUtils.md5Hex(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("charset UTF-8 is not supported in the system.");
        }
    }

    public static String md5Hex(String text) {
        return DigestUtils.md5Hex(text);
    }

    public static void main(String[] args) {
        System.out.println(md5("近日，天文学家在距离地球仅40光年的系外行星上发现了液态水的迹象，这颗行星围绕一颗红矮星运行，被命名为“K2-18b”。科学家认为这颗行星上可能存在生命。"));
    }
}