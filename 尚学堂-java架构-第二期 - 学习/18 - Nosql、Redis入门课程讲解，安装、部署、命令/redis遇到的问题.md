

# Redis无法远程链接/reids服务器的6379端口telnet不通

1. reids服务器的6379端口telnet不通
    ![avatar](img\2018071823260941.png)

2. 查看reids进程和端口，都是存在的。只是ip地址是127.0.0.1而不是0.0.0.0,只是
本机能使用
    >ps -ef|grep redis  

    ![avatar](img\20180718232713382.png)

3. 修改redis的配置文件redis.conf
    > 将 bind 127.0.0.1 修改为  
    bind 0.0.0.0  

    ![avatar](img\20180718233030316.png)

    ![avatar](img\20180718233121234.png)

4. 重启redis-server
    ![avatar](img\20180718233300324.png)

5. 如果还是连接不上可以修改主服务器的配置文件
    >bind 本机地址