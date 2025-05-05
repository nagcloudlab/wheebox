package com.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityCheck {

    @Before("execution(void transfer(..))")
    public void hasPersmission() {
        // Security check logic
        System.out.println("SecurityCheck: hasPermission() called");
    }

}
