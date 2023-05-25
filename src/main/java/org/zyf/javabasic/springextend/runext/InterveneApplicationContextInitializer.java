package org.zyf.javabasic.springextend.runext;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

/**
 * @author yanfengzhang
 * @description 通过实现ApplicationContextInitializer接口，我们可以在ApplicationContext创建之前对其进行一些定制化的修改。
 * 这个接口定义了一个initialize方法，接受一个ConfigurableApplicationContext对象作为参数，我们可以在这个方法中对这个对象进行修改和配置。
 * 具体来说，我们可以通过ApplicationContextInitializer实现以下扩展任务：
 * 1.修改Spring Boot默认的environment属性。使用configurableApplicationContext.getEnvironment()方法获取到environment对象，从而修改环境变量，例如添加自定义配置文件路径。
 * 2.添加自定义的PropertySource。使用environment.getPropertySources().addLast(propertySource)方法，可以添加自定义的属性源，从而实现更灵活的配置。
 * 3.注册自定义bean。使用configurableApplicationContext.getBeanFactory().registerSingleton(beanName, bean)方法，可以注册自定义的bean，从而实现更灵活的依赖注入。
 * <p>
 * 可以通过添加自定义的ApplicationContextInitializer。通过使用SpringApplication.addInitializers(initializer)方法，可以添加自定义的ApplicationContextInitializer实现类，从而扩展应用程序的初始化逻辑。
 * 总之，通过实现ApplicationContextInitializer接口，可以在Spring Boot应用程序启动之前对应用程序进行一些初始化定制化的操作，从而满足开发者对应用程序的特殊需求。
 * @date 2021/2/23  23:48
 */
public class InterveneApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        // 添加自定义配置文件路径
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("InterveneApplicationContextInitializer initialize :" + configurableApplicationContext);
            environment.getPropertySources().addFirst(new ResourcePropertySource("classpath:zyftest.properties"));
            System.out.println("InterveneApplicationContextInitializer initialize add FirstResourcePropertySource  classpath:zyftest.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 添加自定义的PropertySource
        PropertySource<?> propertySource = new MyPropertySource("myPropertySource");
        environment.getPropertySources().addLast(propertySource);
        System.out.println("InterveneApplicationContextInitializer initialize add PropertySource  myPropertySource");

        ConfigurableListableBeanFactory beanFactory = configurableApplicationContext.getBeanFactory();
        // 注册自定义Bean
        MyBean myBean = new MyBean();
        beanFactory.registerSingleton("myBean", myBean);
        System.out.println("InterveneApplicationContextInitializer initialize registerSingleton  myBean");
    }

    // 自定义PropertySource
    private static class MyPropertySource extends PropertySource<String> {
        private static final String MY_PROPERTY_SOURCE_KEY = "my.property.source.key";

        public MyPropertySource(String name) {
            super(name);
        }

        @Override
        public Object getProperty(String name) {
            if (MY_PROPERTY_SOURCE_KEY.equals(name)) {
                return "myPropertySourceValue";
            }
            return null;
        }
    }

    // 自定义Bean
    private static class MyBean {
        private String name = "myBean";

        public String getName() {
            return name;
        }
    }
}
