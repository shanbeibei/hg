package com.shanbeibei.hgshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shanbeibei.hgshop.pojo.Sku;
import com.shanbeibei.hgshop.pojo.SpecOption;

public interface SkuDao {

	List<Sku> list(Sku sku);
	
	Sku get(int id);
	
	// ���sku
	int addSku(Sku sku);
	//��Ӷ�Ӧsku������ֵ
	int addSkuSpec(@Param("skuId") int skuId,@Param("so") SpecOption so);
	/**
	 * ��ȡһ��sku���б� ����spu
	 * @param spuId
	 * @return
	 */
	List<Sku> listBySpu(int spuId);
	
}
