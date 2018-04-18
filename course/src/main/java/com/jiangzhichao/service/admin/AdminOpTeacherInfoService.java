package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.TeacherDO;

public interface AdminOpTeacherInfoService {
	
	int insertTeacher(TeacherDO teacherDO);
	
	int deleteTeacher(String tno);
	
	int updateTeacher(TeacherDO teacherDO);
	
	TeacherDO selectTeacherByTno(String tno);
	
	List<TeacherDO> selectAllTeacher();

}
