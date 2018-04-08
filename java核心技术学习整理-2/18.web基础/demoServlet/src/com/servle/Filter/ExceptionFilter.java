package com.servle.Filter;

import javax.servlet.*;
import java.io.IOException;

public class ExceptionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("异常捕捉");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            Throwable rootCause = e;
            while (rootCause.getCause() != null) {//找到根异常
                rootCause = rootCause.getCause();
            }
            //多级判断找到特定类型异常进行处理
            if (rootCause instanceof Exception) {
                //找到异常类型进行处理
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("异常结束");
    }
}
