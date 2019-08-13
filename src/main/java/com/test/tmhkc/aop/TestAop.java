package com.test.tmhkc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class TestAop {

    @Pointcut("@annotation(com.test.tmhkc.anno.TestAnno)")
    public void testPointcut(){

    }

    @Before("@annotation(com.test.tmhkc.anno.TestAnno)")
    public void testBefore(JoinPoint point){
        log.info("前置方法!");
        log.info("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        log.info("@Before：参数为：" + Arrays.toString(point.getArgs()));
        log.info("@Before：被织入的目标对象为：" + point.getTarget());
        log.info(point.toString());
    }

    @After("@annotation(com.test.tmhkc.anno.TestAnno)")
    public void testAfter(){
        log.info("后置方法!");
    }

    @Around("@annotation(com.test.tmhkc.anno.TestAnno)")
    public Object testAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("环绕方法1!");
        Object result = proceedingJoinPoint.proceed();
        log.info("环绕方法2!");
        return result;
    }

}
