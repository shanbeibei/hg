package com.shanbeibei.hgshop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanbeibei.hgshop.dao.SpecDao;
import com.shanbeibei.hgshop.pojo.Spec;
import com.shanbeibei.hgshop.pojo.SpecOption;
import com.shanbeibei.hgshop.service.SpecService;

/**
 * ���Ĺ���
 * @author shanbeibei
 *
 */
@Service(interfaceClass=SpecService.class)
public class SpecServiceImpl implements SpecService{
	
	@Autowired
	SpecDao specDao;

	public PageInfo<Spec> list(String name, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, 3);
		return new PageInfo<Spec>(specDao.list(name));
		
	}

	public int add(Spec spec) {
		// TODO Auto-generated method stub
		//�������
		specDao.addSpec(spec);
		// ������ܻ�ȡ������ID
		//����ӱ�
		List<SpecOption> options = spec.getOptions();
		//
		int n=1;
		for (SpecOption specOption : options) {
			// ���������id
			specOption.setSpecId(spec.getId());
			specDao.addOption(specOption);
			n++;
		}
		
		// ���ص���Ӱ�����ݵ�����
		return n;
	}

	
	public int update(Spec spec) {
		// TODO Auto-generated method stub
		// ȥ�ӱ���ɾ��
		specDao.deleteSpecOtions(spec.getId());
		// �޸�����
		specDao.updateSpec(spec);	 
		// �����ӱ�
		List<SpecOption> options = spec.getOptions();
		for (SpecOption specOption : options) {
			// ���������id
			specOption.setSpecId(spec.getId());
			specDao.addOption(specOption);
		}
		
		return 1;
		 
	}
	

	public int delete(int id) {
		// TODO Auto-generated method stub
		//ɾ���ӱ�
		specDao.deleteSpecOtions(id);
		//ɾ������
		specDao.deleteSpec(id);
		return 1;
	}

	public Spec findById(int id) {
		// TODO Auto-generated method stub
		
		return specDao.get(id);
	}

	public int deleteBatch(int[] ids) {
		// TODO Auto-generated method stub
		//ɾ���ӱ�
		specDao.deleteSpecOtionsBatch(ids);
		//ɾ������
		specDao.deleteSpecBatch(ids);
		return 1;
	}

	public List<Spec> listAll() {
		// TODO Auto-generated method stub
		return specDao.listAll();
	}
	
	

}
