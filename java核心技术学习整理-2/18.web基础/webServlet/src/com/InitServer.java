package com;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * Created by 啊Q on 2018/4/2.
 */
public class InitServer extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //转发
//        req.getRequestDispatcher("/forword.jsp").forward(req, resp);
        //重定向
//        resp.sendRedirect("/web/redirect.jsp");
        String path = req.getContextPath();
        System.out.println("path = " + path);
        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);

        //
        Enumeration header_names = req.getHeaderNames();
        while (header_names.hasMoreElements()) {
            String headerName = (String) header_names.nextElement();
            String header = req.getHeader(headerName);
            System.out.println(headerName + " = " + header);
        }
        String Authorization = req.getHeader("authorization");
        System.out.println("Authorization = " + Authorization);

//        InputStream inputStream = req.getInputStream();
//        int r = 0;
//        String str = "";
//        byte[] b = new byte[32];
//
//        while ((r = inputStream.read(b)) != -1) {
//            str += new String(b, 0, r);
//        }
//        System.out.println("str = " + str);

        BufferedReader br = req.getReader();
        String str2, wholeStr = "";
        while((str2 = br.readLine()) != null){
            wholeStr += str2;
        }
        System.out.println(wholeStr);

    }


    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
