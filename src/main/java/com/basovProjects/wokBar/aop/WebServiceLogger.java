package com.basovProjects.wokBar.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Pointcut("execution(* com.basovProjects.wokBar.controller..*(..))" +
            "execution(* com.basovProjects.wokBar.service.impl.UserServiceImpl.*(..))"+
            "execution(* com.basovProjects.wokBar.service.impl.OrderServiceImpl.*(..))")
    public void controllerAndServicesMethodsExecution() {
    }

    @Around("controllerAndServicesMethodsExecution()")
    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();
        log.debug(thisJoinPoint.getTarget().getClass().getName() +  " class | " + methodName + " method entered | with args: " + Arrays.toString(methodArgs));
        try {
            Object result = thisJoinPoint.proceed();
            log.debug(thisJoinPoint.getTarget().getClass().getName() +  " class | " + methodName + " method entered | returns " + result);
            return result;
        } catch (Throwable ex) {
            log.error("Error in " + thisJoinPoint.getTarget().getClass().getName() +  " class | " + methodName + " method | with message: " + ex.getMessage());
            return new Object();
        }
    }
}
