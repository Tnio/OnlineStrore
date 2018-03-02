package com.taotao.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 监听商品添加事件，生成静态页面
 */
public class HtmlGenListener implements MessageListener {

	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("#{HTML_OUT_PATH}")
	private String HTML_OUT_PATH;
	
	@Override
	public void onMessage(Message message) {
		
		try {
			//创建一个MessageListener接口的实现类
			//从message中获取商品id
			TextMessage textMessage = (TextMessage) message;
			String strItemId = textMessage.getText();
			Long itemId = new Long(strItemId);
			//查询商品基本信息，商品描述
			TbItem tbItem = itemService.getTbitemById(itemId);
			Item item = new Item(tbItem);
			TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
			//创建数据集
			Map map = new HashMap();
			map.put("item",item);
			map.put("itemDesc", tbItemDesc);
			//创建商品详情页面的模板
			//指定文件输出目录
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.htm");
			//指定輸出的文件名
			FileWriter out = new FileWriter(new File(HTML_OUT_PATH+itemId+".html"));
			//生成静态文件
			template.process(map, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
