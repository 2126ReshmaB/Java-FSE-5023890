package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
  @Before("execution(* com.example.service.BookService.*(..))")
  public void logBefore(JoinPoint joinPoint) {
      System.out.println("Before method: " + joinPoint.getSignature().getName());
  }

  @After("execution(* com.example.service.BookService.*(..))")
  public void logAfter(JoinPoint joinPoint) {
      System.out.println("After method: " + joinPoint.getSignature().getName());
  }
}
