package cn.co.allttss.api.common.config;

import cn.co.allttss.api.common.handler.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;

@Configuration
public class GlobalReturnConfig {
    @RestControllerAdvice
    static class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

        /**
         * <p>Title: supports</p>
         * <p>Description:该组件是否支持给定的控制器方法返回类型和所选的HttpMessageConverter类型。</p>
         *
         * @param returnType    返回值类型
         * @param converterType 转换器类型
         */

        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            // 过滤返回值为'void'的接口
            return !returnType.getMethod().getReturnType().isAssignableFrom(Void.TYPE);
        }

        /**
         * <p>Title: beforeBodyWrite</p>
         * <p>Description:配置统一返回类型 </p>
         *
         * @param body 接口返回值
         */
        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                      ServerHttpResponse response) {
            // 获取请求路径
            String path = request.getURI().getPath();
            /*
             *  如果返回值已封装为Result类型或者为swagger的请求则原样返回，如果接口返回值为String类 型则解析器为StringMessageConverter
             *  解析时出现异常。解决该异常最好的方式就是重写StringMessageConverter方法,但是也可以用一下简单实现
             */
            String time = getTime();
            if (body instanceof Result) {
                return body;
                //} else if (path.contains("/swagger") || path.contains("/v2/api-docs")) {
                //    return body;
            } else if (body instanceof String) {
                return JSON.toJSONString(new Result(true,body, "", "",time));
            }
            return new Result(true, body, "", "",time);
        }

        public String getTime() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
            return simpleDateFormat.format(System.currentTimeMillis());
        }
    }
}