//package org.zyf.javabasic.config;
//
//import java.util.Properties;
//
///**
// * @author yanfengzhang
// * @description 使用 Kafka 生产者或消费者，
// * 可以将配置项封装为一个类或方法，
// * 这样可以避免重复写配置项，提高代码复用性
// * @date 2023/5/5  23:17
// */
//public class KafkaConfig {
//    public static Properties getProducerProps() {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        return props;
//    }
//
//    public static Properties getConsumerProps(String groupId) {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("group.id", groupId);
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        return props;
//    }
//}
