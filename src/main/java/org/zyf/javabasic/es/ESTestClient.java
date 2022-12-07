package org.zyf.javabasic.es;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/11/17  16:39
 */
public class ESTestClient {
//    public static void queryData(RestHighLevelClient porosClient) throws IOException {
//        SearchRequest request = new SearchRequest("shop");
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        request.source(builder);
//        builder.query(QueryBuilders.termQuery("shopid",1101));
//        builder.size(1);
//        builder.from(0);
//        SearchResponse response = porosClient.search(request, RequestOptions.DEFAULT);
//        System.out.println("结果为:"+ response);
//    }

    public static void main(String[] args) throws ParseException {
        List<String> newRelations = Lists.newArrayList();
        newRelations.add("1");
        newRelations.add("2");
        Set<String> intersectionSet = Sets.newHashSet();
        intersectionSet.add("1");

        List<String> newAddRelations = Lists.newArrayList(newRelations);
        newAddRelations.removeAll(intersectionSet);


        System.out.println(newRelations);
        System.out.println(newAddRelations);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.format(new Date(Long.parseLong(String.valueOf(1669100748000L)))));
        System.out.println(sd.format(new Date(System.currentTimeMillis())));
    }
}
