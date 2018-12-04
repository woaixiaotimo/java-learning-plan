


# ActiveMQ的特性

## ActiveMq 的特性　

多种语言和协议编写客户端。语言: Java, C, C++, C#, Ruby, Perl, Python, PHP。  
应用协议: OpenWire,Stomp REST,WS Notification,XMPP,AMQP
完全支持JMS1.1和J2EE 1.4规范 (持久化,XA消息,事务)

对Spring的支持,ActiveMQ可以很容易内嵌到使用Spring的系统里面去,而且也支持Spring2.0的特性

通过了常见J2EE服务器(如 Geronimo,JBoss 4, GlassFish,WebLogic)的测试,其中通过JCA 1.5 resource adaptors的配置,可以让ActiveMQ可以自动的部署到任何兼容J2EE 1.4 商业服务器上

支持多种传送协议:in-VM,TCP,SSL,NIO,UDP,JGroups,JXTA

支持通过JDBC和journal提供高速的消息持久化

从设计上保证了高性能的集群,客户端-服务器,点对点

支持Ajax

支持与Axis的整合

可以很容易得调用内嵌JMS provider,进行测试

不支持顺序消费

## 什么情况下使用ActiveMQ?
1. 多个项目之间集成   
    (1) 跨平台   
    (2) 多语言   
    (3) 多项目  
2. 降低系统间模块的耦合度，解耦   
    (1) 软件扩展性  

3. 系统前后端隔离   
    (1) 前后端隔离，屏蔽高安全区     




# 下载安装ActiveMQ
 

ActiveMQ官网下载地址：http://activemq.apache.org/download.html

![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20161227103246728.png)


![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20161227103523476.png)

从它的目录来说，还是很简单的： 

- bin存放的是脚本文件
- conf存放的是基本配置文件
- data存放的是日志文件
- docs存放的是说明文档
- examples存放的是简单的实例
- lib存放的是activemq所需jar包
- webapps用于存放项目的目录

# 启动ActiveMQ　

进入到ActiveMQ 安装目录的Bin 目录，linux 下输入 ./activemq start 启动activeMQ 服务。

输入命令之后，会提示我们创建了一个进程IP 号，这时候说明服务已经成功启动了。

![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20161227103654976.png)

　ActiveMQ默认启动时，启动了内置的jetty服务器，提供一个用于监控ActiveMQ的admin应用。 
>http://127.0.0.1:8161

 

我们在浏览器打开链接之后输入账号密码（这里和tomcat 服务器类似）

默认账号：admin

密码：admin

![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20161227104137367.png)

到这里为止，ActiveMQ 服务端就启动完毕了。

ActiveMQ 在linux 下的终止命令是 ./activemq stop



# 常用命令

- start : 后台启动
- stop ： 停止
- restart ：重启
- console ：前台启动并打印日志
- status : 显示当前的ActiveMQ是否运行正常，并且能显示pid。
- list : 列出当前Broker名字。
- bstat : 显示当前Broker的统计信息。
- query : 根据筛选条件显示Broker的统计信息。  
    如：activemq query -QQueue=string_queue，是只显示string_queue这个队列的统计信息。更多使用方法可以使用activemq query --help显示帮助。

- browse : 可以查询当前Broker为被消费的消息，会显示消息的详细信息，如消息头，消息内容，优先级等。类似于数据库的查询功能。有自己的一套查询语法但是不是很复杂，同样可以使用activemq browse --help显示帮助。需要注意的是，只有Queue才可以查询，Topic是不可以的，所以这个功能虽然强大，但是有点鸡肋。

