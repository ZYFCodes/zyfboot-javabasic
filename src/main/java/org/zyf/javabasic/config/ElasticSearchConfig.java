//package org.zyf.javabasic.config;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2022/11/16  16:17
// */
//@Configuration
//public class ElasticSearchConfig {
//    @Value("${elasticsearch.url}")
//    private String url;
//
//    @Value("${elasticsearch.port}")
//    private String port;
//
//    @Bean
//    public RestHighLevelClient restHighLevelClient(){
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(url, 9200)));
//        return client;
//    }
//}
