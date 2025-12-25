package org.zyf.javabasic.controller.csdn;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.aop.bizdeal.entity.dto.CsdnUserInfo;
import org.zyf.javabasic.test.wzzz.newdeal.CommentSubmitService;
import org.zyf.javabasic.test.wzzz.queue.QueueBatchManager;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: zyfboot-javabasic
 * @description: 测试评论
 * @author: zhangyanfeng
 * @create: 2025-12-21 14:23
 **/
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoNewCommentsTest {

    @Resource
    private QueueBatchManager queueBatchManager;
    @Resource
    private CommentSubmitService commentSubmitService;

    @Test
    public void doNewComments() throws Exception {
        CsdnUserInfo csdnUserInfo1 = new CsdnUserInfo();
        csdnUserInfo1.setUserIdentification("18055158695");
        csdnUserInfo1.setPwdOrVerifyCode("uuid_tt_dd=10_20886655040-1766297792609-270042; dc_session_id=10_1766297792609.877090; dc_sid=48da4a288811e0dd626ca5b13205f6f2; c_pref=; c_ref=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; fid=20_94549462945-1766297804978-022236; c_first_ref=default; c_first_page=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; c_dsid=11_1766297804979.432282; c_segment=10; creative_btn_mp=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1766297813; HMACCOUNT=D0DCC5A536360255; hide_login=1; log_Id_click=1; SESSION=fe2edabb-3288-4c22-a4db-ac551f390ce2; _clck=70gulj%5E2%5Eg21%5E0%5E2181; _clsk=zm5noq%5E1766297817109%5E1%5E0%5Ek.clarity.ms%2Fcollect; UserName=2501_92763450; UserInfo=9f23f6a487364af79619a3fc020f8398; UserToken=9f23f6a487364af79619a3fc020f8398; UserNick=Orelly; AU=0EE; UN=2501_92763450; BT=1766297833978; p_uid=U010000; c_page_id=default; csdn_newcert_2501_92763450=1; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1766297835; vip_auto_popup=1; log_Id_view=28; log_Id_pv=3; dc_tos=t7lw4t");
        CsdnUserInfo csdnUserInfo2 = new CsdnUserInfo();
        csdnUserInfo2.setUserIdentification("19267274671");
        csdnUserInfo2.setPwdOrVerifyCode("uuid_tt_dd=10_20886655040-1766297792424-155792; dc_session_id=10_1766297792424.376249; dc_sid=6eb80b76f1e6d4e7f7b31f738fd4dff1; c_pref=; c_ref=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; fid=20_10297278002-1766297863250-411805; c_first_ref=default; c_first_page=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; c_dsid=11_1766297863250.328792; c_segment=0; _clck=qkyu1s%5E2%5Eg21%5E0%5E2181; https_waf_cookie=6db89b07-2c8f-4a11205ab95e8067c8652572192a53858c93; bc_bot_session=17662978640d92fb7de4a2ec5a; hide_login=1; SESSION=793bc65e-f68c-452f-ba6e-fe97a7cc19b3; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1766297869; HMACCOUNT=EBFB5299131038E7; UserName=2501_92763362; UserInfo=d8beecf331e342f9971d816418ad4ea4; UserToken=d8beecf331e342f9971d816418ad4ea4; UserNick=Go%E5%AF%93%E8%A8%80; AU=743; UN=2501_92763362; BT=1766297889705; p_uid=U010000; csdn_newcert_2501_92763362=1; vip_auto_popup=1; dc_tos=t7lw6c; tfstk=gm8tLiGNGXFOppgIePmnnpgLZ8lnKDAwjdR7otXgcpppgKUgSZbGcs6p3PYDiItYkQWUSCYMi5-eEQaMSc70kN7VlYDor4cw_Z7j4ptKLRrCs1g0lZsf7wgeUZvKr4Aw1D5_9P3kjJ7cQs6flGsfO66FNo_b5sGddsCzcR9flXhdM16fGiZ1dk15wZ6XhZGpO95fl19flXdCLsGOVz6I1tavjIczLWw5ZraXJ1IIxC6IVd8C6NBW1FUbl_1OXTO1pAe3AMIvBGLnW859AQpG6Kuz7Nsvf3_6kY3ORQ-HC6pjeoBB2F8AqFH84OTN3LQ6vvaOlN89qg7x255HWBTPDFkTROpeO3bwPvzvFBAk7M8xeJ62j_7C6HhbAOsf408kyCOQETC0fXhL0oS1Tpbs9WAC-sPc9TcTForVxW5dEX3Y0oS1B6BotLr40MgF.; c_page_id=default; dc_tos=t7lw6g; log_Id_pv=4; creative_popup=%7B%22arrowIcon%22%3A%22https%3A//i-operation.csdnimg.cn/images/d97ff21f43594e6ebdd097d835750e40.png%22%2C%22img%22%3A%22https%3A//i-operation.csdnimg.cn/images/283294459cf14a9e9d5c1091b1ed3f20.png%22%2C%22imgStyle%22%3A%22height%3A%2088px%3B%22%2C%22darkCfg%22%3A%7B%7D%2C%22role%22%3A%22noPost%22%2C%22report%22%3A%7B%22spm%22%3A%223001.11120%22%2C%22extra%22%3A%22%22%7D%2C%22style%22%3A%22%22%2C%22arrowIconStyle%22%3A%22%22%2C%22url%22%3A%22https%3A//www.csdn.net/blogstar2025%3Futm_source%3D571302904%22%2C%22newTab%22%3Afalse%2C%22userName%22%3A%222501_92763362%22%7D; creative_btn_mp=2; log_Id_view=50; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1766297897; _clsk=1fsm0jv%5E1766297896890%5E2%5E0%5Ek.clarity.ms%2Fcollect; log_Id_click=5");

        try {
            queueBatchManager.add(csdnUserInfo1);
            queueBatchManager.add(csdnUserInfo2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }
    @Test
    public void doSubmit(){
        long start = System.currentTimeMillis();
        CsdnUserInfo userInfo = new CsdnUserInfo();
        userInfo.setUserIdentification("18055158695");
        userInfo.setPwdOrVerifyCode("uuid_tt_dd=10_20886655040-1766297792609-270042; dc_session_id=10_1766297792609.877090; dc_sid=48da4a288811e0dd626ca5b13205f6f2; c_pref=; c_ref=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; fid=20_94549462945-1766297804978-022236; c_first_ref=default; c_first_page=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; c_dsid=11_1766297804979.432282; c_segment=10; creative_btn_mp=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1766297813; HMACCOUNT=D0DCC5A536360255; hide_login=1; log_Id_click=1; SESSION=fe2edabb-3288-4c22-a4db-ac551f390ce2; _clck=70gulj%5E2%5Eg21%5E0%5E2181; _clsk=zm5noq%5E1766297817109%5E1%5E0%5Ek.clarity.ms%2Fcollect; UserName=2501_92763450; UserInfo=9f23f6a487364af79619a3fc020f8398; UserToken=9f23f6a487364af79619a3fc020f8398; UserNick=Orelly; AU=0EE; UN=2501_92763450; BT=1766297833978; p_uid=U010000; c_page_id=default; csdn_newcert_2501_92763450=1; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1766297835; vip_auto_popup=1; log_Id_view=28; log_Id_pv=3; dc_tos=t7lw4t");

        String userIdentification = userInfo.getUserIdentification();
        String pwdOrVerifyCode = userInfo.getPwdOrVerifyCode();
        log.info("用户【{}】操作对张彦峰在线CSDN所有文章进行评论行为===============================================", userIdentification);

        log.info("处理用户：{}, 开始操作对张彦峰在线CSDN所有文章进行评论行为！！！！！！！！！", userIdentification);
        //评论操作执行
        commentSubmitService.doSubmit(userIdentification, pwdOrVerifyCode);
        long end = System.currentTimeMillis();
        String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
    }
}
