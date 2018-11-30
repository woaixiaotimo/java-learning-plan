

# RocketMQ概述

RocketMQ 是一款分布式、队列模型的消息中间件，具有以下特点： 能够保证严格的消息顺序 提供丰富的消息拉取模式 高效的订阅者水平扩展能力 实时的消息订阅机制 亿级消息堆积能力

支持功能

- 点对点
- 发布订阅
- 分布式
- 支持消息堆积
- 重试机制
- 持久化
- 顺序消息
- 事务消息(分布式事务)
- 拉取机制


# RocketMQ包含的组件

NameServer：单点，供Producer和Consumer获取Broker地址  
Producer：产生并发送消息   
Consumer：接受并消费消息  
Broker：消息暂存，消息转发  
![](https://raw.githubusercontent.com/woaixiaotimo/img/master/123123123.png)


## Name Server
    Name Server是RocketMQ的寻址服务。用于把Broker的路由信息做聚合。客户端依靠Name Server决定去获取对应topic的路由信息，从而决定对哪些Broker做连接。  

    Name Server是一个几乎无状态的结点，Name Server之间采取share-nothing的设计，互不通信。
    对于一个Name Server集群列表，客户端连接Name Server的时候，只会选择随机连接一个结点，以做到负载均衡。  

    Name Server所有状态都从Broker上报而来，本身不存储任何状态，所有数据均在内存。
    如果中途所有Name Server全都挂了，影响到路由信息的更新，不会影响和Broker的通信。

## Broker
    Broker是处理消息存储，转发等处理的服务器。  

    Broker以group分开，每个group只允许一个master，若干个slave。  
    只有master才能进行写入操作，slave不允许。  

    slave从master中同步数据。同步策略取决于master的配置，可以采用同步双写，异步复制两种。 

    客户端消费可以从master和slave消费。在默认情况下，消费者都从master消费，在master挂后，客户端由于从Name Server中感知到Broker挂机，就会从slave消费。  

    Broker向所有的NameServer结点建立长连接，注册Topic信息。

## RocketMQ优点
- 强调集群无单点，可扩展
- 任意一点高可用，水平可扩展
- 海量消息堆积能力，消息堆积后，写入低延迟。
- 支持上万个队列 
- 消息失败重试机制 
- 消息可查询 
- 开源社区活跃 
- 成熟度（经过双十一考验）

