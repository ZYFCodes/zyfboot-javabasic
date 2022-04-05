package org.zyf.javabasic.aop.complex.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * @author yanfengzhang
 * @description http://localhost:8080/swagger-ui.html 处理页面
 * @date 2020/11/10  23:39
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.zyf.javabasic"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        List<VendorExtension> extensions = Lists.newArrayList();
        Contact contact = new Contact("张彦峰", "https://zyfcodes.blog.csdn.net", "1273740896@qq.com");
        VendorExtension vendorExtension = new VendorExtension() {
            @Override
            public String getName() {
                return "AOP知识实践";
            }

            @Override
            public Object getValue() {
                return "https://zyfcodes.blog.csdn.net/article/details/106086455";
            }
        };
        extensions.add(vendorExtension);

        return new ApiInfoBuilder()
                .title("彦峰本地相关测试 API文档")
                .description("只针对平时一些技术类学习写的代码进行基本测试分析使用！有问题代码请按指定邮箱联系！")
                .termsOfServiceUrl("https://zyfcodes.blog.csdn.net/")
                .contact(contact)
                .extensions(extensions)
                .version("1.0")
                .build();
    }
}
