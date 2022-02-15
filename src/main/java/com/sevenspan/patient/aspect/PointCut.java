package com.sevenspan.patient.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCut {

    @Pointcut("execution(* com.sevenspan.patient.controller..*.*(..))")
    public void controllerPointCut(){}

    @Pointcut("execution(* com.sevenspan.patient.service..*.*(..))")
    public void servicePointCut(){}

    @Pointcut("execution(* com.sevenspan.patient.scheduler..*.*(..))")
    public void schedulerPointCut(){}
}
