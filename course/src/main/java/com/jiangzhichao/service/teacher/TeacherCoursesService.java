package com.jiangzhichao.service.teacher;

import java.util.List;

import com.jiangzhichao.entity.CourseVO;

/**
 * 教师教授课程Service
 * 
 * @author BornToWin
 *
 */
public interface TeacherCoursesService {

	/**
	 * 教师本学期所授课程
	 * @param tno
	 * @param term
	 * @return
	 */
	List<CourseVO> queryCourseByTnoAndTerm(String tno,String term);
	
	/**
	 * 教师过往学期所授课程
	 * @param tno
	 * @param term
	 * @return
	 */
	List<CourseVO> queryOldCourseByTnoAndTerm(String tno,String term);
	
}
