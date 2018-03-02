package com.taotao.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {

	
	@Test
	public void testSolrJ()throws Exception{
		//创建一个solrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.122:8080/solr");
		//创建一个文档对象solrInputDocument
		SolrInputDocument document = new SolrInputDocument();
		//向文件中添加域
		document.addField("id", "test01");
		document.addField("item_title","测试商品");
		document.addField("item_price", 10);
		//把文档写入数据库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
}
