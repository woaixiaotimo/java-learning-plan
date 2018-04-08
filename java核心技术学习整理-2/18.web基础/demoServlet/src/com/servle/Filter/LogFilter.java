package com.servle.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogFilter implements Filter {

    private Log log = new Log();
    private String filterName;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterName = filterConfig.getFilterName();
        log.info("启动 Filter：" + filterName);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        long startTime = System.currentTimeMillis();//启动时间
        String requestURI = request.getRequestURI();//访问的uri
        requestURI = request.getQueryString() == null ? requestURI : (requestURI + "?" + request.getQueryString());//所有的地址栏参数
        filterChain.doFilter(request, response);// 继续执行filter 或者Servlet
        long endTime = System.currentTimeMillis();//执行后的时间
        log.info(request.getRemoteAddr() + " 访问了 " + request.getRequestURL() + " 总用时：" + (endTime - startTime) + " 毫秒");//总用时
    }

    @Override
    public void destroy() {
        log.info("关闭Filter：" + filterName);
    }

    //暂时不引用真正的日志组件
    private class Log {
        public void info(String logStr) {
            System.out.println(logStr);
        }
    }
}
