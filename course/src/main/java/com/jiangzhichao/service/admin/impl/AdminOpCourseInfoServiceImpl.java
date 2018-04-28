package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.StuCourseMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.service.admin.AdminOpCourseInfoService;

@Service
@Transactional
public class AdminOpCourseInfoServiceImpl implements AdminOpCourseInfoService {

	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private StuCourseMapper stuCourseMapper;
	
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
		return courseMapper.select();
	}

	@Override
	public void importCourse(List<CourseDTO> courses) {
		for (CourseDTO courseDTO : courses) {
			courseMapper.insertSelective(courseDTO);
		}
	}

}
