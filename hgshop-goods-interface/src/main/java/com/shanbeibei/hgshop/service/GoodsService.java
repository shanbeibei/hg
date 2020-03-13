package com.shanbeibei.hgshop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.pojo.Brand;
import com.shanbeibei.hgshop.pojo.Category;
import com.shanbeibei.hgshop.pojo.Sku;
import com.shanbeibei.hgshop.pojo.Spu;
import com.shanbeibei.hgshop.pojo.SpuVo;

/**
 * 
 * @author zhuzg
 *  
 *  **** Dubbo ����ӿں�����Ҫ�з�Void �ķ���ֵ********
 *   
 */
public interface GoodsService { 
	
	int addBrand(Brand brand);
	int updateBrand(Brand brand);
	int deleteBrand(Integer id);
	/**
	 * 
	 * @param firstChar ����ĸ
	 * @param page ҳ��
	 * @return
	 */
	PageInfo<Brand> listBrand( String firstChar,int page); 
	
	
	int addCategory(Category category);
	int updateCategory(Category category);
	int deleteCategory(Integer id);
	/**
	 * 
	 * @param firstChar ����ĸ
	 * @param page ҳ��
	 * @return
	 */
	PageInfo<Category> listCategory( String firstChar,int page); 
	/**
	 * ��������ʽ��ʾ�б�
	 * @return
	 */
	List<Category> treeCategory(); 
	

	// spu�Ĺ���
	PageInfo<Spu>  listSpu(int page,SpuVo vo);
	int addSpu(Spu spu);
	int updateSpu(Spu spu);
	int deleteSpu(int id);
	Spu getSpu(int id);
	int deleteSpuBatch(int[] id);
	
	// sku�Ĺ���
	PageInfo<Sku>  listSku(int page,Sku sku);
	int addSku(Sku sku);
	Sku getSku(int id);//��ȡ����
	int updateSku(Sku sku);
	int deleteSku(int id);
	int deleteSkuBatch(int[] id);
	List<Brand> getAllBrands();
	/**
	 * ����spu ��ȡ���е�sku
	 * @param page
	 * @param sku
	 * @return
	 */
	List<Sku> listSkuBySpu(int spuId);

}
