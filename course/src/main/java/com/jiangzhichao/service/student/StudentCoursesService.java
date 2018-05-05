package com.jiangzhichao.service.student;

import java.util.List;

import com.jiangzhichao.entity.CourseVO;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentDTO;

/**
 * 学生选课信息Service
 * 
 * @author BornToWin
 *
 */
public interface StudentCoursesService {

	/**
	 * 查询本学期所有可选课程信息
	 * @param dno
	 * @param term
	 * @return
	 */
	List<CourseVO> queryAllCourseByDnoAndTerm(String dno,String term);
	
	/**
	 * 查询本学期某学生可选课程信息
	 * @param student
	 * @return
	 */
	List<CourseVO> queryOptionsBySno(StudentDTO student);

	/**
	 * 查询本学期已选课程信息和成绩
	 * @param sno
	 * @return
	 */
	List<CourseVO> querySelectedBySno(String sno);
	
	/**
	 * 查询过往学期已选课程信息和成绩
	 * @param sno
	 * @return
	 */	
	List<CourseVO> querySelectedOldBySno(String sno);
	
	/**
	 * 选课
	 * @param stuCourseDTO
	 * @return
	 */
	int takeCourse(StuCourseDTO stuCourseDTO);
	
	/**
	 * 退课
	 * @param stuCourseDTO
	 * @return
	 */
	int dropCourse(StuCourseDTO stuCourseDTO);
}
