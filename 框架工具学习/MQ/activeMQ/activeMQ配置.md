
# mq内嵌web服务器配置

## 修改web服务器端口

路径
>apache-activemq-5.15.8/conf/jetty.xml 

![](https://raw.githubusercontent.com/woaixiaotimo/img/master/20181203142611.png)

## 修改用户名密码

路径
>apache-activemq-5.15.8/conf/jetty-realm.properties



# mq配置

## 修改mq默认端口

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