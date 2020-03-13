package com.shanbeibei.hgshop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.shanbeibei.hgshop.dao.UserDao;
import com.shanbeibei.hgshop.pojo.User;
import com.shanbeibei.hgshop.service.WebUserService;
/**
 * �û��ķ���  �����û�����ͨ�û� Ҳ���ǹ����������
 * @author lenovo
 *
 */
@Service(interfaceClass = WebUserService.class)
public class WebUserServiceImpl implements WebUserService {

	@Autowired
	UserDao userDao;
	public User login(String username, String pwd) {
		// TODO Auto-generated method stub
		//�ǵü���
		return userDao.find(username,DigestUtils.md5Hex(pwd));
	}

	public User register(User user) {
		// TODO Auto-generated method stub
		//����ת��������
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		//��ӳɹ�
		if(userDao.add(user)>0) {
			 return userDao.getById(user.getUid());
		}
		//���ʧ��
		
		return null;
	}

}
