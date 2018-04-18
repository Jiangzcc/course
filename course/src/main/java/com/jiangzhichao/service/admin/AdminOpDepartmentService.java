package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.DepartmentDO;

public interface AdminOpDepartmentService {
	
	int insertDepartment(DepartmentDO departmentDO);
	
	int deleteDepartment(String dno);
	
	int updateDepartment(DepartmentDO departmentDO);
	
	DepartmentDO selectDepartmentByDno(String dno);
	
	List<DepartmentDO> selectAllDepartment();

}
