package com.omcube.authserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("loginFailHandler")
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String message = exception.getLocalizedMessage();

        Map<String,Object> resultMap =new HashMap<>();

        resultMap.put("code","fail");
        resultMap.put("msg",message);

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(resultMap));
    }
}
