package org.zyf.javabasic.guava.stream;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * 描述：简化流操作
 * guava提供了一个源与汇的概念对应读写字节字符共有4个类ByteSource,CharSource,ByteSink,CharSink
 *
 * @author yanfengzhang
 * @date 2019-12-30 20:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StreamTest {
    private URL url = new URL("https://i.csdn.net/#/uc/collection-list");
    private File file = new File("/Users/yanfengzhang/eclipse-workspace/zyf/test.txt");

    public StreamTest() throws MalformedURLException {
    }

    @Test
    public void basic() throws ExecutionException, IOException {

        /*从url中获得字节数组*/
        Resources.toByteArray(url);
        /*从url中获得字符源*/
        Resources.asCharSource(url, Charsets.UTF_8);
        /*从url中获得字节源*/
        Resources.asByteSource(url);

        /*从文件中获得字节数组*/
        Files.toByteArray(file);
        /*获得字符源*/
        CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);
        /*获得字节源*/
        ByteSource byteSource = Files.asByteSource(file);
        /*获得字符汇 以append追加方式（覆盖方式可以省略*/
        CharSink charSink = Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND);
        /*或者字节汇 以append追加方式（覆盖方式可以省略）*/
        ByteSink byteSink = Files.asByteSink(file, FileWriteMode.APPEND);

        /*字节源转字符源*/
        byteSource.asCharSource(Charsets.UTF_8);
        /*字节汇转字符汇*/
        byteSink.asCharSink(Charsets.UTF_8);

        /*读取出byte[]*/
        byte[] bytes = byteSource.read();
        /*读取出String*/
        String string = charSource.read();
        /*字节源copy到汇 可以使用OutputStream*/
        byteSource.copyTo(byteSink);
        /*字符源copy到汇 可以使用Appendable*/
        charSource.copyTo(charSink);
        /*写入字节*/
        byteSink.write(bytes);
        /*写入字符（使用的是CharSequence，可以用String）*/
        charSink.write(string);
    }

    @Test
    public void testStream() throws ExecutionException, IOException {
        /*逐行读取 以utf-8编码*/
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        /*读取单词*/
        Multiset<String> wordOccurrences = HashMultiset.create(
                //以空格拆分
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        //获取文件按照sha1生成的hash码
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        //File的工具类Files
        //将url资源copy到file中
        Resources.asByteSource(url).copyTo(Files.asByteSink(file));

        CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);
        String string = charSource.read();

        TestCase.assertTrue(string.startsWith("<!DOCTYPE") && string.endsWith("</html>"));
    }

}
