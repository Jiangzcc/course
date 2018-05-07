package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.DepartmentMapper;
import com.jiangzhichao.dao.StuCourseMapper;
import com.jiangzhichao.dao.TeacherMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.DepartmentDTO;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.service.admin.AdminOpCourseInfoService;

/**
 * 管理员操作课程信息Service
 * 
 * @author BornToWin
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminOpCourseInfoServiceImpl implements AdminOpCourseInfoService {

	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private StuCourseMapper stuCourseMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public int insertCourse(CourseDTO courseDTO) {
		return courseMapper.insert(courseDTO);
	}

	@Override
	public int deleteCourse(String cno) {
		int i = 0;
		List<StuCourseDTO> cnos = stuCourseMapper.selectByCno(cno);
		if(cnos.size() == 0) {
			i = courseMapper.deleteByPrimaryKey(cno);
		}
		return i;
	}

	@Override
	public int updateCourse(CourseDTO courseDTO) {
		return courseMapper.updateByPrimaryKey(courseDTO);
	}

	@Override
	public CourseDTO selectCourseByCno(String cno) {
		return courseMapper.selectByPrimaryKey(cno);
	}

	@Override
	public List<CourseDTO> selectAllCourse() {
		// 懒得改Sql
		List<CourseDTO> courses = courseMapper.select();
		List<DepartmentDTO> departs = departmentMapper.select();
		List<TeacherDTO> teachers = teacherMapper.select();
		for (CourseDTO course : courses) {
			for (DepartmentDTO department : departs) {
				if(course.getDno() != null && course.getDno().equals(department.getDno())) {
					course.setDno(department.getDname());
				}
				if(course.getDno() == null) {
					course.setDno("公共课");
				}
			}
			for (TeacherDTO teacher : teachers) {
				if(course.getTno() != null && course.getTno().equals(teacher.getTno())) {
					course.setTno(teacher.getTname());
				}
				if(course.getTno() == null) {
					course.setTno("空");
				}
			}
		}
		return courses;
	}

	@Override
	public void importCourse(List<CourseDTO> courses) {
		for (CourseDTO courseDTO : courses) {
			courseMapper.insertSelective(courseDTO);
		}
	}

}
