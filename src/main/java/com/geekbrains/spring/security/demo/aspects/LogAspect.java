package com.geekbrains.spring.security.demo.aspects;

import com.geekbrains.spring.security.demo.services.UserSessionHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private UserSessionHandler userSessionHandler;

    public LogAspect(UserSessionHandler userSessionHandler) {
        this.userSessionHandler = userSessionHandler;
    }

    @Pointcut("@annotation(LogMethod)")
    private void annotatedMethod() {
    }

    @After("annotatedMethod()")
    public void logAfter(JoinPoint jointPoint) throws Throwable {
        Object[] values = jointPoint.getArgs();
        if (values != null || values.length != 0) {
            Principal principal = null;
            HttpServletRequest request = null;
            for (Object value : values) {
                if (value != null) {
                    if (value.getClass().getSimpleName().equals("UsernamePasswordAuthenticationToken")) {
                        principal = (Principal) value;
                    }
                    if (value.getClass().getSimpleName().equals("Servlet3SecurityContextHolderAwareRequestWrapper")) {
                        request = (HttpServletRequest) value;
                    }
                }
            }
            if (principal != null && request != null) {
                userSessionHandler.makeSign(principal, request);
            }
        }
    }

}
