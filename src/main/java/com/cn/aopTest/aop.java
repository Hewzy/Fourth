package com.cn.aopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class aop {
    // 相当于创建一个Log工厂，不需要再new对象
    Logger logger = LoggerFactory.getLogger(aop.class);
    // 定义切入点 service 下面的addUser方法
    @Pointcut("execution(public * com.cn.service..*.addUser(..))")
    public void addLog(){
        System.out.println("这里执行添加的方法");
    }

    // 定义切入点service下面的delete方法
    @Pointcut("execution(public * com.cn.service..*.delete(..))")
    public void delete(){
        System.out.println("这里执行删除的方法");
    }

    @Before("addLog()||delete()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("模拟目标方法之前");
        logger.info("目标方法为："+ joinPoint.getSignature().getDeclaringTypeName()+""
                + joinPoint.getSignature().getName());
        logger.info("参数为："+Arrays.toString(joinPoint.getArgs()));
        logger.info("被织入的对象"+joinPoint.getTarget());
    }

    @AfterReturning(returning = "returnValue",pointcut = "addLog()||delete()")
    public void doAfterReturning(JoinPoint joinPoint , Object returnValue){
        logger.info("模拟日志记录........");
        logger.info("目标方法为："+joinPoint.getSignature().getDeclaringTypeName()+" "
                +joinPoint.getSignature().getName());
        logger.info("参数为："+Arrays.toString(joinPoint.getArgs()));

    }
}
