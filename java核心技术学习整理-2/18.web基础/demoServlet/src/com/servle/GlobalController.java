package com.servle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class GlobalController {

    public static void execute(HttpServletRequest request, HttpServletResponse response) {

        //获取请求的url（不包含域名和参数，但包含域名后的斜杠）
        String servletPath = request.getServletPath();
        String classPath = "";
        String methodName = "";
        execute(request, response, classPath, methodName);
    }

    private static void execute(HttpServletRequest request, HttpServletResponse response,
                                String classPath, String methodName) {
        try {
            //根据类路径获取对应的类的class
            Class clasz = Class.forName(classPath);
            //创建实例
            Object controllers = clasz.newInstance();

            //获取所有方法
            Method[] methods = clasz.getDeclaredMethods();

            //获得方法
            Method method = clasz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //调用方法
            method.invoke(controllers, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
