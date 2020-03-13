package com.shanbeibei.hgshop.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.comm.HgshopConstant;
import com.shanbeibei.hgshop.pojo.Cart;
import com.shanbeibei.hgshop.pojo.Order;
import com.shanbeibei.hgshop.pojo.User;
import com.shanbeibei.hgshop.service.CartService;
import com.shanbeibei.hgshop.service.OrderService;
import com.shanbeibei.hgshop.service.WebUserService;
/**
 * ���ڴ������û���ص�����
 * @author lenovo
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Reference
	WebUserService webuserService;

	@Reference
	CartService cartService;
	
	@Reference
	OrderService orderService;
	/**
	 * �����¼ҳ��
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		
		
		return "user/login";
	}
	/**
	 * �����û���¼������
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request,String username,String password) {
		User user=webuserService.login(username, password);
		if (user==null) {
			request.setAttribute("error", "�û��������");
			return "user/login";
		}
		//д��session
		request.getSession().setAttribute(HgshopConstant.USERKEY,user);
		return "redirect:/user/home";
	}
	
	
	@RequestMapping("home")
	public String home() {
		return "/user/index";
	}
	
	/**
	 * ����ע��ҳ��
	 * @return
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/register";
	}
	
	/**
	 * �����û�ע���ύ������
	 * @param request
	 * @return
	 */
	@RequestMapping("register")
	public String login(HttpServletRequest request,User user) {
		//ע��
		User register=webuserService.register(user);
		if (register==null) {
			request.setAttribute("error", "ע��ʧ��");
			return "user/register";
		}
		//ע��ɹ���ת����¼ҳ��
		return "redirect:toLogin";
	}
	/**
	 * ���빺�ﳵ
	 * @param skuId
	 * @param buyNum ��������
	 * @return
	 */
	@RequestMapping(value = "addCart",produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String addCart(HttpServletRequest request,int skuId,int buyNum) {
		
		User loginUser = (User) request.getSession().getAttribute(HgshopConstant.USERKEY);
		if (loginUser==null) {			
			return "�ף�����δ��½�����ܼ��빺�ﳵŶ";
		}
		int result= cartService.addCart(loginUser.getUid(),skuId,buyNum);
		return result>0?"success":"���ʧ��";
	}
	
	/**
	 * �����������
	 * @param request
	 * @return
	 */
	public String home(HttpServletRequest request) {
		
		
		return "user/home";
	}
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("cartlist")
	public String cartlist(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page) {
		//��ȡ��ǰ��¼���û�
		User loginUser = (User)request.getSession().getAttribute(HgshopConstant.USERKEY);
		if(loginUser==null) {
			request.setAttribute("error", "����δ��½");
			return "error";
		}
		PageInfo<Cart> cartList = cartService.list(loginUser.getUid(), page);
		request.setAttribute("pageInfo", cartList);
		return "user/cartlist";
		
	}
	
	/**
	 * 
	 * @param request
	 * @param cartIds 
	 * @param address  �ʼĵ�ַ
	 * @return
	 */
	@RequestMapping("addorder")
	@ResponseBody
	public String addorder(HttpServletRequest request,
			@RequestParam("cartIds[]") int[] cartIds,String address){
		//��ȡ��ǰ��¼���û�
		User loginUser = (User)request.getSession().getAttribute(HgshopConstant.USERKEY);
		if(loginUser==null) {
			request.setAttribute("error", "����δ��½");
			return "error";
		}
		System.out.println("cartIds is " + cartIds);
		int result = cartService.createOrder(loginUser.getUid(),address, cartIds);
		return result>0?"success":"���ʧ��";
	}
	
	/**
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("orderlist")
	public String orderlist(HttpServletRequest request,
			@RequestParam(defaultValue="1") int page) {
		//��ȡ��ǰ��¼���û�
		User loginUser = (User)request.getSession().getAttribute(HgshopConstant.USERKEY);
		if(loginUser==null) {
			request.setAttribute("error", "����δ��½");
			return "error";
		}
		PageInfo<Order> list = orderService.list(loginUser.getUid(), page);
		request.setAttribute("pageInfo", list);
		return "user/orderlist";
		
	}
}
