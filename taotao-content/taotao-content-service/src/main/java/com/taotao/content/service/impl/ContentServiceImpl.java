package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;


@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	
	/**
	 * 添加
	 */
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入
		contentMapper.insert(content);
		
		//缓存同步，删除redis中cid对应的缓存信息
		jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());
		
		return TaotaoResult.ok();
	}


	/**
	 * 根据cid查询内容列表
	 */
	public List<TbContent> getContentList(long cid) {
		
		//先在缓存中进行查询
		try {
			String json = jedisClient.hget(CONTENT_KEY, cid+"");
			if (StringUtils.isNotBlank(json)) {
				//如果不为空，转成list直接返回
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//添加查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//将查询到的数据添加到缓存中
		try {
			jedisClient.hset(CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
