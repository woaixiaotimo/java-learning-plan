


# JMS的两种消息模式

消息列队有两种消息模式，一种是点对点的消息模式，还有一种就是订阅的模式.

## 点对点的消息模式

点对点的模式主要建立在一个队列上面，当连接一个列队的时候，发送端不需要知道接收端是否正在接收，可以直接向ActiveMQ发送消息。  

**消息，将会先进入队列中，如果有接收端在监听，则会发向接收端，如果没有接收端接收，则会保存在activemq服务器，直到接收端接收消息**，

**Queue支持存在多个消费者，但是对一个消息而言，只会有一个消费者可以消费。**


## 订阅模式


订阅/发布模式，同样可以有着多个发送端与多个接收端，但是**接收端与发送端存在时间上的依赖**，就是如果发送端发送消息的时候，接收端并没有监听消息，那么ActiveMQ将不会保存消息，将会认为消息已经发送，换一种说法，就是发送端发送消息的时候，接收端不在线，是接收不到消息的，哪怕以后监听消息，同样也是接收不到的。

**也就是说 接收/订阅端 要优先于 发布/发送端 启动**



和点对点方式不同，**发布到topic的消息会被所有订阅者消费。**




# 点对点的实现代码

项目使用MAVEN来构建 (版本自己选择即可)

```
<!-- https://mvnrepository.com/artifact/org.apache.activemq/activemq-all -->
<dependency>
        <groupId>org.apache.activemq</groupId>
        <artifactId>activemq-all</artifactId>
        <version>5.15.8</version>
</dependency>
```

## 生产者/发送端 实现代码

```
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//生产者
public class Producter {

    //连接账号
    private final String USER_NAME = "admin";
    //连接密码
    private final String PASSWORD = "admin";
    //连接地址
    private final String BROKER_URL = "tcp://192.168.10.150:61616";
    //connection的工厂
    private ConnectionFactory factory;
    //连接对象
    private Connection connection;
    //一个操作会话
    private Session session;
    //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
    private Destination destination;
    //生产者，就是产生数据的对象
    private MessageProducer producer;

    public static void main(String[] args) {
        Producter producter = new Producter();
        producter.start();
    }

    public void start() {
        try {
            //根据用户名，密码，url创建一个连接工厂
            factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
            //从工厂中获取一个连接
            connection = factory.createConnection();
            //测试过这个步骤不写也是可以的，但是网上的各个文档都写了
            connection.start();
            //创建一个session
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


            //===============================p2p模式中创建的是队列===================================
            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接了一个名为"text-msg"的队列，这个会话将会到这个队列，当然，如果这个队列不存在，将会被创建
            destination = session.createQueue("text-msg");
            //=======================================================================================


            //从session中，获取一个消息生产者
            producer = session.createProducer(destination);
            //设置生产者的模式，有两种可选
            //DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会被保存
            //DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //创建一条消息，当然，消息的类型有很多，如文字，字节，对象等,可以通过session.create..方法来创建出来

            for (int i = 0; i < 100; i++) {
                //发送一条消息
                TextMessage textMsg = session.createTextMessage("发送 = " + "呵呵+" + i + 1);
                producer.send(textMsg);
            }

            System.out.println("发送消息成功");
            //即便生产者的对象关闭了，程序还在运行哦
            producer.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```


## 消费者/接收端 实现代码


```

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

//消费者
public class Comsumer {
    //连接账号
    private final String userName = "";
    //连接密码
    private final String password = "";
    //连接地址
    private final String brokerURL = "tcp://192.168.10.150:61616";
    //connection的工厂
    private ConnectionFactory factory;
    //连接对象
    private Connection connection;
    //一个操作会话
    private Session session;
    //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
    private Destination destination;
    //消费者，就是接收数据的对象
    private MessageConsumer messageConsumer;

    public static void main(String[] args) {
        Comsumer comsumer = new Comsumer();
        comsumer.start();
    }

    public void start() {
        try {
            //根据用户名，密码，url创建一个连接工厂
            factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
            //从工厂中获取一个连接
            connection = factory.createConnection();
            //测试过这个步骤不写也是可以的，但是网上的各个文档都写了
            connection.start();
            //创建一个session
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


            //===============================p2p模式中创建的是队列===================================
            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接了一个名为"text-msg"的队列，这个会话将会到这个队列，当然，如果这个队列不存在，将会被创建
            destination = session.createQueue("text-msg");
            //=======================================================================================


            //根据session，创建一个接收者对象
            messageConsumer = session.createConsumer(destination);


            //实现一个消息的监听器
            //实现这个监听器后，以后只要有消息，就会通过这个监听器接收到
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        //获取到接收的数据
                        String text = ((TextMessage) message).getText();
                        System.out.println("接收 = " + text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });


            //关闭接收端，也不会终止程序哦
            //messageConsumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

```

