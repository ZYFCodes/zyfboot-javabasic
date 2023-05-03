//package org.zyf.javabasic.reactivestreams;
//
//import akka.Done;
//import akka.NotUsed;
//import akka.actor.ActorSystem;
//import akka.stream.ActorMaterializer;
//import akka.stream.Materializer;
//import akka.stream.javadsl.Source;
//import akka.stream.javadsl.Sink;
//import akka.stream.javadsl.TwitterSource;
//import com.typesafe.config.ConfigFactory;
//
//import java.util.concurrent.CompletionStage;
//
///**
// * @author yanfengzhang
// * @description 使用 Akka Streams 实现的反应式流应用举例代码，用于从 Twitter 实时数据流中读取推文并将其输出到控制台
// * @date 2023/5/1  19:47
// */
//public class TweetsPrint {
//    public static void main(String[] args) throws Exception {
//        // 创建 Actor 系统和 Materializer
//        ActorSystem system = ActorSystem.create("reactive-streams-example", ConfigFactory.load());
//        Materializer materializer = ActorMaterializer.create(system);
//
//        // 创建 TwitterSource 对象并订阅推文数据流
//        Source<String, NotUsed> tweets = TwitterSource.create(
//                "Consumer Key",
//                "Consumer Secret",
//                "Access Token",
//                "Access Token Secret"
//        ).map(status -> status.getText());
//
//        // 创建 Sink 对象并将推文输出到控制台
//        Sink<String, CompletionStage<Done>> consoleSink = Sink.foreach(System.out::println);
//
//        // 将 Source 和 Sink 连接起来，并运行流处理程序
//        tweets.runWith(consoleSink, materializer);
//    }
//
//}
