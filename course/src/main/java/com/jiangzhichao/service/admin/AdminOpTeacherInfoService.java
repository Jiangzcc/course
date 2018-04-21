package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.TeacherDO;

/**
 * 管理员CRUD教师信息
 * 
 * @author BornToWin
 *
 */
public interface AdminOpTeacherInfoService {
	
	/**
	 * 新增教师信息
	 * @param teacherDO
	 * @return
	 */
	int insertTeacher(TeacherDO teacherDO);
	
	/**
	 * 删除教师信息
	 * @param tno
	 * @return
	 */
	int deleteTeacher(String tno);
	
	/**
	 * 修改教师信息
	 * @param teacherDO
	 * @return
	 */
	int updateTeacher(TeacherDO teacherDO);
	
	/**
	 * 通过教师编号查询教师信息
	 * @param tno
	 * @return
	 */
	TeacherDO selectTeacherByTno(String tno);
	
	/**
	 * 查询所有教师信息
	 * @return
	 */
	List<TeacherDO> selectAllTeacher();
	
	/**
	 * 导入教师信息列表
	 * @param teachers
	 */
	void importTeacher(List<TeacherDO> teachers);

}
