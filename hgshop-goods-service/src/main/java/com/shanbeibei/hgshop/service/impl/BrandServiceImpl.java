package com.shanbeibei.hgshop.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.dao.BrandDao;
import com.shanbeibei.hgshop.pojo.Brand;
import com.shanbeibei.hgshop.service.BrandService;
@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDao brandDao;

	public PageInfo<Brand> list(String firstChar,int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 3);
		return new PageInfo<Brand>(brandDao.list(firstChar));
	}

	public int deleteBrand(int id) {
		// TODO Auto-generated method stub
		brandDao.deleteBrand(id);
		return 1;
	}

	public int add(Brand brand) {
		// TODO Auto-generated method stub
		brandDao.add(brand);
		return 1;
	}

	public int update(Brand brand) {
		// TODO Auto-generated method stub
		brandDao.update(brand);
		return 1;
	}

	public Brand findById(int id) {
		// TODO Auto-generated method stub
		return brandDao.update(id);
	}

}
