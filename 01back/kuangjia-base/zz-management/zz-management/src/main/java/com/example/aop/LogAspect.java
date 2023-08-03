package com.example.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect //切面类
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.example.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
//操作人的ID，当前员工的ID
//        通过获取请求头，解析jwt令牌获取ID
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");
//        操作时间
        LocalDateTime operateTime = LocalDateTime.now();
//        操作类名
        String className = joinPoint.getTarget().getClass().getName();
//        操作方法名
        String methodName = joinPoint.getSignature().getName();
//        操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
//        方法返回值
        long start = System.currentTimeMillis();
        //         调用原始目标方法运行
        Object result = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result);
//        操作耗时（原始方法运行之前，和运行之后）
        long end = System.currentTimeMillis();

        Long costTime = end - start;
        //记录日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录的日志：{}",operateLog);
        return result;
    }

}
