package com.itheima.springsecurity.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //获取用户身份信息
        String localizedMessage = exception.getLocalizedMessage();
/*        // 获取用户的凭证信息
        Object credentials = authentication.getCredentials();
        // 获取用户的权限信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();*/

        //创建结果对象
        HashMap result = new HashMap();
        result.put("code", -1);
        result.put("message", "登录失败");
        result.put("data", localizedMessage);

        //转换成json字符串
        String json = JSON.toJSONString(result);

        //返回响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
