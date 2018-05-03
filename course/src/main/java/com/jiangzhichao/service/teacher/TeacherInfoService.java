package com.jiangzhichao.service.teacher;

import com.jiangzhichao.entity.TeacherDTO;

/**
 * 教师信息Service
 * 
 * @author BornToWin
 *
 */
public interface TeacherInfoService {
	
	/**
	 * 修改密码
	 * @param teacherDTO
	 * @return
	 */
	int updatePassword(TeacherDTO teacherDTO);

}
