package org.zyf.javabasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述：启动入口类
 *
 * @author yanfengzhang
 * @date 2019-12-19 18:11
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.zyf.javabasic"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableSwagger2
public class ZYFApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ZYFApplication.class, args);

//        // 使用 MyBean
//        MyBean myBean = context.getBean(MyBean.class);
//        myBean.doSomething(); // 调用 MyBean.doSomething() 方法
    }

//    @Bean
//    public MyBean myBean(){
//        return new MyBean();
//    }

//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.setPort(8080);
//        return tomcat;
//    }
}
