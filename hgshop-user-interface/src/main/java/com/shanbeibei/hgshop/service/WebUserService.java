package com.shanbeibei.hgshop.service;

import com.shanbeibei.hgshop.pojo.User;

public interface WebUserService {
	/**
	 * �û���¼
	 * @param username
	 * @param pwd
	 * @return
	 */
	User login(String username,String pwd);
	/**
	 * ע��
	 * @param user
	 * @return
	 */
	User register(User user);
}
