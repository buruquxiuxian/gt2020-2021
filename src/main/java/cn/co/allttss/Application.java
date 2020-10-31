package cn.co.allttss;

import cn.co.allttss.api.common.config.*;
import cn.co.allttss.api.common.filter.LogMdcAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@ServletComponentScan//Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
@EnableTransactionManagement
@Import({LogAopConfig.class, LogMdcAspect.class, CorsConfig.class, RedisConfig.class, SpringContextHolder.class, GlobalReturnConfig.class, MvcConfig.class})
//@ConfigurationProperties(prefix="spring.datasource")
//@MapperScan(basePackages = {"cn.co.gaoxoaotong.api.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
