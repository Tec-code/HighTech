package com.ht.common.auth;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TokenFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String REQUEST_AUTH_KEY = "user";

    private List<String> excludeUrls;
    private TokenProvider tokenProvider;

    public void setExcludeUrls(String excludeUrls) {
        this.excludeUrls = Arrays.asList(excludeUrls.split(","));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 无需鉴权的接口直接通过
        String path = request.getServletPath();
        if (excludeUrls.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 请求头没有token的直接返回401
        String token = request.getHeader(AUTH_HEADER);
        if (null == token) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 校验token有效性
        // Filter中无法使用Autowire注入，在第一次调用时初始化tokenProvider
        if (null == tokenProvider) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            tokenProvider = webApplicationContext.getBean(TokenProvider.class);
        }
        String validUser = tokenProvider.validateLogin(token);
        if (null == validUser) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 鉴权通过，用户信息写入请求属性
        request.setAttribute(REQUEST_AUTH_KEY, validUser);
        filterChain.doFilter(request, response);
    }
}
