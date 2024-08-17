package org.zyf.javabasic.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/10/31  11:13
 */
public class BaseTest {
    //创建 HttpClient 的实例
    private static HttpClient client = new DefaultHttpClient();
    private static String host="https://blog.csdn.net/";
    public static void main(String[] args) throws IOException {
        //构建一个POST请求
        HttpPost post = new HttpPost(host);
        //构建表单参数
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("username", "18586128339"));
        formParams.add(new BasicNameValuePair("password", "csdn20142014"));
        //将表单参数转化为“实体”
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
        //将“实体“设置到POST请求里
        post.setEntity(entity);

        //提交POST请求
        HttpResponse response = client.execute(post);

        HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
        String content = EntityUtils.toString(result);;//用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
        System.out.println(content);
    }
}
