package com.shanbeibei.hgshop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.dao.CartDao;
import com.shanbeibei.hgshop.dao.OrderDao;
import com.shanbeibei.hgshop.pojo.Cart;
import com.shanbeibei.hgshop.pojo.Order;
import com.shanbeibei.hgshop.pojo.OrderDetail;
import com.shanbeibei.hgshop.service.CartService;

@Service(interfaceClass=CartService.class)
public class CartServiceImpl  implements CartService {

	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao orderDao;

	public int addCart(Integer uid, int skuId, int buyNum) {
		// TODO Auto-generated method stub
		// ����һ�����������һ���ж�
		Cart cart = new Cart(uid,skuId,buyNum);
		return cartDao.add(cart);
		
	}

	public int delCart(int[] ids) {
		// TODO Auto-generated method stub
		return cartDao.delete(ids);
	}

	public int clearCart(int uid) {
		// TODO Auto-generated method stub
		return cartDao.clear(uid);
	}

	public PageInfo<Cart> list(int uid, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		
		return new PageInfo(cartDao.list(uid));
	}

	public int createOrder(Integer uid, String address,int[] cartIds) {
		// TODO Auto-generated method stub
		//�ȸ��ݹ��ﳵid�������������
		//���ݹ��ﳵid ��ѯ��Ʒ����Ϣ
		List<Cart> cartList  = cartDao.listByIds(cartIds);
		
		//���������ļ۸�
		BigDecimal sumTotal = new BigDecimal(0);
		
		int addNum = 0;
		
		//�����ܼ۸�
		for (Cart cart : cartList) {
			sumTotal.add(cart.getSumTotal());
		}
		
		//�������������
		 Order order = new Order();
		//�û�
		order.setUid(uid);   
		order.setSumtotal(sumTotal);//�����ܼ۸�
		order.setAddress(address);//�ʼĵ�ַ
		
		addNum += orderDao.add(order);
		
		//�����ӱ������
		for (Cart cart : cartList) {
			OrderDetail orderDetail = new OrderDetail(order.getOid(),cart);
			//�����ӱ�
			addNum += orderDao.addDetail(orderDetail);
		}
		
		//�ӹ��ﳵ��ɾ����Щ����
		 cartDao.deleteByIds(cartIds);
		
		
		//�ɹ�Ӱ�����ݵ�����
		return addNum;
	}

	public int clearCart() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
