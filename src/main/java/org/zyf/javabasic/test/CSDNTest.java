package org.zyf.javabasic.test;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/6  10:50
 */
public class CSDNTest {

    public static void main(String[] args) throws IOException {
        String url = "https://blog.csdn.net/xiaofeng10330111?spm=1011.2124.3001.5343&type=blog";

        Document document = Jsoup.connect(url)
                .userAgent("ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36")
                .get();

        Elements items = document.select("#article_list > div");
        System.out.println(items.size());
    }
}
