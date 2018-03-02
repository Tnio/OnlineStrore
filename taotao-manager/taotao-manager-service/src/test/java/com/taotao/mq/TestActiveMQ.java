package com.taotao.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class TestActiveMQ {

	@Test
	public void testQueueProducter()throws Exception{
		//创建一个连接工程connectionFactory对象，指定连接ip及端口
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.222:61616");
		//使用connectionFactory创建一个connection对象
		Connection connection = connectionFactory.createConnection();
		//开启连接，调用start方法
		connection.start();
		//使用connection对象创建一个session对象
		//第一个参数：是否开启事务，一般不开启，当值为false的时候，后面的参数才有意义
		//第二个参数：消息的应答模式，手动应答和自动应答，一般自动应答
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//使用session对象创建一个destination，目的地有两种queue,topic
		Queue queue = session.createQueue("test-queue");
		//使用session对象创建一个producer对象
		MessageProducer producer = session.createProducer(queue);
		//使用producer发送信息
		TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("使用activeMQ发送的队列消息");
		//TextMessage textMessage2 = session.createTextMessage("使用activeMQ发送的队列消息");
		producer.send(textMessage);
		//关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void testQueueConsumer()throws Exception{
		//创建一个连接工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.222:61616");
		//使用工厂对象创建一个连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//使用连接对象创建一个session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建一个destination对象，使用queue
		Queue queue = session.createQueue("test-queue");
		//使用session对象创建一个消费者
		MessageConsumer consumer = session.createConsumer(queue);
		//使用消费者对象接收消息
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				//打印消息
				TextMessage textMessage = (TextMessage) message;
				String text = "";
				try {
					 text = textMessage.getText();
					 System.out.println(text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//等待键盘输入
		//System.in.read();
		//关闭资源
		consumer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void testTopicProducer()throws Exception{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.222:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("test-topic");
		MessageProducer producer = session.createProducer(topic);
		TextMessage textMessage = session.createTextMessage("使用topic发送的队列");
		producer.send(textMessage);
		producer.close();
		session.close();
		connection.close();
	}
	
}
