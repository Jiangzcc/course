package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.DepartmentDOMapper;
import com.jiangzhichao.entity.DepartmentDO;
import com.jiangzhichao.service.admin.AdminOpDepartmentService;

@Service
@Transactional
public class AdminOpDepartmentServiceImpl implements AdminOpDepartmentService {

	@Autowired
	private DepartmentDOMapper departmentDOMapper;
	
	@Override
	public int insertDepartment(DepartmentDO departmentDO) {
		return departmentDOMapper.insert(departmentDO);
	}

	@Override
	public int deleteDepartment(String dno) {
		//删除前先判断此专业下是否有学生
		int i = departmentDOMapper.deleteByPrimaryKey(dno);
		return i;
	}

	@Override
	public int updateDepartment(DepartmentDO departmentDO) {
		return departmentDOMapper.updateByPrimaryKey(departmentDO);
	}

	@Override
	public DepartmentDO selectDepartmentByDno(String dno) {
		return departmentDOMapper.selectByPrimaryKey(dno);
	}

	@Override
	public List<DepartmentDO> selectAllDepartment() {
		return departmentDOMapper.select();
	}

}
