package com.taotao.solrj;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrCloud {

	@Test
	public void testSolrCloud()throws Exception{
		//创建一个CloudSolrServer对象，构造方法有一个参数，zkHost，是字符串类型的zookeeper地址列表
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.122:2181,192.168.25.122:2182,192.168.25.122:2183");
		//指定默认connection
		solrServer.setDefaultCollection("collection2");
		//向索引库中添加文档
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "001");
		document.addField("item_title", "测试商品");
		document.addField("item_price", "199");
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	@Test
	public void testSolrCloudDelete()throws Exception{
		//创建一个CloudSolrServer对象，构造方法有一个参数，zkHost，是字符串类型的zookeeper地址列表
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.122:2181,192.168.25.122:2182,192.168.25.122:2183");
		//指定默认connection
		solrServer.setDefaultCollection("collection2");
		solrServer.deleteByQuery("*:*");
		//提交
		solrServer.commit();
	}
}