# 订阅/发布模式的实现代码

注意：订阅端要先于发布端先启动，不然生产的消息会背舍弃

## 发布/发送端

```
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布
 */
public class Publish {

    //连接账号
    private final String USER_NAME = "admin";
    //连接密码
    private final String PASSWORD = "admin";
    //连接地址
    private final String BROKER_URL = "tcp://192.168.10.150:61616";
    //connection的工厂
    private ConnectionFactory factory;
    //连接对象
    private Connection connection;
    //一个操作会话
    private Session session;
    //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
    private Destination destination;
    //生产者，就是产生数据的对象
    private MessageProducer producer;

    public static void main(String[] args) {
        Publish publish = new Publish();
        publish.start();
    }

    public void start() {
        try {
            //根据用户名，密码，url创建一个连接工厂
            factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
            //从工厂中获取一个连接
            connection = factory.createConnection();
            //测试过这个步骤不写也是可以的，但是网上的各个文档都写了
            connection.start();
            //创建一个session
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接了一个名为"text-msg"的队列，这个会话将会到这个队列，当然，如果这个队列不存在，将会被创建

            //=======================================================
            //点对点与订阅模式唯一不同的地方，就是这一行代码，点对点创建的是Queue，而订阅模式创建的是Topic
            destination = session.createTopic("topic-text");
            //=======================================================

            //从session中，获取一个消息生产者
            producer = session.createProducer(destination);
            //设置生产者的模式，有两种可选
            //DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会被保存
            //DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //创建一条消息，当然，消息的类型有很多，如文字，字节，对象等,可以通过session.create..方法来创建出来

            long s = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                //发送一条消息
                TextMessage textMsg = session.createTextMessage("哈哈+" + (i + 1));
                producer.send(textMsg);
                System.out.println(textMsg.getText());
                session.commit();
            }
            long e = System.currentTimeMillis();
            System.out.println("发送消息成功");
            System.out.println(e - s);
            //即便生产者的对象关闭了，程序还在运行哦
            producer.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


```


## 订阅/接收端

