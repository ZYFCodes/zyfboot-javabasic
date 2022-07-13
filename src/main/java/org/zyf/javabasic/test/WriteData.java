package org.zyf.javabasic.test;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/9/7  17:52
 */

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;

public class WriteData {
    public static void writeToFile(String data, String fileNamePath) {
        byte[] sourceByte = data.getBytes();
        if (null != sourceByte) {
            try {
                //文件路径（路径+文件名）
                File file = new File(fileNamePath);
                if (!file.exists()) {
                    //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                //文件输出流将数据写入文件
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(sourceByte);
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                // do something
            } finally {
                // do something
            }
        }
    }

    public static void main(String[] args) {
        String[] dd = {"x", "n"};
        System.out.println(StringUtils.containsAny("sssssssssiijjhgfghjnbvcxdfghnbvc", dd));
    }
}
