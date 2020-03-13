package com.shanbeibei.hgshop.controller;

import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Spec;
import com.shanbeibei.hgshop.pojo.SpecOption;
import com.shanbeibei.hgshop.service.SpecService;


/**
 * 
 * @author shanbeibei
 * ���Ĺ���
 */
@Controller
@RequestMapping("spec")
public class SpecController {
	
	@Reference
	SpecService specService;
	
	/**
	 * ��������б�
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="") String name
	) {
		 PageInfo<Spec> pageInfo = specService.list(name, page);
		//pageInfo.getPages()
		 request.setAttribute("pageInfo", pageInfo);
		 request.setAttribute("queryName", name);
		return "spec/list";
	}
	
	/**
	 * ���
	 * @param request
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,Spec spec) {
		//System.out.println("spec" + spec);
		//System.out.println();
		spec.getOptions().removeIf(new Predicate<SpecOption>() {
			public boolean test(SpecOption x) {return x.getOptionName()==null;}
		});
		//System.out.println("spec �����" + spec);
		//���÷���
		int add = specService.add(spec);
		return add>0?"success":"false";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest request,Spec spec) {
		System.out.println("spec" + spec);
		//System.out.println();
		spec.getOptions().removeIf(new Predicate<SpecOption>() {
			public boolean test(SpecOption x) {return x.getOptionName()==null;}
		});
		System.out.println("spec �����" + spec);
		//���÷���
		int result = specService.update(spec);  
		return result >0 ?"success":"false";
		//return "fail";
	}
	
	
	
	/**
	 * �����޸�����ʱ��Ļ���
	 * @param request
	 * @param id ���id
	 * @return
	 */
	@RequestMapping("getSpec")
	@ResponseBody
	public Spec getSpec(HttpServletRequest request, int id){
		return specService.findById(id);
		
	}
	
	
	/**
	 * ɾ�����
	 * @param request
	 * @param id  ����id
	 * @return
	 */
	@RequestMapping("delSpec")
	@ResponseBody
	public String delSpec(HttpServletRequest request,int id) {
		//���÷���
		int delNum = specService.delete(id);
		return delNum>0?"success":"false";
	}
	
	/**
	 * ɾ�����
	 * @param request
	 * @param id  ����id
	 * @return
	 */
	@RequestMapping("delSpecBatch")
	@ResponseBody
	public String delSpecBatch(HttpServletRequest request,@RequestParam(name="ids[]") int[] ids) {
		System.out.println("Ҫɾ���Ķ�����");
		for (int i : ids) {
			System.out.println(" i is " + i  );
		}
		//���÷���
		int delNum = specService.deleteBatch(ids);
		return delNum>0?"success":"false";
	}

}
