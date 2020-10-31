package cn.co.allttss.api.common.config;

import cn.co.allttss.api.common.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Configuration
public class LogAopConfig {
    @Resource
    private HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(LogAopConfig.class);

    /**
     * 定义切点Pointcut
     * 第一个*号：表示返回类型， *号表示所有的类型
     * 第二个*号：表示类名，*号表示所有的类
     * 第三个*号：表示方法名，*号表示所有的方法
     * 后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut("execution(*  cn.co.allttss.api.controller.*.*(..))")
    public void executionService() {
    }

    /**
     * 方法调用之前调用
     *
     * @param joinPoint
     */
    @Before(value = "executionService()")
    public void doBefore(JoinPoint joinPoint) {
        //String requestId = String.valueOf(UUID.randomUUID());
        //MDC.put("requestId", requestId);
        //IpFilter ipFilter = new IpFilter();
        //String ip = ipFilter.getWebIP();
        String ip =  IpUtil.getIpAddr(request);
        String url = request.getRequestURL().toString();

        logger.info("=====>url：{}", url);
        logger.info("=====>ip地址：{}", ip);
        logger.info("=====>@Before：处理开始，请求参数为：{}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法之后调用
     *
     * @param joinPoint
     * @param returnValue 方法返回值
     */
    @AfterReturning(pointcut = "executionService()", returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) {
        logger.info("=====>@AfterReturning：处理结束");
        //logger.info("=====>@AfterReturning：处理结束，响应参数为：{}", returnValue);
        // 处理完请求，返回内容
        //MDC.clear();
    }

}