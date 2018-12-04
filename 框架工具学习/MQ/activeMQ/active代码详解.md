
## Session方法使用

一旦从ConnectionFacatory中获得一个Connection，必须从Connection中创建一个Session。session是一个发送或者接收消息的对象，可以使用session创建MessageProducer(生产者)，MessageConsumer(消费者)和Message(消息)。
**Session可以被事务化，也可以不被事务化，通常可以通过向Connection的创建session的方法传递一个布尔参数对此进行设置**

Session createSession(boolean transacted, int acknowledgeMode)throws JMSException;  
transacted：是否开启事务  
acknowledgeMode:消息签收模式

在事务状态下进行发送操作，消息并未真正投递到中间件，而只有进行session.commit操作之后，消息才会发送到中间件，再转发到适当的消费者进行处理。如果是调用rollback操作，则表明，当前事务期间内所发送的消息都取消掉。此时无论commit或rollback，会重新打开一个事务。 


与此同时，在rollback之后，随着新的事务打开，一些持久化的消息会重新接收。原因在于当传送模式处于持久话的状态，产生的消息如若没有被及时签收确认，则消息会被中间件持久化。此时，当客户端重新连接或新的事务开启，消息会被再次发送到客户端。
为什么commit之后，不会有持久的消息重新传送呢?  

原因在于commit操作会自动将为签收确认的消息进行签收确认，如果是当前接收但未签收确认的消息，都会被确认处理。因而在commit之后不会有持久化的消息出现。

**签收模式**

Session.AUTO_ACKNOWLEDGE : 当客户端从receive或onMessage成功返回时，Session自动签收客户端的收条。(常用)

CLIENT_ACKNOWLEDGE : 客户端通过调用消息的acknowledge方法签收消息。在这种情况下，签收发生在session层面，签收一个已消费的消息，会自动签收这个session所有已消费的消息的收条。

DUPS_OK_ACKNOWLEDGE : 此选项不必确保对传送消息的签收。他可能引起消息的重复，但是降低了session的开销，所以只有当客户端能容忍重复消息时，才可使用。
