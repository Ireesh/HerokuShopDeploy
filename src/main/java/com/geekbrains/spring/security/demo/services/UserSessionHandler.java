package com.geekbrains.spring.security.demo.services;

import com.geekbrains.spring.security.demo.entities.UserSessionPathLog;
import com.geekbrains.spring.security.demo.repositories.UserSessionPathLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionHandler {
    private String usetPath = null;
    private UserSessionPathLog userSessionPathLog;

    @Autowired
    UserSessionPathLogRepository userSessionPathLogRepository;

    @Transactional
    public void savePath(UserSessionPathLog userSessionPathLog) {
        userSessionPathLogRepository.save(userSessionPathLog);
    }

    public List<UserSessionPathLog> showAllHistory() {
        return userSessionPathLogRepository.findAll();
    }

    public void makeSign(Principal principal, HttpServletRequest request) {
        if (principal != null) {
            userSessionPathLog = new UserSessionPathLog();
            userSessionPathLog.setName(principal.getName());
            userSessionPathLog.setDate(Calendar.getInstance().getTime().toString());
            userSessionPathLog.setPath(request.getScheme() + request.getServletPath());
            savePath(userSessionPathLog);
        }
    }
}
