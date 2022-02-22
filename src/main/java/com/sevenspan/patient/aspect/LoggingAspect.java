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

    @Before("com.sevenspan.patient.aspect.PointCut.controllerPointCut() " +
            "|| com.sevenspan.patient.aspect.PointCut.servicePointCut() " +
            "|| com.sevenspan.patient.aspect.PointCut.schedulerPointCut()")
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

    @After("com.sevenspan.patient.aspect.PointCut.controllerPointCut() " +
            "|| com.sevenspan.patient.aspect.PointCut.servicePointCut() " +
            "|| com.sevenspan.patient.aspect.PointCut.schedulerPointCut()")
    public void afterMethod(JoinPoint joinPoint) {

        //Displaying class name and method name
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName() + " -- END");

    }

    @Around("com.sevenspan.patient.aspect.PointCut.controllerPointCut()")
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

    @AfterReturning(pointcut = "com.sevenspan.patient.aspect.PointCut.controllerPointCut()", returning = "object")
    public void afterReturningMethod(Object object) {
        log.info(object);
    }

    @AfterThrowing(pointcut = "com.sevenspan.patient.aspect.PointCut.controllerPointCut()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName() + " -- EXCEPTION");
        log.error(ex.getMessage());
    }
}
