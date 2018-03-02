package com.taotao.mq;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@SuppressWarnings("all")
public class TestSpringActiveMQ {

	@Test
	public void testQueueProducer()throws Exception{
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-activemq.xml");
		//从容器中获取JMSTamplate对象
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		//从容器中获取destination对象
		Queue queue = applicationContext.getBean(Queue.class);
		
		jmsTemplate.send(queue, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("使用spring和activemq整合发送的queue2");
				return textMessage;
			}
		});
		
	}
}
