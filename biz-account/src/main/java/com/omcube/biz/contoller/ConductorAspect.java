package com.omcube.biz.contoller;

import com.omcube.comm.annotation.Conductor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ConductorAspect {

    @Pointcut("@annotation(com.omcube.comm.annotation.Conductor)" )
    public void addAdvice(){
    }

    @Around("addAdvice()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature sign =  (MethodSignature)pjp.getSignature();
        Method method = sign.getMethod();
        Conductor annotation = method.getAnnotation(Conductor.class);
        //获取需要执行的流程服务
        //接口入参
        Object[] args = pjp.getArgs();

        //执行返回
        //Object o = pjp.proceed(args);

        //服务调度情况
        return "abc";

    }


}
