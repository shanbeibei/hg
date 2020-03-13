package com.shanbeibei.hgshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Brand;
import com.shanbeibei.hgshop.service.BrandService;
/**
 * Ʒ�ƹ���
 * @author lenovo
 *
 */
@Controller
@RequestMapping("brand")
public class BrandController {
//	s
	@Reference
	BrandService brandService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="") String firstChar
	) {
		 PageInfo<Brand> pageInfo = brandService.list(firstChar,page);
		//pageInfo.getPages()
		 request.setAttribute("pageInfo", pageInfo);
		 request.setAttribute("firstChar", firstChar);
		return "brand/list";
	}
	
	@RequestMapping("delbrand")
	@ResponseBody
	public String delBrand(HttpServletRequest request,int id) {
		//���÷���
		int delNum = brandService.deleteBrand(id);
		return delNum>0?"success":"false";
	}
	
	/**
	 * ���
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,Brand spec ) {
		System.err.println(spec);
		int add = brandService.add(spec);
		return add>0?"success":"false";
	}
	/**
	 * �޸�
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,Brand spec) {
		int result = brandService.update(spec);  
		return result >0 ?"success":"false";
		//return "fail";
	}
	
	
	/**
	 * �����޸�����ʱ��Ļ���
	 */
	@RequestMapping("getSpec")
	@ResponseBody
	public Brand getSpec(HttpServletRequest request, int id){
		System.err.println("��ȡ");
		return brandService.findById(id);
	}
	
}
