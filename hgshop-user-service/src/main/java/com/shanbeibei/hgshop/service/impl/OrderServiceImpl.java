package com.shanbeibei.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.dao.OrderDao;
import com.shanbeibei.hgshop.pojo.Order;
import com.shanbeibei.hgshop.pojo.OrderDetail;
import com.shanbeibei.hgshop.service.OrderService;

/**
 * 
 * @author zhuzg
 *
 */
@Service(interfaceClass=OrderService.class)
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;

	public PageInfo<Order> list(int userId, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 10);
		
		return new PageInfo<Order>(orderDao.list(userId));
	}

	public List<OrderDetail> listDetail(int orderId, int page) {
		// TODO Auto-generated method stub
		return orderDao.listDetail(orderId);
	}
	
}
