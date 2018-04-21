package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.DepartmentDO;

/**
 * ����ԱCRUDרҵ��ϢService
 * 
 * @author BornToWin
 *
 */
public interface AdminOpDepartmentService {
	
	/**
	 * ����רҵ��Ϣ
	 * @param departmentDO
	 * @return
	 */
	int insertDepartment(DepartmentDO departmentDO);
	
	/**
	 * ɾ��רҵ��Ϣ
	 * @param dno
	 * @return
	 */
	int deleteDepartment(String dno);
	
	/**
	 * �޸�רҵ��Ϣ
	 * @param departmentDO
	 * @return
	 */
	int updateDepartment(DepartmentDO departmentDO);
	
	/**
	 * ͨ��רҵ��Ų�ѯרҵ��Ϣ
	 * @param dno
	 * @return
	 */
	DepartmentDO selectDepartmentByDno(String dno);
	
	/**
	 * ��ѯ����רҵ��Ϣ
	 * @return
	 */
	List<DepartmentDO> selectAllDepartment();

}
