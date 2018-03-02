package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

/**
 * 内容分类管理controller
 */


@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService categoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(defaultValue="0")Long id){
		List<EasyUITreeNode> list = categoryService.getCateGory(id);
		return list;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult insertContentCat(Long parentId,String name){
		TaotaoResult result = categoryService.insertContentCat(parentId, name);
		return result;
	}
}
