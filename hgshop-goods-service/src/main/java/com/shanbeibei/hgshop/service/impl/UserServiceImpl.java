package com.shanbeibei.hgshop.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.shanbeibei.hgshop.config.AdminProperties;
import com.shanbeibei.hgshop.service.UserService;
@Service(interfaceClass = UserService.class,version = "1.0.0")
public class UserServiceImpl implements UserService {

	@Autowired
	AdminProperties adminPro;
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		//�ж��û��������Ƿ��������ļ�һ��
		return (adminPro.getAdminName().equals(username)
				&& adminPro.getPassword().equals(password));
	}

}
