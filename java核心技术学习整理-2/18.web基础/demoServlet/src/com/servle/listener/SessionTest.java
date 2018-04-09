package com.servle.listener;

import javax.servlet.http.*;
import java.io.Serializable;
import java.util.Date;

//因为会被串行化 ，所以实现Serializable接口
public class SessionTest implements HttpSessionBindingListener, HttpSessionActivationListener, Serializable {

    private Log log = new Log();
    private String name;
    private Date dateCreated;

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        //放进Session前被调用
        HttpSession session = event.getSession();
        String name = event.getName();
        log.info(this + "被绑定到session\"" + session.getId() + "\"的" + name + "的属性上");

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        //从Session移除后调用
        HttpSession session = event.getSession();
        String name = event.getName();
        log.info(this + "被从session\"" + session.getId() + "\"的" + name + "的属性上移除");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent event) {
        //从硬盘恢复后调用
        HttpSession session = event.getSession();
        log.info(this + "已成功从硬盘加载 SessionId" + session.getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent event) {
        //从硬盘恢复后调用
        HttpSession session = event.getSession();
        log.info(this + "即将保存到硬盘 SessionId" + session.getId());
    }

    private class Log {
        void info(String s) {
            System.out.println(s);
        }
    }
}
