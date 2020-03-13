package com.shanbeibei.hgshop.service;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Brand;

public interface BrandService {
	PageInfo<Brand> list(String firstChar, int page);

	int deleteBrand(int id);

	int add(Brand spec);

	int update(Brand spec);

	Brand findById(int id);
}
