package com.shanbeibei.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.dao.BrandDao;
import com.shanbeibei.hgshop.dao.CategoryDao;
import com.shanbeibei.hgshop.dao.SkuDao;
import com.shanbeibei.hgshop.dao.SpuDao;
import com.shanbeibei.hgshop.pojo.Brand;
import com.shanbeibei.hgshop.pojo.Category;
import com.shanbeibei.hgshop.pojo.Sku;
import com.shanbeibei.hgshop.pojo.SpecOption;
import com.shanbeibei.hgshop.pojo.Spu;
import com.shanbeibei.hgshop.pojo.SpuVo;
import com.shanbeibei.hgshop.service.GoodsService;

/**
 * 
 * @author zhuzg
 *
 */
@Service(interfaceClass=GoodsService.class)
public class GoodsServiceImpl  implements GoodsService{
	@Autowired
	CategoryDao catDao;

	@Autowired
	SpuDao spuDao;

	@Autowired
	SkuDao skuDao;

	@Autowired
	BrandDao brandDao;

	public int addBrand(Brand brand) {
		// TODO Auto-generated method stub

		return 0;
	}

	public int updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteBrand(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PageInfo<Brand> listBrand(String firstChar, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return catDao.add(category);
	}

	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		return catDao.update(category);
	}

	public int deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		return catDao.delete(id);
	}

	/**
	 * 
	 */

	public PageInfo<Category> listCategory(String firstChar, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> treeCategory() {
		// TODO Auto-generated method stub
		return catDao.tree();
	}

	// spu的列表

	public PageInfo<Spu> listSpu(int page, SpuVo vo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 10);

		return new PageInfo<Spu>(spuDao.list(vo));
	}

	public int addSpu(Spu spu) {
		// TODO Auto-generated method stub
		int cnt = spuDao.add(spu);
		// kafaTemplate.send("MyAddSpu", "spuId", cnt+"");
		return cnt;

	}

	public int updateSpu(Spu spu) {
		// TODO Auto-generated method stub
		return spuDao.update(spu);
	}

	public int deleteSpu(int id) {
		// TODO Auto-generated method stub
		return spuDao.delete(id);
	}

	public int deleteSpuBatch(int[] ids) {
		// TODO Auto-generated method stub
		return spuDao.deleteSpuBatch(ids);
	}

	public List<Brand> getAllBrands() {
		// TODO Auto-generated method stub
		return brandDao.listAll();
	}

	public PageInfo<Sku> listSku(int page, Sku sku) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 5);
		return new PageInfo<Sku>(skuDao.list(sku));
	}

	public int addSku(Sku sku) {
		// TODO Auto-generated method stub
		// 先加主表
		int cnt = skuDao.addSku(sku);
		List<SpecOption> specs = sku.getSpecs();
		for (SpecOption specOption : specs) {
			cnt += skuDao.addSkuSpec(sku.getId(), specOption);
		}

		return cnt;
	}

	public Sku getSku(int id) {
		// TODO Auto-generated method stub
		return skuDao.get(id);
	}

	public int updateSku(Sku sku) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteSku(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteSkuBatch(int[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Spu getSpu(int id) {
		// TODO Auto-generated method stub
		return spuDao.findById(id);
	}

	public List<Sku> listSkuBySpu(int spuId) {
		// TODO Auto-generated method stub
		return skuDao.listBySpu(spuId);
	}


}
