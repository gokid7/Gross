package com.migros.registerService.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAspect {

    @Around("execution(* *(..)) && @annotation(ExecutionTime)")
    public Object evaluateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        try{
            return joinPoint.proceed();
        } finally {
            log.info(joinPoint.getSignature().getName() + " request executed in " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
