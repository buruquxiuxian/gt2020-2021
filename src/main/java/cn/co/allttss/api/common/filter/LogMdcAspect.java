package cn.co.allttss.api.common.filter;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @title 为异步方法添加traceId
 */
@Aspect
@Component
public class LogMdcAspect {
    private static final String UNIQUE_ID = "TRACE_ID";

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Async)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("test-------------------------qm");
        MDC.put(UNIQUE_ID, UUID.randomUUID().toString().replace("-", ""));
        Object result = point.proceed();// 执行方法
        MDC.remove(UNIQUE_ID);
        return result;
    }
}

