package com.sevenspan.patient.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    //Pointcuts
    @Pointcut("execution(* com.sevenspan.patient.controller..*.*(..))")
    public void controllerPointCut(){}

    @Pointcut("execution(* com.sevenspan.patient.service..*.*(..))")
    public void servicePointCut(){}

    @Pointcut("execution(* com.sevenspan.patient.scheduler..*.*(..))")
    public void schedulerPointCut(){}

    //Aspects
    @Before("controllerPointCut() || servicePointCut() || schedulerPointCut()")
    public void beforeMethod(JoinPoint joinPoint) {

        //Displaying class name and method name
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName() + " -- START");

        //Displaying Method Argument
        Object[] objects = joinPoint.getArgs();
        log.info("Method Arguments: ");
        for (Object ob : objects) {
            log.info(ob.toString());
        }
    }

    @After("controllerPointCut() || servicePointCut() || schedulerPointCut()")
    public void afterMethod(JoinPoint joinPoint) {

        //Displaying class name and method name
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName() + " -- END");

    }

    @Around("controllerPointCut()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //Displaying Method Argument
        Object[] objects = proceedingJoinPoint.getArgs();
        log.info("Method Arguments: ");
        for (Object ob : objects) {
            log.info(ob.toString());
        }

        //Get response in object
        //Return response of method
        return proceedingJoinPoint.proceed();
    }

    @AfterReturning(pointcut = "controllerPointCut()", returning = "object")
    public void afterReturningMethod(Object object) {
        log.info(object);
    }

    @AfterThrowing(pointcut = "controllerPointCut()",throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName() + " -- EXCEPTION");
        log.error(ex.getMessage());
    }
}
