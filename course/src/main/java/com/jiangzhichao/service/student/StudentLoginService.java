package com.jiangzhichao.service.student;

import com.jiangzhichao.entity.StudentDTO;

/**
 * 学生登陆Service
 * 
 * @author BornToWin
 *
 */
public interface StudentLoginService {

	/**
	 * 通过学号和密码查询学生信息
	 * @param sno
	 * @param spassword
	 * @return
	 */
	StudentDTO queryStudent(String sno,String spassword);
}
