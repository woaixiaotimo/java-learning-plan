[[toc]]

# 在 Linux 上安装 Tomcat 7，本篇提供两种常用方法：

1. 通过 apt-get 命令进行在线安装（会自动配置好环境变量和服务）
2. 通过下载并解压 .tar.gz 包进行手动安装（需要手动配置环境变量）




##  通过 apt-get 命令进行在线安装（会自动配置好环境变量和服务）

1. 更新软件包管理器
    >sudo apt-get update

2. 通过 apt-get 命令安装 Tomcat 7
    >sudo apt-get install tomcat7


3. 如何通过服务开启、关闭、重启 Tomcat 7

    - 启动 Tomcat 7
        >sudo service tomcat7 start  

    - 关闭 Tomcat 7
        >sudo service tomcat7 stop

    - 重启 Tomcat 7
        >sudo service tomcat7 start

    以下是Tomcat开启成功及关闭成功截图！ 

    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414213759167.jpg)

    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414213818090.jpg)

4. 使用 apt-get 命令 安装后的 tomcat 7的目录结构说明

    - /etc/tomcat7 　　：全局配置
    - /usr/share/tomcat7/ 　　 　：程序主目录
    - /usr/share/tomcat7/conf/Catalina/localhost/ 　　：本机部署的 Catalina 配置
    - /var/lib/tomcat7/ 　　：工作主目录
    - /var/lib/tomcat7/webapps 　　：应用文件实际存放于此
    - /var/lib/tomcat7/work 　　：动态工作目录（动态编译的 .jsp 存放于此）


5. 测试

    1.可以通过 curl（如果你的 Linux 没有安装 桌面，可以通过这个方式进行测试）
    >curl http://localhost:8080

    2.直接打开浏览器，输入地址：http://localhost:8080 或 http://127.0.0.1:8080

    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414213908809.jpg)




##  通过下载并解压,进行手动安装(需要手动配置环境变量)


1. 从官网下载tomcat  
    http://tomcat.apache.org/


2. 先安装 JDK  
    具体安装方法请参考：  
    [安装JDK](安装JDK.md)

3. 将 Tomcat 7 的 .tar.gz包 复制到 /opt/目录下  

    >sudo cp 'tomcat所在的路径' /opt

4. 解压 JDK 到当前目录
    >sudo tar -xvzf apache-tomcat-7.0.85.tar.gz 

5. 打开启动脚本（在 tomcat 目录下）
    >sudo vi ./bin/startup.sh

    在 启动脚本 文件中的最前面 加这段代码：

    ```
    JAVA_HOME=/opt/jdk1.7.0_79  
    JRE_HOME=/opt/jdk1.7.0_79/jre  
    PATH=$JAVA_HOME/bin:$JRE_HOME:$PATH  
    CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    TOMCAT_HOME=/opt/apache-tomcat-7.0.85 
    ```

    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414214145545.jpg)

    注： JAVA_HOME 和 JRE_HOME 是已经配置好 JDK 和 JRE 目录，TOMCAT_HOME 为当前 apache-tomcat-7.0.85 目录，保存退出。

6. 开启、关闭、重启 Tomcat（在 Tomcat 目录下）

    - 启动 Tomcat 7
        >sudo sh ./bin/startup.sh

    - 关闭 Tomcat 7
        >sudo sh ./bin/shutdown.sh

    - 重启 Tomcat 7
        >sudo sh ./bin/startup.sh

    下列截图表示 Tomcat 7 开启成功，你也可以将 Tomcat 添加到服务，这里不作过多讲解。 

    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414214217874.jpg)


7. 测试

    同另一种安装方式一样


# 常见问题

## 常见问题一

![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414214327281.jpg)

解决方法：可能没有添加 jdk 和 jre 环境变量。

## 常见问题二


![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20180414214403480.jpg)

解决方法：可能没有安装 JDK。