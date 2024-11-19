package org.zyf.javabasic.test.worddeal;

import java.io.*;
import java.nio.file.*;

/**
 * @program: zyfboot-javabasic
 * @description: 文件切分
 * @author: zhangyanfeng
 * @create: 2019-11-19 23:10
 **/
public class FileSplitter {
    // 方法：将大文件切割成多个小文件
    // inputFilePath：大文件路径
    // outputDir：输出的小文件夹路径
    // chunkSize：每个小文件的大小（字节）
    public static void splitFile(String inputFilePath, String outputDir, long chunkSize) throws IOException {
        // 获取大文件的路径和总大小
        Path inputPath = Paths.get(inputFilePath);
        long totalSize = Files.size(inputPath);
        // 计算切割成多少个小文件
        int chunkCount = (int) Math.ceil((double) totalSize / chunkSize);

        // 使用 BufferedReader 逐行读取大文件
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            for (int i = 0; i < chunkCount; i++) {
                // 为每个小文件创建一个输出路径
                String outputFilePath = outputDir + "/chunk_" + i + ".txt";

                // 使用 BufferedWriter 将文件内容写入到小文件
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
                    long currentChunkSize = 0; // 当前小文件的大小
                    String line;
                    // 逐行读取大文件内容，直到小文件达到指定大小
                    while ((line = reader.readLine()) != null && currentChunkSize < chunkSize) {
                        writer.write(line);
                        writer.newLine();
                        currentChunkSize += line.length();
                    }
                }
            }
        }
    }

    // 主方法：执行文件切割操作
    public static void main(String[] args) throws IOException {
        String inputFilePath = "large_text.txt"; // 输入的大文件路径
        String outputDir = "chunks"; // 输出的小文件夹路径
        long chunkSize = 100 * 1024 * 1024; // 每个小文件的大小为 100MB

        // 调用文件切割方法
        splitFile(inputFilePath, outputDir, chunkSize);
    }
}
