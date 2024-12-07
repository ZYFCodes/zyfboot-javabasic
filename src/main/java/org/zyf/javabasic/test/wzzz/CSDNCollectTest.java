package org.zyf.javabasic.test.wzzz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNCollectTest
 * @author: zhangyanfeng
 * @create: 2024-12-06 21:33
 **/
public class CSDNCollectTest {

    public static void main(String[] args) {
//        // 创建HttpClient实例
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            // 构造GET请求
//            HttpGet httpGet = new HttpGet("https://blog.csdn.net/phoenix/web/v1/isCollect?articleId=139639510");
//
//            String cookie ="";
//            cookie="uuid_tt_dd=10_17862519460-1727669887241-272040; fid=20_79733434169-1727669887445-187579; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; c_segment=8; HMACCOUNT=AF46696B098133A7; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608312=1; csdn_newcert_m0_74022416=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_XWL9875435=1; csdn_newcert_BXL3456=1; csdn_newcert_JH8876434=1; csdn_newcert_LH34568=1; csdn_newcert_LLN98765=1; csdn_newcert_ZF265467=1; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_PZX9845=1; csdn_newcert_PZX87654323=1; csdn_newcert_SR9872345686689=1; csdn_newcert_JX275431234568=1; csdn_newcert_pengyou23452=1; csdn_newcert_DJV2980765=1; csdn_newcert_LJ11111111111111=1; csdn_newcert_macbookpro11=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; csrfToken=d4DGJtn5C5NyXyH8qWL93s8e; csdn_newcert_LFY5678=1; fpv=4d2e9e704d25dbbd6675cbbd7d2fd13a; csdn_newcert_xiaofeng10330111=1; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_17862519460-1727669887241-272040!5744*1*pengyou23452; _ga=GA1.2.122272612.1727670031; _ga_7W1N0GEY1P=GS1.1.1730104369.14.1.1730104422.7.0.0; c_dl_prid=-; c_dl_rid=1730130196413_815010; c_dl_fref=https://download.csdn.net/; c_dl_fpage=/; c_dl_um=-; LH34568comment_new=1730940230748; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1731911112; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1731911112; __gads=ID=d23f7510d6636e6e:T=1727670033:RT=1731911113:S=ALNI_MZGD1pzgr06RMW3ZFW_batcuAJgWA; __gpi=UID=00000f273bc94a9a:T=1727670033:RT=1731911113:S=ALNI_MaCNtJz1QKs-8Ht7u7TMjv3z_6itw; __eoi=ID=7f8251b0aa589042:T=1727670033:RT=1731911113:S=AA-AfjZ-kDNbDfGNY2mruf_VAqiS; FCNEC=%5B%5B%22AKsRol_7tveVp3iiuWPi4l6OUOdt2QQ2yre-V0B91Ja4nCJy6GiaaOe2RHf9-IFlXvbPYxIROceB9jYsldwwLoQc9tojIgm6FCPgs0xP__I3O8ohq9u_ezBmen3cJRrtOC7NbvPmxhTay4NQRsUEXnKdxTuiQvDq4A%3D%3D%22%5D%5D; m0_74022416comment_new=1731423458479; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1732890932; https_waf_cookie=5d8e7e70-238b-429a0e11af56aa7db4debce7235b2b805283; historyList-new=%5B%5D; csdn_newcert_AKE2014=1; csdn_newcert_zcc_victor=1; csdn_newcert_JF20141203=1; csdn_newcert_JFF201412031708=1; csdn_newcert_JFJFJFJFJFJF2014=1; tfstk=fW3KTnNhSCCLBA1OXgtgEL7pZ4OMm4hExvlfr82hVAHtaYQh-z43V7wtUw0oZbiRyjyN-X0nZ6oZnj_n-HzlyyzzPKvDoEvETzzWIL89ZJls_7axKY0tTXzP_gT1_NGFeVQeE0aSFPZ_a745RwM55Rwaa76QNwO967y_OMNCAf6_i7P5RYaW6fwaNfsA78-7nanlo_JUP9ohPagBzkeAdRNa_VUUv-Z_BaNKWWELhXgU_Az7wmgYYx9GxJix0xFmKF_KHXMKcogCCdUrsmH8M2OfD8HE12qsRQj4_zoIcygJH9GipvuEcX9GX5nstVEilKQ8YcME0u3kIEznmj0zc2Td3Rq4wxUIXd_Khg5toqeB_t2YZMO96gSzA5-FTM1LTuQpm5eDsvsP4lI46-A9YgSzA5PTnCbF4grO0; dc_sid=90353178a2c99a3f78c96e3f32eaf07b; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1733368347680%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF%22%2C%22blog-threeH-dialog-expa%22%3A1732070788716%7D; csdn_newcert_weixin_48602413=1; SESSION=6bb12604-0f06-4f9d-ac43-8a310b0d58e2; UserName=xiaofeng10330111; UserInfo=acfec759e19c4e9aac04b464c7354a65; UserToken=acfec759e19c4e9aac04b464c7354a65; UserNick=%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF; AU=F6D; UN=xiaofeng10330111; BT=1733385894196; p_uid=U110000; _clck=1kqghg7%7C2%7Cfrh%7C0%7C1734; is_advert=1; c_first_ref=wiki.fintopia.tech; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111/article/details/134363363%3Fops_request_misc%3D%25257B%252522request%25255Fid%252522%25253A%25252233ca884bd2a260fc5e7c6e9d269d3455%252522%25252C%252522scm%252522%25253A%25252220140713.130102334.pc%25255Fblog.%252522%25257D%26request_id%3D33ca884bd2a260fc5e7c6e9d269d3455%26biz_id%3D0%26utm_medium%3Ddistribute.pc_search_result.none-task-blog-2%7Eblog%7Efirst_rank_ecpm_v1%7Erank_v31_ecpm-1-134363363-null-null.nonecase%26utm_term%3D%25E8%25A7%2584%25E5%2588%2599%26spm%3D1018.2226.3001.4450; c_hasSub=true; creative_btn_mp=3; c_utm_source=vip_hyzx_fc_toolbar1; fe_request_id=1733476564826_0688_5645821; referrer_search=1733476572921; xiaofeng10330111comment_new=1733450062954; c_pref=https%3A//so.csdn.net/so/search%3Fq%3DSQL%26t%3Dblog%26u%3Dxiaofeng10330111; c_ref=https%3A//blog.csdn.net/xiaofeng10330111%3Fspm%3D1008.2028.3001.5343; _clsk=1875qb0%7C1733483195848%7C1%7C0%7Cw.clarity.ms%2Fcollect; dc_session_id=10_1733483207934.813952; c_dsid=11_1733483214132.423485; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A222%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%22xiaofeng10330111%22%7D; waf_captcha_marker=ee84eec0991c40cf4250dbbe0226257114ec7e57d30628c0ff203312507743b9; c_page_id=default; dc_tos=so2k8n; log_Id_pv=2; log_Id_view=28; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1733483256";
//
//            // 设置必要的请求头
//            httpGet.addHeader("accept", "*/*");
//            httpGet.addHeader("accept-encoding", "gzip, deflate, br, zstd");
//            httpGet.addHeader("accept-language", "zh-CN,zh;q=0.9");
//            httpGet.addHeader("connection", "keep-alive");
//            httpGet.addHeader("sec-fetch-mode", "cors");
//            httpGet.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");
//            httpGet.addHeader("cookie", cookie);
//
//            // 发送请求并获取响应
//            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//                // 获取响应状态码
//                int statusCode = response.getStatusLine().getStatusCode();
//                System.out.println("响应状态码: " + statusCode);
//
//                // 获取响应体
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    String responseBody = EntityUtils.toString(entity);
//                    System.out.println("响应内容: " + responseBody);
//
//                    // 解析JSON响应
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    ResponseData responseData = objectMapper.readValue(responseBody, ResponseData.class);
//
//                    // 打印解析后的数据
//                    System.out.println("状态码: " + responseData.code);
//                    System.out.println("消息: " + responseData.message);
//                    System.out.println("收藏状态: " + (responseData.data.status ? "已收藏" : "未收藏"));
//                    System.out.println("提示: " + responseData.data.tips);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        test();
    }

