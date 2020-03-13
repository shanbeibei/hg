package com.shanbeibei.hgshop.dao;

import java.util.List;

import com.shanbeibei.hgshop.pojo.Order;
import com.shanbeibei.hgshop.pojo.OrderDetail;

public interface OrderDao {
	int addDetail(OrderDetail orderDetail);

	int add(Order order);

	/**
	 * ��ʾһ�ж���
	 * @param userId
	 * @return
	 */
	List<Order> list(int userId);

	/**
	 * ��ʾһ������������
	 * @param orderId
	 * @return
	 */
	List<OrderDetail> listDetail(int orderId);
}
