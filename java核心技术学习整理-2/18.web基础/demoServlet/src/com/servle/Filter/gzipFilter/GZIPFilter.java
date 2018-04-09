package com.servle.Filter.gzipFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZIPFilter implements Filter {
    public void destroy() {
        System.out.println("GZIP结束");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String acceptEncoding = request.getHeader("Accept-Encoding");
        if (acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1) {
            GZipResponseWrapper gzipRes =new GZipResponseWrapper(response);
            chain.doFilter(req, gzipRes);

            gzipRes.finishResponse();
        }else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("GZIP开始");
    }

}
