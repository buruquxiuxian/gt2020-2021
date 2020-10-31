package cn.co.allttss.api.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MvcJsonConverter());
    }

    /**
     * 使用LogInterceptor时的开启方法
     */
//    @Autowired
//    private LogInterceptor logInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor);
//        super.addInterceptors(registry);
//    }

}