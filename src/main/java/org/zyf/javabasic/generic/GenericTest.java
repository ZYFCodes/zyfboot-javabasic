package org.zyf.javabasic.generic;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/2/1  23:54
 */
public class GenericTest {
    public static void main(String[] args) {
        BizConversion bizConversion=new BizConversion();
        List<Movie> movies = bizConversion.getBizInfoInCheckOnline("tv_series", Movie.class);
        System.out.println(movies);
    }

}
