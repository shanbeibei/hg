package com.shanbeibei.hgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanbeibei.hgshop.pojo.Category;
import com.shanbeibei.hgshop.service.GoodsService;

/**
 *  类别管理
 * @author zhuzg
 *
 */
@Controller
@RequestMapping("cat")
public class CategoryController {
	
	@Reference
	GoodsService goodsService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		return "cat/list";
	}
	
	@RequestMapping("treeData")
	@ResponseBody
	public List<Category> treeData(HttpServletRequest request) {
		return goodsService.treeCategory();
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,
			@RequestParam(defaultValue="0") int parentId,String name ) {
		Category category = new Category();
		 category.setParentId(parentId);
		 category.setName(name);
		return goodsService.addCategory(category)>0?"success":"failed";
	}
	
	
	@RequestMapping("del")
	@ResponseBody
	public String del(HttpServletRequest request,
			@RequestParam(defaultValue="0") int id) {
		
		
		return goodsService.deleteCategory(id)>0 ?"success":"failed";
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,
			Category cat) {
		System.err.println( "cat is " +  cat);
		return goodsService.updateCategory(cat)>0 ?"success":"failed";
	}
	
	
	

}
