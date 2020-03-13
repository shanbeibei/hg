package com.shanbeibei.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Order;
import com.shanbeibei.hgshop.pojo.OrderDetail;



public interface OrderService {

	
	
	PageInfo<Order> list(int userId,int page);
	
	
	List<OrderDetail> listDetail(int orderId,int page);
	
	
	
	

}
