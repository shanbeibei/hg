package com.shanbeibei.hgshop.service;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Cart;
/**
 * ���ﳵ�ķ���
 * @author lenovo
 *
 */
public interface CartService {


	//���
	/**
	 * 
	 * @param uid �û�id
	 * @param skuId
	 * @param buyNum  ��������
	 * @return
	 */
	int addCart(Integer uid, int skuId, int buyNum);
	
	//ɾ��
	/**
	 * 
	 * @param ids   ���ﳵ������id
	 * @return
	 */
	int delCart(int[] ids);
	/**
	 * ��չ��ﳵ
	 * @return
	 */
	public int clearCart(int uid) ;
	
	//�鿴���ﳵ
	/**
	 * 
	 * @param uid �û�id
	 * @param page
	 * @return
	 */
	PageInfo<Cart> list(int uid,int page);

	/**
	 * 
	 * @param uid
	 * @param cartIds
	 * @return
	 */
	int createOrder(Integer uid, String address,int[] cartIds); 
	
	
	
}
