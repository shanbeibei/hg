package com.shanbeibei.hgshop.dao;

import java.util.List;

import com.shanbeibei.hgshop.pojo.Cart;
/**
 * 
 * @author shanbeibei
 *
 */
public interface CartDao {
	
	int delete(int[] ids);

	int add(Cart cart);

	int clear(int uid);

	List<Cart> list(int uid);

	List<Cart> listByIds(int[] cartIds);
	
	void deleteByIds(int[] cartIds);

}
