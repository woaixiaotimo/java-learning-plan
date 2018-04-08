package com.servle.Filter;


import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    private String characterEncoding;
    private boolean enableb;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        characterEncoding = filterConfig.getInitParameter("CharacterEncoding");
        enableb = "true".equalsIgnoreCase(filterConfig.getInitParameter("CharacterEncoding").trim());
        System.out.println("字符过滤");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (enableb && characterEncoding != null) {
            servletRequest.setCharacterEncoding(characterEncoding);
            servletResponse.setCharacterEncoding(characterEncoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("字符结束");
    }
}
