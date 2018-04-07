package com.servle;

import com.util.ClasspathPackageScanner;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 啊Q on 2018/4/7.
 */
public class GlobalController {

    private Map<String, Object> controllerServices = new HashMap<String, Object>();
    private Map<String, Method> controllerMethods = new HashMap<String, Method>();

    private static GlobalController instance = null;

    public static GlobalController getInstance() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IOException {
        if (instance == null) {
            synchronized (GlobalController.class) {
                if (instance == null) {
                    instance = new GlobalController();
                }
            }
        }
        return instance;
    }

    private GlobalController() throws IOException, ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        String classpath = "com.controller";
        ClasspathPackageScanner scanner = new ClasspathPackageScanner(classpath);
        List<String> classPaths = scanner.getFullyQualifiedClassNameList();
        for (String className : classPaths) {
            Class aClass = Class.forName(className);
            if (aClass.getAnnotation(Service.class) != null) {
                Object classInstance = aClass.newInstance();
                Method[] methods = aClass.getMethods();
                for (int i = 0; i < methods.length; i++) {
                    ControllerUrl controllerUrl = methods[i].getAnnotation(ControllerUrl.class);
                    if (controllerUrl != null) {
                        this.controllerMethods.put(controllerUrl.value(), methods[i]);
                        this.controllerServices.put(controllerUrl.value(), classInstance);
                    }
                }
            }
        }
    }

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException,
            IllegalAccessException {
        String servletPath = req.getServletPath();
        String controllerUrl = servletPath.split(".do")[0];
        Method method = controllerMethods.get(controllerUrl);
        Object controller = controllerServices.get(controllerUrl);
        method.invoke(controller,req,resp);
    }
}
