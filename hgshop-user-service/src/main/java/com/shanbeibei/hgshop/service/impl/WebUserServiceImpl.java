package com.shanbeibei.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.shanbeibei.hgshop.dao.UserDao;
import com.shanbeibei.hgshop.pojo.User;
import com.shanbeibei.hgshop.service.WebUserService;
/**
 * 用户的服务  这里用户是普通用户 也就是购物的消费者
 * @author lenovo
 *
 */
@Service(interfaceClass = WebUserService.class)
public class WebUserServiceImpl implements WebUserService {

	@Autowired
	UserDao userDao;
	public User login(String username, String pwd) {
		// TODO Auto-generated method stub
		//记得加密
		return userDao.find(username,DigestUtils.md5Hex(pwd));
	}

	public User register(User user) {
		// TODO Auto-generated method stub
		//明文转换成密文
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		//添加成功
		if(userDao.add(user)>0) {
			 return userDao.getById(user.getUid());
		}
		//添加失败
		
		return null;
	}

}
