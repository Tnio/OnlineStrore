package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.service.SearchItemService;

/**
 * 导入商品数据到索引库
 * @author yl
 *
 */
@Service
public class SearchItemServiceImpl implements SearchItemService{

	@Autowired
	private SolrServer solrServer;
	@Autowired
	private ItemMapper itemMapper;
	
	public TaotaoResult importAllToIndex() throws Exception {
		//查询所有商品数据
		List<SearchItem> list = itemMapper.getItemList();
		//创建一个文档对象solrInputDocument
		for (SearchItem searchItem : list) {
			//向文件中添加域
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			//把文档写入数据库
			solrServer.add(document);
		}
		//提交
		solrServer.commit();
		return TaotaoResult.ok();
	}

}
