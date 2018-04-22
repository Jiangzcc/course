package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.CourseDO;

/**
 * 管理员操作课程信息Service
 * 
 * @author BornToWin
 *
 */
public interface AdminOpCourseInfoService {

	/**
	 * 新增课程信息
	 * @param courseDO
	 * @return
	 */
	int insertCourse(CourseDO courseDO);
	
	/**
	 * 删除课程信息
	 * @param cno
	 * @return
	 */
	int deleteCourse(String cno);
	
	/**
	 * 修改课程信息
	 * @param courseDO
	 * @return
	 */
	int updateCourse(CourseDO courseDO);
	
	/**
	 * 通过课程编号查询课程信息
	 * @param cno
	 * @return
	 */
	CourseDO selectCourseByCno(String cno);
	
	/**
	 * 查询所有课程信息
	 * @return
	 */
	List<CourseDO> selectAllCourse();
	
	/**
	 * 导入教师信息
	 */
	void importCourse(List<CourseDO> courses);
}
