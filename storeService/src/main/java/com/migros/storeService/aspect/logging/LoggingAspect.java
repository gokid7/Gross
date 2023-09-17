package com.migros.storeService.aspect.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.migros.storeService.service.*.*(..))")
    public void beforeMethodExecution() {
        System.out.println("Before method execution: Logging...");
    }

    @After("execution(* com.migros.storeService.service.*.*(..))")
    public void afterMethodExecution() {
        System.out.println("After method execution: Logging...");
    }

    @AfterReturning(pointcut = "execution(* com.migros.storeService.service.*.*(..))", returning = "result")
    public void afterReturningMethod(Object result) {
        System.out.println("Method returned: " + result);
    }
}
