package com.taotao.jedis;


import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.content.jedis.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@SuppressWarnings("all")
public class JedisTest {

	@Test
	public void testJedisSingle()throws Exception{
		//参数1：IP地址		参数2：端口
		Jedis jedis = new Jedis("192.168.25.111",6379);
		jedis.set("test", "1000");
		String string = jedis.get("test");
		System.out.println(string);
	}
	
	@Test
	public void testJedisPool()throws Exception{
		//创建一个连接池对象
		JedisPool jedisPool = new JedisPool("192.168.25.111", 6379);
		//从连接池中获取连接
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("test");
		System.out.println(string);
		//每次jedis使用完毕之后需要关闭，连接池进行回收
		jedis.close();
		//连接池也想要进行关闭
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster()throws Exception{
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.111", 7001));
		nodes.add(new HostAndPort("192.168.25.111", 7002));
		nodes.add(new HostAndPort("192.168.25.111", 7003));
		nodes.add(new HostAndPort("192.168.25.111", 7004));
		nodes.add(new HostAndPort("192.168.25.111", 7005));
		nodes.add(new HostAndPort("192.168.25.111", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		//系统中可以是单例
		String string = jedisCluster.get("test1");
		System.out.println(string);
		//系统结束之前关闭
		jedisCluster.close();
	}
	
	@Test
	public void testJedisClentPool()throws Exception{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisClient client = applicationContext.getBean(JedisClient.class);
		client.set("client", "haha");
		String result = client.get("client");
		System.out.println(result);
		
	}
	
	
	
}
