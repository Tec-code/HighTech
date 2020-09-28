package com.ht.common.auth;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TokenFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private static final String SESSION_AUTH_KEY = "user";

    private List<String> excludeUrls;
    private TokenProvider tokenProvider;

    public void setExcludeUrls(String excludeUrls) {
        this.excludeUrls = Arrays.asList(excludeUrls.split(","));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (excludeUrls.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(AUTH_HEADER);
        if (null == token) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
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
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_AUTH_KEY, validUser);
        filterChain.doFilter(request, response);
    }
}
