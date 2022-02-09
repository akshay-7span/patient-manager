package com.sevenspan.patient.aspect;

import com.sevenspan.patient.dto.responsedto.SuccessMessage;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    @Before("com.sevenspan.patient.aspect.PointCut.controllerPointCut() || com.sevenspan.patient.aspect.PointCut.servicePointCut()")
    public void beforeMethod(JoinPoint joinPoint){

        //Displaying class name and method name
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName()+" -- START");

        //Displaying Method Argument
        Object[] objects= joinPoint.getArgs();
        log.info("Method Arguments: ");
        for (Object ob:objects) {
            log.info(ob.toString());
        }
    }

    @After("com.sevenspan.patient.aspect.PointCut.controllerPointCut() || com.sevenspan.patient.aspect.PointCut.servicePointCut()")
    public void afterMethod(JoinPoint joinPoint){

        //Displaying class name and method name
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName()+" -- END");

   }

    @Around("com.sevenspan.patient.aspect.PointCut.controllerPointCut()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //Displaying class name and method name
        //Before Method Execution
        log.info(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + proceedingJoinPoint.getSignature().getName()+" -- START");

        //Displaying Method Argument
        Object[] objects= proceedingJoinPoint.getArgs();
        log.info("Method Arguments: ");
        for (Object ob:objects) {
            log.info(ob.toString());
        }

        //Displaying class name and method name
        //Execution of Method
        //Get response in object
        Object object = proceedingJoinPoint.proceed();

        //After Method Execution
        log.info(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + proceedingJoinPoint.getSignature().getName()+" -- END");

        //Return response of method
        return object;
    }

    @AfterReturning(pointcut = "com.sevenspan.patient.aspect.PointCut.controllerPointCut()", returning="object")
    public void afterReturningMethod(Object object) throws Throwable
    {
        SuccessMessage successMessage=(SuccessMessage)object;
        log.info(successMessage.getMessage());
        log.info(successMessage.getStatus());
        log.info(successMessage.getData());
    }

    @AfterThrowing(pointcut = "com.sevenspan.patient.aspect.PointCut.controllerPointCut()",throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex)
    {
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() + " @" + joinPoint.getSignature().getName()+" -- EXCEPTION");
        log.error(ex.getMessage());
    }

}