```
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 订阅
 */
public class Subscribe {

    //连接账号
    private  final String USER_NAME = "admin";
    //连接密码
    private final  String PASSWORD = "admin";
    //连接地址
    private final String BROKER_URL = "tcp://192.168.10.150:61616";
    //connection的工厂
    private ConnectionFactory factory;
    //连接对象
    private Connection connection;
    //一个操作会话
    private Session session;
    //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
    private Destination destination;
    //生产者，就是产生数据的对象
    private MessageConsumer messageConsumer;

    public static void main(String[] args) {
        Subscribe send = new Subscribe();
        send.start();
    }

    public void start() {
        try {
            //根据用户名，密码，url创建一个连接工厂
            factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
            //从工厂中获取一个连接
            connection = factory.createConnection();
            //测试过这个步骤不写也是可以的，但是网上的各个文档都写了
            connection.start();
            //创建一个session
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            session = connection.createSession(true, Session.SESSION_TRANSACTED);

            //=======================================================
            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接了一个名为"text-msg"的队列，
            //这个会话将会到这个队列，当然，如果这个队列不存在，将会被创建
            //点对点与订阅模式唯一不同的地方，就是这一行代码，点对点创建的是Queue，而订阅模式创建的是Topic
            destination = session.createTopic("topic-text");
            //=======================================================

            //从session中，获取一个消息消费者
            messageConsumer = session.createConsumer(destination);


            //实现一个消息的监听器
            //实现这个监听器后，以后只要有消息，就会通过这个监听器接收到
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        //获取到接收的数据
                        String text = ((TextMessage) message).getText();
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

# ActiveMQ的应用

## 事务以及保证消息的成功处理(签收模式)

一旦从ConnectionFacatory中获得一个Connection，必须从Connection中创建一个Session。session是一个发送或者接收消息的对象，可以使用session创建MessageProducer(生产者)，MessageConsumer(消费者)和Message(消息)。
**Session可以被事务化，也可以不被事务化，通常可以通过向Connection的创建session的方法传递一个布尔参数对此进行设置**


Session createSession(boolean transacted, int acknowledgeMode)throws JMSException;  

transacted：是否开启事务  
acknowledgeMode:消息签收模式

### 事务

如果开始事务模式，那么消息签收参数将会被忽略。该参数会默认设置为Session.SESSION_TRANSACTED


在事务状态下进行发送操作，消息并未真正投递到中间件，而只有进行session.commit操作之后，消息才会发送到中间件，再转发到适当的消费者进行处理。如果是调用rollback操作，则表明，当前事务期间内所发送的消息都取消掉。此时无论commit或rollback，会重新打开一个事务。 


与此同时，在rollback之后，随着新的事务打开，一些持久化的消息会重新接收。原因在于当传送模式处于持久话的状态，产生的消息如若没有被及时签收确认，则消息会被中间件持久化。此时，当客户端重新连接或新的事务开启，消息会被再次发送到客户端。
为什么commit之后，不会有持久的消息重新传送呢?  

原因在于commit操作会自动将为签收确认的消息进行签收确认，如果是当前接收但未签收确认的消息，都会被确认处理。因而在commit之后不会有持久化的消息出现。

### 签收模式

**Session.AUTO_ACKNOWLEDGE** : 当客户端从receive或onMessage成功返回时，Session自动签收客户端的收条。(常用)

**CLIENT_ACKNOWLEDGE** : 客户端通过调用消息的acknowledge方法签收消息。在这种情况下，签收发生在session层面，签收一个已消费的消息，会自动签收这个session所有已消费的消息的收条。

**DUPS_OK_ACKNOWLEDGE** : 此选项不必确保对传送消息的签收。他可能引起消息的重复，但是降低了session的开销，所以只有当客户端能容忍重复消息时，才可使用。


## 消息以及有效期的管理

MessageProducer是一个由session创建的对象用来向Destination发送消息。

如果该对象在创建时指定为null( session.createProducer(destination))需要在发送消息时手动指定destination。

### 消息发送方法

void send(Message message) throws JMSException;  

void send(Destination destination, Message message) throws JMSException;  

void send(Message message, int deliveryMode, int priority, long timeToLive)throws JMSException;

void send(Destination destination,Message message,int deliveryMode,int priority,long timeToLive)throws JMSException;


**参数：**  
- deliveryMode : 是消息传送模式  
    DeliveryMode.PERSISTENT : 持久性消息(默认)-2  
    DeliveryMode.NON_PERSISTENT : 非持久性消息-1  
    如果能够容忍消息丢失，使用非持久性消息可以改善性能和减少储存的开销。   
- priority : 消息优先级  
    消息优先级分为0-9十个级别  
    0-4 : 普通消息  
    5-9 ：加急消息  
    如果不指定消息的优先级则**默认为4**。jms不严格按照这十个优先级发送消息，但必须保证加急消息要优于普通消息到达。  

- timeToLive ：消息的过期时间  
    默认情况下消息永远不会过期，如果消息在特定周期内失去意义，那么可以设置过期时间，时间单位为毫秒。

### 消息有效期

这样的场景也是有的，一条消息的有效时间，当发送一条消息的时候，可能希望这条消息在指定的时间被处理，如果超过了指定的时间，那么这条消息就失效了，就不需要进行处理了。那么我们除了使用上面的发送方法外，还可以使用ActiveMQ的设置有效期来实现。


```
TextMessage msg = session.createTextMessage("哈哈");
//设置该消息的超时时间，单位为毫秒
producer.setTimeToLive(60000);
producer.send(msg);
```

这里每一条消息的有效期都是不同的，打开ip:8161/admin/就可以查看到，里面的消息越来越少了。

过期的消息是不会被接收到的。

过期的消息会从队列中清除，并存储到ActiveMQ.DLQ这个队列里面，这个稍后会解释。

### 过期消息，处理失败的消息如何处理

 过期的、处理失败的消息，将会被ActiveMQ置入“ActiveMQ.DLQ”这个队列中。

这个队列是ActiveMQ自动创建的。

如果需要查看这些未被处理的消息，可以进入这个队列中查看


```
//指定一个目的地，也就是一个队列的位置
destination = session.createQueue("ActiveMQ.DLQ");
```

这样就可以进入队列中，然后实现接口，或者通过receive()方法，就可以拿到未被处理的消息，从而保证正确的处理


## MQ的关闭

使用Connection实例的close方法进行关闭，这样会释放session等资源。

```
connection.close();
```