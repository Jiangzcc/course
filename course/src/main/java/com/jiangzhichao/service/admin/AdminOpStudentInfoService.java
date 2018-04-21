package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.StudentDO;

/**
 * 管理员CRUD学生信息
 * 
 * @author BornToWin
 *
 */
public interface AdminOpStudentInfoService {

	/**
	 * 新增学生信息
	 * @param studentDO
	 * @return
	 */
	int insertStudent(StudentDO studentDO);
	
	/**
	 * 删除学生信息
	 * @param sno
	 * @return
	 */
	int deleteStudent(String sno);
	
	/**
	 * 修改学生信息
	 * @param studentDO
	 * @return
	 */
	int updateStudent(StudentDO studentDO);
	
	/**
	 * 通过学号查询学生
	 * @param sno
	 * @return
	 */
	StudentDO selectStudentBySno(String sno);
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	List<StudentDO> selectAllStudent();
	
	/**
	 * 导入学生信息列表
	 * @param students
	 */
	void importStudent(List<StudentDO> students);
}
