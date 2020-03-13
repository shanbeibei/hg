package com.shanbeibei.hgshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * 配置类 读取属性文件
 * @author shanbeibei
 *
 */
@Configuration
@PropertySource("classpath:hgshop-admin.peoperties")
public class AdminProperties{
	@Value("${admin.name}")
	String adminName;
	@Value("${admin.pwd}")
	String password;
	public String getAdminName() {
		return adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
