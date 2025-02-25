package org.zyf.javabasic.java8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.common.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选，排序，聚合等。
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 * @author yanfengzhang
 * @date 2019-11-27 15:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StreamTest {

    public static void main(String[] args) {
        List<GroupBrandCateBO> list = new ArrayList<>(
                Arrays.asList(
                        new GroupBrandCateBO("v1", "g1", "b1"),
                        new GroupBrandCateBO("v1", "g2", "b2"),
                        new GroupBrandCateBO("v1", "g2", "b2"),
                        new GroupBrandCateBO("v3", "g3", "b3")
                )
        );
        Map<String, String> map = list.stream().collect(
                Collectors.toMap(GroupBrandCateBO::getVersion, GroupBrandCateBO::getGroupCode, (oldVal, currVal) -> oldVal, LinkedHashMap::new));
        System.out.println(map.getClass());
        Map<String, String> map0 = list.stream().collect(
                Collectors.toMap(GroupBrandCateBO::getVersion, GroupBrandCateBO::getGroupCode, (oldVal, currVal) -> oldVal));
        System.out.println(map0.getClass());

        Map<String, String> map00 = list.stream().collect(
                Collectors.toMap(GroupBrandCateBO::getVersion, GroupBrandCateBO::getGroupCode, (oldVal, currVal) -> currVal));
        Map<String, String> map01 = list.stream().collect(
                Collectors.toMap(GroupBrandCateBO::getVersion, GroupBrandCateBO::getGroupCode, (oldVal, currVal) -> oldVal + currVal));
        System.out.println(map00.toString());
        System.out.println(map01.toString());
    }

    static class GroupBrandCateBO {
        String version;
        String groupCode;
        String brand;

        GroupBrandCateBO(String version, String groupCode, String brand) {
            this.version = version;
            this.groupCode = groupCode;
            this.brand = brand;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        String getGroupCode() {
            return groupCode;
        }

        public void setGroupCode(String groupCode) {
            this.groupCode = groupCode;
        }

        String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    @Test
    public void testCreateStream() {
        /**
         * 集合
         * 这种数据源较为常用，通过stream()方法即可获取流对象
         */
        List<User> list = new ArrayList<User>();
        Stream<User> listStream = list.stream();

        /**
         * 数组
         * 通过Arrays类提供的静态函数stream()获取数组的流对象
         */
        String[] names = {"chaimm", "peter", "john"};
        Stream<String> stringStream = Arrays.stream(names);

        /**
         * 值
         * 直接将几个值变成流对象
         */
        Stream<String> streamTest = Stream.of("chaimm", "peter", "john");

        /**
         * 文件
         */
        try (Stream lines = Files.lines(Paths.get("文件路径名"), Charset.defaultCharset())) {
            //可对lines做一些操作
        } catch (IOException e) {
        }

        /**
         * iterator
         * 创建无限流
         */
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

}
