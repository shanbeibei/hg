package com.shanbeibei.hgshop.service;

import com.shanbeibei.hgshop.pojo.User;

public interface WebUserService {
	/**
	 * ÓÃ»§µÇÂ¼
	 * @param username
	 * @param pwd
	 * @return
	 */
	User login(String username,String pwd);
	/**
	 * ×¢²á
	 * @param user
	 * @return
	 */
	User register(User user);
}
