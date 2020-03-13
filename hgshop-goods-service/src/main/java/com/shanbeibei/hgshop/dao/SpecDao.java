package com.shanbeibei.hgshop.dao;

import java.util.List;

import com.shanbeibei.hgshop.pojo.Spec;
import com.shanbeibei.hgshop.pojo.SpecOption;

/**
 * 
 * @author shanbeibei
 *
 */
public interface SpecDao {

	List<Spec> list( String name);

	int addSpec(Spec spec);

	int addOption(SpecOption specOption);

	int updateSpec(Spec spec);

	int deleteSpecOtions(int id);

	int deleteSpec(int id);

	Spec get(int id);

	int deleteSpecOtionsBatch(int[] ids);

	int deleteSpecBatch(int[] ids);
	
	List<Spec> listAll();
}
