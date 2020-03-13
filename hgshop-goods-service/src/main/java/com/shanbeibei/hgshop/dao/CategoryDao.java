package com.shanbeibei.hgshop.dao;

import java.util.List;

import com.shanbeibei.hgshop.pojo.Category;

/**
 * 
 * @author zhuzg
 *
 */
public interface CategoryDao {

	 List<Category> tree();

	int add(Category category);

	/**
	 * 
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * ÐÞ¸Ä
	 * @param category
	 * @return
	 */
	int update(Category category);
}
