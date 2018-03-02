package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.ItemSearchDao;
import com.taotao.search.service.SearchService;


@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private ItemSearchDao itemSearchDao;

	public SearchResult search(String queryString, int page, int rows) throws Exception {
		//创建一个SolrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(queryString);
		//设置分页条件
		if (page<1) page = 1;
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		//需要指定默认搜素域
		solrQuery.set("df", "item_title");
		//设置高亮
		solrQuery.setHighlight(true);
		//设置高亮的域
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		//执行查询，调用searchDao，得到searchResult
		SearchResult searchResult = itemSearchDao.search(solrQuery);
		//计算总页数
		long recordCount = searchResult.getRecordCount();
		long pageCount = recordCount / rows;
		if (pageCount > 0 ) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		//返回searchResult
		return searchResult;
	}

}
