package com.taotao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理controller
 * @author yl
 */
@Controller
public class ItemController {

	@Resource
	private ItemService itemService;
	
	/**
	 * 删除商品
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult deleteItem(int[] ids){
		TaotaoResult result = itemService.deleteItemById(ids);
		return result; 
	}
	
	/**
	 * 上架商品
	 */
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public TaotaoResult upItem(int[] ids){
		TaotaoResult result = itemService.upItem(ids);
		return result;
	}
	
	/**
	 * 下架商品
	 */
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public TaotaoResult downItem(int[] ids){
		TaotaoResult result = itemService.downItem(ids);
		return result;
	}
	
	/**
	 * 查询所有商品
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	/**
	 * 添加商品
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item ,String desc){
		TaotaoResult result = itemService.addItem(item, desc);
		return result;
	}
	
}
