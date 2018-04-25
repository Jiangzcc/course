package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.DepartmentDO;

/**
 * 管理员CRUD专业信息Service
 * 
 * @author BornToWin
 *
 */
public interface AdminOpDepartmentService {
	
	/**
	 * 新增专业信息
	 * @param departmentDO
	 * @return
	 */
	int insertDepartment(DepartmentDO departmentDO);
	
	/**
	 * 删除专业信息
	 * @param dno
	 * @return
	 */
	int deleteDepartment(String dno);
	
	/**
	 * 修改专业信息
	 * @param departmentDO
	 * @return
	 */
	int updateDepartment(DepartmentDO departmentDO);
	
	/**
	 * 通过专业编号查询专业信息
	 * @param dno
	 * @return
	 */
	DepartmentDO selectDepartmentByDno(String dno);
	
	/**
	 * 查询所有专业信息
	 * @return
	 */
	List<DepartmentDO> selectAllDepartment();

}
