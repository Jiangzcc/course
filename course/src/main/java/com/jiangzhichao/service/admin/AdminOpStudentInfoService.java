package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.StudentDTO;

/**
 * 管理员CRUD学生信息
 * 
 * @author BornToWin
 *
 */
public interface AdminOpStudentInfoService {

	/**
	 * 新增学生信息
	 * @param studentDTO
	 * @return
	 */
	int insertStudent(StudentDTO studentDTO);
	
	/**
	 * 删除学生信息
	 * @param sno
	 * @return
	 */
	int deleteStudent(String sno);
	
	/**
	 * 修改学生信息
	 * @param studentDTO
	 * @return
	 */
	int updateStudent(StudentDTO studentDTO);
	
	/**
	 * 通过学号查询学生
	 * @param sno
	 * @return
	 */
	StudentDTO selectStudentBySno(String sno);
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	List<StudentDTO> selectAllStudent();
	
	/**
	 * 导入学生信息列表
	 * @param students
	 */
	void importStudent(List<StudentDTO> students);
}
