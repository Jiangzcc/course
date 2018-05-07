package com.jiangzhichao.service.teacher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.entity.CourseVO;
import com.jiangzhichao.service.teacher.TeacherCoursesService;

/**
 * 教师教授课程Service
 * 
 * @author BornToWin
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TeacherCoursesServiceImpl implements TeacherCoursesService {

	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public List<CourseVO> queryCourseByTnoAndTerm(String tno, String term) {
		return courseMapper.selectByTnoAndTerm(tno, term);
	}

	@Override
	public List<CourseVO> queryOldCourseByTnoAndTerm(String tno, String term) {
		return courseMapper.selectOldByTnoAndTerm(tno, term);
	}

}
