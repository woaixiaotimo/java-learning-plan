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

## 什么情况下使用ActiveMQ?
1. 多个项目之间集成   
    (1) 跨平台   
    (2) 多语言   
    (3) 多项目  
2. 降低系统间模块的耦合度，解耦   
    (1) 软件扩展性  

3. 系统前后端隔离   
    (1) 前后端隔离，屏蔽高安全区      


1. 不同语言应用集成   
ActiveMQ 中间件用Java语言编写，因此自然提供Java客户端 API。但是ActiveMQ 也为C/C++、.NET、Perl、PHP、Python、Ruby 和一些其它语言提供客户端。在你考虑如何集成不同平台不同语言编写应用的时候，ActiveMQ 拥有巨大优势。在这样的例子中，多种客户端API通过ActiveMQ 发送和接受消息成为可能，无论使用的是什么语言。此外，ActiveMQ 还提供交叉语言功能，该功能整合这种功能，无需使用远程过程调用（RPC）确实是个优势，因为消息协助应用解耦。 

2. 作为RPC的替代     
使用RPC同步调用的应用十分普遍。假设大多数客户端服务器应用使用RPC，包括ATM、大多数WEB应用、信用卡系统、销售点系统等等。尽管很多系统很成功，但是转换使用异步消息可以带来很多好处，而且也不会放弃响应保证。使用同步请求的系统在规模上有较大的限制，因为请求会被阻塞，从而导致整个系统变慢。如果使用异步消息替代，可以很容易增加额外的消息接收者，使得消息能被并发消耗，从而加快请求处理。当然，你的系统应用间应该是解耦的。 

3. 应用之间解耦   
正如之前讨论的，紧耦合架构可以导致很多问题，尤其是如果他们是分布的。松耦合架构，在另一方面，证实了更少的依赖性，能够更好地处理不可预见的改变。不仅可以在系统中改变组件而不影响整个系统，而且组件交互也相当的简单。相比使用同步的系统（调用者必须等待被调用者返回信息），异步系统（调用方发送消息后就不管，即fire-and-forget）能够给我们带来事件驱动架构（event-driven architecture EDA）。 

4. 作为事件驱动架构的主干   
解耦，异步架构的系统允许通过代理器自己配置更多的客户端，内存等（即vertical scalability）来扩大系统，而不是增加更多的代理器（即horizontal scalability）。考虑如亚马逊这样繁忙的电子商务系统。当用户购买物品，事实上系统需要很多步骤去处理，包括下单，创建发票，付款，执行订单，运输等。但是用户下单后，会立即返回“谢谢你下单”的界面。不只是没有延迟，而且用户还会受到一封邮件表明订单已经收到。在亚马逊下单的例子就是一个多步处理的例子。每一步都由单独的服务去处理。当用户下单是，有一个同步的体积表单动作，但整个处理流程并不通过浏览器同步处理。相反地，订单马上被接受和反馈。而剩下的步骤就通过异步处理。如果在处理过程中出错，用户会通过邮件收到通知。这样的异步处理能提供高负载和高可用性。 

5. 提高系统扩展性   
很多使用事件驱动设计的系统是为了获得高可扩展性，例如电子商务，政府，制造业，线上游戏等。通过异步消息分开商业处理步骤给各个应用，能够带来很多可能性。考虑设计一个应用来完成一项特殊的任务。这就是面向服务的架构（service-oriented architecture SOA）。每一个服务完成一个功能并且只有一个功能。应用就通过服务组合起来，服务间使用异步消息和最终一致性。这样的设计便可以引入一个复杂事件处理概念（complex event processing CEP）。使用CEP，部件间的交互可以被记录追踪。在异步消息系统中，可以很容易在部件间增加一层处理。
