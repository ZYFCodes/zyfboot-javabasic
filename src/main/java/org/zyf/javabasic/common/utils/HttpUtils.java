package org.zyf.javabasic.common.utils;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/26  12:12
 */
public class HttpUtils {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("cookie","uuid_tt_dd=10_17305139580-1626749169640-127923; __gads=ID=182ba910e3eb9edb-22aeeeef6cca003c:T=1626749205:RT=1626749205:S=ALNI_Ma_ukQ49SzYdI5FNJpfuObHraVGVA; ssxmod_itna=eqAxBDRieDqCuxBPOe5DkiHYY5ew484AKKdD/KKwDnqD=GFDK40EYBquNmbqvubDI2+qNEKloarKvBeCnozvQ=eiTD4q07Db4GkDAqiOD7u4+YD4f3D+DD4DEU4Gj0DzLkkDixiUS65PeqQWakDleD0PFHDCeDQcKDPcPDvxi81P9oAxDakc9Ihppd1ay7cOO7DQHkzpPDUerGve7eS7ijr=GqQGD5K7Dqyt4xwQlYC0hqF70YsYBTe7r6buizq0iPH5x4YYD===; ssxmod_itna2=eqAxBDRieDqCuxBPOe5DkiHYY5ew484AKKG9Q5CKBDBk7D7K4GaYUBA5QBUKsE2iyNeSnOApih7DUnrRG5hUlyDwQafupcp2QQf36+08aLKTaSUiLWdqnCAMOQ2fkpk6fRcOFkQbVjwU2zfA2UAPqpR3KrCogG+0FDAPPO8RctMEougQevCDhnmRnKxbi68WiP8jsInWKrSa//mtvAmtKyCjTOntiX8TLml2dd+ciOGR8UPcAnqnBFf9yIzOSDKSrj4TEIQKjh69BIQtArGk7yaARVZ6XV16OhId2tvI3VPMlm6AoZmWBT1ixzSh1gRrBTHejrTr3OODVG1dmkdC+9x+Yx+on8cud2APISFXw+7kh3ih9xx+W327wpg79hPNLiiloEEhrmIXAzAhkb+CdQ5OqrQqbEx7wGDMEd67=y+pQofYofP1v9bpNqrZeoZji8ch70eD7jihD5GGHjeK0xSiGufqKC9n4wB9FkDHOWppYwt6487D50hpFH7eqVRANAF1nHklHuSqzRBqMBeLji27Q0IrmwqsFoqq=qICGhddeqmK4Bx2j+duYOB9XD53DGcDG73wxKtqlC5oExk=xorqncxbWPGgPKiwAwUK0r2D6XB48jxq7Sbm4GeqdaUCA3B7BLlPtiwuiDD=; UserName=xiaofeng10330111; UserInfo=3ccfe7d74ab1433ebd8282251aa2aa2b; UserToken=3ccfe7d74ab1433ebd8282251aa2aa2b; UserNick=张彦峰ZYF; AU=F6D; UN=xiaofeng10330111; BT=1626749181073; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac={\"islogin\":{\"value\":\"1\",\"scope\":1},\"isonline\":{\"value\":\"1\",\"scope\":1},\"isvip\":{\"value\":\"0\",\"scope\":1},\"uid_\":{\"value\":\"xiaofeng10330111\",\"scope\":1}}; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_17305139580-1626749169640-127923!5744*1*xiaofeng10330111; c_first_ref=www.baidu.com; c_segment=3; dc_sid=0926b61384edf57bb01dcf30e483613c; __gpi=00000000-0000-0000-0000-000000000000&Y3Nkbi5uZXQ=&Lw==; __yadk_uid=CgVUE5bs7YKqK7mEt7qz27zNG4StXDdp; FCCDCF=[null,null,[\"[[],[],[],[],null,null,true]\",1630899595645],null,null]; c_hasSub=true; log_Id_click=101; c_first_page=https://blog.csdn.net/ianly123/article/details/82658622; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1630900533,1630900588,1630909133,1630983681; dc_session_id=10_1631000438512.464457; c_pref=https://www.baidu.com/link; c_ref=https://blog.csdn.net/xiaofeng10330111/article/details/107635951; c_page_id=default; dc_tos=qz1zzq; log_Id_pv=524; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1631000440; log_Id_view=512");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String zyfurls = "https://blog.csdn.net/xiaofeng10330111/article/details/106080301\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/118000193\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/117075767\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/116803687\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/107635951\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106943391\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105148032\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105124900\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105078547\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/90384502\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87272559\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87272495\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87272337\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87272248\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87271881\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87271644\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87271456\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87271101\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86772740\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86772650\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86770057\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86605409\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86596105\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86596036\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85682513\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85254052\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253976\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253886\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253615\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253477\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253412\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/118000193\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/117075767\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/116803687\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/107635951\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106943391\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106091174\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106086563\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106086455\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106086200\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106086035\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106081590\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106081236\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106081077\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106080861\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106080490\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106080394\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106080301\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106080206\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106080007\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106079903\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/106079740\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105652399\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105633821\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105631666\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105608235\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105413134\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105361100\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105361083\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105361059\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105361028\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105361002\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105360974\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105360939\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105360860\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/105358094\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/88667231\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/88641737\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/100706167\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/104836403\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/97183370\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87958174\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/87272970\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86694766\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86517016\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516999\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516988\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516955\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516943\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516911\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516890\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516876\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/86516867\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85330132\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253353\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85253251\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85169642\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85163814\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85163793\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/85162029\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/84936252\n" +
                "https://blog.csdn.net/xiaofeng10330111/article/details/53034130";
        List<String> urlList = Splitter.on("\n").omitEmptyStrings().trimResults().splitToList(zyfurls);
        System.out.println(urlList);

        urlList.forEach(randomInt -> {
            HttpUtils.sendPost(randomInt, null);
            System.out.println("随机访问网站请求网站为：" + randomInt);
        });

    }
}