    // 定义响应数据结构
    static class ResponseData {
        public int code;
        public String message;
        public String traceId;
        public Data data;

        static class Data {
            public String tips;
            public boolean status;
        }
    }


    public static void test() {
        // 请求 URL
        String url = "https://mp-action.csdn.net/interact/wrapper/pc/favorite/v1/api/addFavoriteInFolds";

        String cookie ="";
        cookie="uuid_tt_dd=10_10799280460-1729181125668-130168; fid=20_69363885578-1729181127059-913233; c_first_ref=default; c_segment=8; HMACCOUNT=2B5DA67F120A89C5; csdn_newcert_weixin_48861542=1; csdn_newcert_LJ11111111111111=1; csdn_newcert_DJV2980765=1; csdn_newcert_pengyou23452=1; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_LFY5678=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_XWL9875435=1; csdn_newcert_BXL3456=1; csdn_newcert_JH8876434=1; csdn_newcert_LH34568=1; csdn_newcert_LLN98765=1; csdn_newcert_ZF265467=1; csdn_newcert_JX275431234568=1; csdn_newcert_SR9872345686689=1; csdn_newcert_PZX9845=1; csdn_newcert_PZX87654323=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_macbookpro11=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_xiaofeng10330111=1; c_ins_prid=-; c_ins_rid=1729313983688_920679; c_ins_fref=https://edu.csdn.net/; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1729314060817; csdn_newcert_2401_86608312=1; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608357=1; FCCDCF=%5Bnull%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2C%5B%5B13%2C%22%5B%5C%22DBABL~BVQqAAAAAg%5C%22%2C%5B%5B7%2C%5B1731248991%2C853317000%5D%5D%5D%5D%22%5D%5D%5D; FCNEC=%5B%5B%22AKsRol8gVNU26Bs-_1fmCVflnpjp9UbJsrzD5BwEmq4b-l-PuF-zORb1gaeWT5DLtHtgG71s7DIlQK3987I3iskSn1o7jOs3cqzA_85pfhAsB96CKkSynBMp_r35MtkzLinnf-D3wSuvJGrrsaCCa6haF5J29Sg5lA%3D%3D%22%5D%5D; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1731828510; historyList-new=%5B%5D; ssxmod_itna=eqjxyGqiw40DzEKXhxQw6rDBi8GCDmr2SxAIRxD/KQsDnqD=GFDK40EvOp=A0IiI8j2G3hEQe8qLxa7pCordfjjeReD=xYQDwxYoDUxGtDpxG=3hzDYAkDt4DTD34DYDiO=DBRkIQDFAL6BfqdReTduAqDEYqQDYwQDmTbDno0ZSwtj4htDG3bDzqiDf4QDIdNRB4GnD0jaourSD03o=1aixqGWW2=ixPGuRQQUD0pMnwNRnZhQA44s9BeFj7G=/0xdYix3j/qADhMiQBwNQOlsI7+LzAhtNX13eD===; ssxmod_itna2=eqjxyGqiw40DzEKXhxQw6rDBi8GCDmr2SxAKdG9bgDBkYCAx7Pp2OH8S3LFGF4=kvEsIIguRjNV44C9Is3t3PfN7557Qerskt/OIhsK0bL3OLqW/dENwdZoc57BAdQL4uwmhbu5VFlwGv4lAxHRNEGT61iAYhFuvx1bjUt3AfK0doon3wBQT0/dRi8bI07f6xWLfkj4TImkGmGTnxuCuThkbf/Ocwlf3rZ8rcC3IPPpj3wONl8GoTqbxI/C9ty4LzxR8wFYf=6GaIlM8BjBukm2yIl+D9QF1AwkfYXufxLj=wRj//Umi/oYOrxEDDwx=DjKDeLx4D===; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; csdn_newcert_2401_89357748=1; 2401_89357748comment_new=1733035052763; dp_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NzY5MTE0NSwiZXhwIjoxNzMzODQ0ODY5LCJpYXQiOjE3MzMyNDAwNjksInVzZXJuYW1lIjoiMjQwMV84OTM1Nzc0OCJ9.FFc9zKvrORaQvvdDgEOBrT8wTc8FtEufvsLuuIkLN9w; csdn_newcert_qq_42878414=1; csdn_newcert_2401_89358905=1; csdn_newcert_2401_89360015=1; csdn_newcert_2401_89359839=1; csdn_newcert_2401_89360075=1; csdn_newcert_AKE2014=1; csdn_newcert_zcc_victor=1; csdn_newcert_STC91s=1; csdn_newcert_JF20141203=1; csdn_newcert_JFF201412031708=1; csdn_newcert_JFJFJFJFJFJF2014=1; csdn_newcert_2401_89465532=1; csdn_newcert_2401_89473361=1; csdn_newcert_u011374437=1; tfstk=fLaxgf1q1_dxe8OJ2hsoIVjifFfukPF4m-PBSADDf8e8_RLDoV0mffM8QE4gs5Z9WWkyoS40sIrzKWT0oiuMWquZ59Xh-wf4gVu6I6cehKuS6X-fScg1_deP89Xh-ajXNVBAK-0NGSK-_YGj1VGfwYGIFn9_CxiWFXhn5Vg_C4iS_XH6GVG_NaMr1VM_CVG6weM5GATTj36jklOrxFT_lNoxphDrab4-MmHIVYLsMzhxDvNboasexfEQJmyCEnDbvkyKt-bWkJFbHkiQlL6-77qLBXUfOHHT37amVr1MYbuackgbW9pba-e4ukNfE3h8VS4-qy6pWmr73koTSTbEokV37DafkpkoYfeKluBWkJsPry4Krrvnpfxfw_KwbmGP0I-XM2dUSYl-KspBbhokabHhwi-wbmGrwvfuVh-ZqQ5..; dc_sid=90353178a2c99a3f78c96e3f32eaf07b; xiaofeng10330111comment_new=1733408575038; hide_login=1; p_uid=U010000; tianshu11tianshucomment_new=1733408575038; m0_74022498comment_new=1733330102240; m0_74022416comment_new=1733329758365; _clck=odihnm%7C2%7Cfrh%7C0%7C1751; xiaoxu2022xiaoxucomment_new=1733416087149; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1733445119:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1733445119:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1733445119:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; 2401_86609655comment_new=1733416087149; SESSION=e1b80ccb-7ee8-4f75-8dbe-4be2902faeef; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1733476867815%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22CloudZzzzzzz%22%2C%22blog-threeH-dialog-expa%22%3A1729305626760%7D; c_hasSub=true; csdn_newcert_2401_89496617=1; csdn_newcert_2401_89496917=1; csdn_newcert_akppp=1; csdn_newcert_sinat_20516251=1; UserName=weixin_48602413; UserInfo=0ecbb8b8900149839d5d42a63e389969; UserToken=0ecbb8b8900149839d5d42a63e389969; UserNick=%40%40boBo%40%40; AU=D8A; UN=weixin_48602413; BT=1733481716337; csdn_newcert_weixin_48602413=1; dc_session_id=10_1733491671726.162894; c_dsid=11_1733491847625.122546; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22weixin_48602413%22%7D; c_pref=https%3A//blog.csdn.net/weixin_48602413; c_ref=https%3A//mp.csdn.net/mp_blog/creation/success/105413134; c_page_id=default; log_Id_pv=17; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1733492125; log_Id_view=219; _clsk=1ofe1ts%7C1733492126464%7C3%7C0%7Cn.clarity.ms%2Fcollect; log_Id_click=16; dc_tos=so2r3c\n";

        // 创建 HTTP 客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 POST 请求
            HttpPost httpPost = new HttpPost(url);

            // 设置 Headers
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");
            httpPost.setHeader("Origin", "https://blog.csdn.net");
            httpPost.setHeader("Referer", "https://blog.csdn.net/xiaofeng10330111/article/details/139639510");
            httpPost.setHeader("Cookie", "cookie");

            // 设置 Body (Payload)
            Map<String, Object> payload = new HashMap<>();
            payload.put("author", "xiaofeng10330111");
            payload.put("description", "文章浏览阅读2.5w次，点赞105次，收藏83次...");
            payload.put("folderIdList", new int[]{41336561});
            payload.put("fromType", "PC");
            payload.put("source", "blog");
            payload.put("sourceId", 140538842);
            payload.put("title", "从ES的JVM配置起步思考JVM常见参数优化eeeeeeeeee");
            payload.put("url", "https://blog.csdn.net/xiaofeng10330111/article/details/140538842");
            payload.put("username", "xiaofeng10330111");

            // 将 payload 转换为 JSON 格式
            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(payload), "UTF-8");
            httpPost.setEntity(entity);

            // 执行请求
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // 解析响应
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("响应状态码: " + response.getStatusLine().getStatusCode());
            System.out.println("响应内容: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