- dstat : 查询队列的关键数值。  
如队列大小，生产者消费者数量，消息出队入队统计等。还可以支持通过类别查询，如只查询队列或者只查询主题。
![](https://raw.githubusercontent.com/woaixiaotimo/img/master/170315095033891.png)



个人认为，activemq status和activemq dstat是比较常用的两个监控命令，可以使用shell，然后grep/awk解析。命令行虽然方便，但是明显缺失必要的信息，如硬件使用百分比，订阅者下线信息等。至少目前不能完成监控的全部工作，希望以后可以持续完善功能。

# 配置

## mq内嵌web服务器配置

### 修改web服务器端口

路径
>apache-activemq-5.15.8/conf/jetty.xml 

![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20181203142611.png)

## 修改用户名密码

路径
>apache-activemq-5.15.8/conf/jetty-realm.properties



## mq配置

### 修改mq默认端口

路径
>apache-activemq-5.15.8/conf/activemq.xml 

```
<transportConnectors>
    <!-- DOS protection, limit concurrent connections to 1000 and frame size to 100MB -->
    <transportConnector name="openwire" uri="tcp://0.0.0.0:61616?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="amqp" uri="amqp://0.0.0.0:5672?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="stomp" uri="stomp://0.0.0.0:61613?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="mqtt" uri="mqtt://0.0.0.0:1883?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
    <transportConnector name="ws" uri="ws://0.0.0.0:61614?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
</transportConnectors>

```

## 安全验证机制

路径
>apache-activemq-5.15.8/conf/activemq.xml 

```

```

 ## 持久化/存储方式

 ### 1.KahaDB

 ActiveMQ 5.3 版本起的默认存储方式。KahaDB存储是一个基于文件的快速存储消息，设计目标是易于使用且尽可能快。它使用基于文件的消息数据库意味着没有第三方数据库的先决条件。

要启用 KahaDB 存储，需要在 activemq.xml 中进行以下配置：

```
<broker brokerName="broker" persistent="true" useShutdownHook="false">
    <persistenceAdapter>
        <kahaDB directory="${activemq.data}/kahadb" journalMaxFileLength="16mb"/>
    </persistenceAdapter>
</broker>

```

### 2.AMQ

与 KahaDB 存储一样，AMQ存储使用户能够快速启动和运行，因为它不依赖于第三方数据库。AMQ 消息存储库是可靠持久性和高性能索引的事务日志组合，当消息吞吐量是应用程序的主要需求时，该存储是最佳选择。但因为它为每个索引使用两个分开的文件，并且每个 Destination 都有一个索引，所以当你打算在代理中使用数千个队列的时候，不应该使用它。

```
<persistenceAdapter>
    <amqPersistenceAdapter
        directory="${activemq.data}/kahadb"
        syncOnWrite="true"
        indexPageSize="16kb"
        indexMaxBinSize="100"
        maxFileLength="10mb" />
</persistenceAdapter>
```

 ### 3.JDBC

 选择关系型数据库，通常的原因是企业已经具备了管理关系型数据的专长，但是它在性能上绝对不优于上述消息存储实现。事实是，许多企业使用关系数据库作为存储，是因为他们更愿意充分利用这些数据库资源。

 ```
<beans>
    <broker brokerName="test-broker" persistent="true" xmlns="http://activemq.apache.org/schema/core">
            <persistenceAdapter>
                    <jdbcPersistenceAdapter dataSource="#mysql-ds"/>
            </persistenceAdapter>
    </broker>
    <bean id="mysql-ds" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost/activemq?relaxAutoCommit=true"/>
        <property name="username" value="activemq"/>
        <property name="password" value="activemq"/>
        <property name="maxActive" value="200"/>
        <property name="poolPreparedStatements" value="true"/>
    </bean>
</beans>
 ```

 ### 4.内存存储

 内存消息存储器将所有持久消息保存在内存中。在仅存储有限数量 Message 的情况下，内存消息存储会很有用，因为 Message 通常会被快速消耗。在 activema.xml 中将 broker 元素上的 persistent 属性设置为 false 即可。

 ```
 <broker brokerName="test-broker" persistent="false" xmlns="http://activemq.apache.org/schema/core">
    <transportConnectors>
        <transportConnector uri="tcp://localhost:61635"/>
    </transportConnectors>
</broker>
```

 # ActiveMQ 的部署模式

 ## 1.单例模式

这个就不啰嗦了，略过。


## 2.无共享主从模式

这是最简单的 Provider 高可用性的方案，主从节点分别存储 Message。从节点需要配置为连接到主节点，并且需要特殊配置其状态。

所有消息命令（消息，确认，订阅，事务等）都从主节点复制到从节点，这种复制发生在主节点对其接收的任何命令生效之前。并且，当主节点收到持久消息，会等待从节点完成消息的处理（通常是持久化到存储），然后再自己完成消息的处理（如持久化到存储）后，再返回对 Producer 的回执。

从节点不启动任何传输，也不能接受任何客户端或网络连接，除非主节点失效。当主节点失效后，从节点自动成为主节点，并且开启传输并接受连接。这是，使用 failover 传输的客户端就会连接到该新主节点。

Broker 连接配置如下：
```
failover://(tcp://masterhost:61616,tcp://slavehost:61616)?randomize=false
```

但是，这种部署模式有一些限制，

- 主节点只会在从节点连接到主节点时复制其活动状态，因此当从节点没有连接上主节点之前，任何主节点处理的 Message 或者消息确认都会在主节点失效后丢失。不过你可以通过在主节点设置 waitForSlave 来避免，这样就强制主节点在没有任何一个从节点连接上的情况下接受连接。

- 就是主节点只能有一个从节点，并且从节点不允许再有其他从节点。

- 把正在运行的单例配置成无共享主从，或者配置新的从节点时，你都要停止当前服务，修改配置后再重启才能生效。


    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/897247-20170209161210916-723854934.png)


在可以接受一些故障停机时间的情况下，可以使用该模式。

从节点配置：
```
<services>
    <masterConnector remoteURI="tcp://remotehost:62001" userName="Rob" password="Davies"/>
</services>
```

此外，可以配置 shutdownOnMasterFailure 项，表示主节点失效后安全关闭，保证没有消息丢失，允许管理员维护一个新的从节点。

## 3.共享存储主从模式

允许多个代理共享存储，但任意时刻只有一个是活动的。这种情况下，当主节点失效时，无需人工干预来维护应用的完整性。另外一个好处就是没有从节点数的限制。

有两种细分模式：

1. 基于数据库

    它会获取一个表上的排它锁，以确保没有其他 ActiveMQ 代理可以同时访问数据库。其他未获得锁的代理则处于轮询状态，就会被当做是从节点，不会开启传输也不会接受连接。


    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/897247-20170209161329666-682001780.png)
2. 基于文件系统

    需要获取分布式共享文件锁，linux 系统下推荐用 GFS2。

    ![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20170209161340307.png)