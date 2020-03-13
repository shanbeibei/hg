package com.shanbeibei.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shanbeibei.hgshop.pojo.Brand;

public interface BrandDao {

	List<Brand> list(String firstChar);

	int deleteBrand(int id);

	int add(Brand brand);

	int update(Brand brand);

	Brand update(int id);
	
	/**
	 * 获取所有的品牌
	 * @return
	 */
	@Select("SELECT id,name,first_char as firstChar "
			+ " FROM hg_brand "
			+ "where deleted_flag=0"
			+ " ORDER BY name ")
	List<Brand> listAll();
	
}
