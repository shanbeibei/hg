package com.shanbeibei.hgshop.service;
/**
 * 
 * @author lenovo
 *
 */

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Spec;

public interface SpecService {
	PageInfo<Spec> list(String name,int page);
	
	List<Spec> listAll();
	
	int add(Spec spec);
	
	int update(Spec spec);
	
	int delete(int id);
	/**
	 * ����һ����� �����޸ĵ�ʱ�����
	 * @param id
	 * @return
	 */
	Spec findById(int id);
	
	int deleteBatch(int[] id);
	
}
