//package org.zyf.javabasic.reactivestreams;
//
//import reactor.core.publisher.Flux;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * @author yanfengzhang
// * @description 使用 Reactor 框架实现的反应式流应用举例代码，用于读取文件内容并将其输出到控制台
// * @date 2023/5/1  19:43
// */
//public class FileContentPrint {
//    public static void main(String[] args) throws Exception {
//        Path path = Paths.get("test.txt");
//
//        // 创建 Flux 对象并读取文件内容
//        Flux<String> fileContent = Flux.using(
//                () -> Files.lines(path),
//                Flux::fromStream,
//                stream -> {
//                    try {
//                        stream.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
//
//        // 订阅 Flux 对象并输出文件内容
//        fileContent.subscribe(System.out::println);
//    }
//}
