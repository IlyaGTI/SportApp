package com.example.sportapp.aspect;

import com.example.sportapp.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
@Slf4j
public class Advice {

    @Pointcut("execution(* com.example.sportapp.service.*.*(..))")
    public void logPointCut(){
    }

    @Before("logPointCut()")
    public void beforeMethod(JoinPoint jp){
        log.info("Метод " + jp.getSignature() + " начал работу");
    }

    @After("logPointCut()")
    public void afterMethod(JoinPoint jp){
        log.info("Метод " + jp.getSignature() + " закончил работу");
    }

    @AfterThrowing(value = "execution(* com.example.sportapp.service.*.*(..))", throwing = "e")
    public void throwExc(JoinPoint jp, Exception e){
        if(jp.getSignature().equals(null)) {
            log.info(e.getMessage());
        }
    }
}
