package org.zyf.javabasic.test.wzzz;

import com.alibaba.fastjson.JSON;
import org.zyf.javabasic.common.Article;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UrlWriterReadService {
    public static void writeUrlsToFile(List<Article> urlObjects, String directory) {
        // 1. 生成当前时间字符串，格式：yyyyMMdd_HHmmss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        // 2. 生成文件路径
        String fileName = "urls_" + timestamp + ".txt";
        Path filePath = Paths.get(directory, fileName);

        // 3. 确保目录存在
        try {
            Files.createDirectories(filePath.getParent()); // 确保目录存在
        } catch (IOException e) {
            System.err.println("无法创建目录：" + directory);
            return;
        }

        // 4. 写入 URL
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Article obj : urlObjects) {
                writer.write(obj.getUrl());
                writer.newLine();
            }
            System.out.println("URL 列表已成功写入：" + filePath);
        } catch (IOException e) {
            System.err.println("写入文件失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<Article> urlList = CSDNArticles.ARTICLES;

        Set<String> urlInfoList = urlList.stream().map(Article::getUrl).collect(Collectors.toSet());
        System.out.println(JSON.toJSON(urlInfoList));
        System.out.println(urlInfoList.size());

        String directory = "/Users/zyf/Downloads/csdn"; // 指定目录（Windows 示例）

        writeUrlsToFile(urlList, directory);
    }
}
