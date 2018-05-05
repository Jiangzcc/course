package com.jiangzhichao.service.student;

import com.jiangzhichao.entity.StudentDTO;

/**
 * 学生信息Service
 * 
 * @author BornToWin
 *
 */
public interface StudentInfoService {

	/**
	 * 把专业编号换成专业名称
	 * @param student
	 * @return
	 */
	StudentDTO transDno(StudentDTO student);
	
	/**
	 * 修改密码
	 * @param student
	 * @return
	 */
	int updatePassword(StudentDTO student);
}
