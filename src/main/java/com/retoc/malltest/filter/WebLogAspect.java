package com.retoc.malltest.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 *  打印请求和相应信息
 */
@Aspect
@Component
public class WebLogAspect {

    private final Logger log= LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.retoc.malltest.model.controller.*.*(..))")
    public void webLog(){

    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes=
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        log.info("URL:" + request.getRequestURL().toString());
        log.info("HTTP:"+request.getMethod());
        log.info("IP:"+request.getRemoteAddr());
        log.info("CLASS_METHOD:"+joinPoint.getSignature().getDeclaringType()
        +"."+joinPoint.getSignature().getName());
        log.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));

    }
    @AfterReturning(returning = "res",pointcut = "webLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        log.info("RESPONSE:"+new ObjectMapper().writeValueAsString(res));
    }
}
