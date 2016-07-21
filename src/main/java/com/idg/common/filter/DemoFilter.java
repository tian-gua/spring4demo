package com.idg.common.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yehao on 16/7/21.
 */
public class DemoFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("请求了:" + httpServletRequest.getRequestURI());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
