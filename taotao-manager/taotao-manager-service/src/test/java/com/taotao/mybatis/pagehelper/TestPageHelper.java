package com.taotao.mybatis.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

@SuppressWarnings("all")
public class TestPageHelper {

	@Test
	public void testPageHelper() throws Exception{
		//初始化spring容器
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//从容器中获得mapper代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//执行查询
		TbItemExample example = new TbItemExample();
		
		//分页插件，PageHelper，设置获取第一页，设置10条内容
		PageHelper.startPage(1, 10);
		
		//紧跟着的第一个select的方法会被分页
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//取分页信息
		System.out.println("结果集中的记录数："+list.size());
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("总记录数:"+pageInfo.getTotal());
		System.out.println("总页数："+pageInfo.getPages());
	}
}
