package com.jiangzhichao.service.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.StatusMapper;
import com.jiangzhichao.dao.StuCourseMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.CourseVO;
import com.jiangzhichao.entity.Status;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.service.student.StudentCoursesService;

@Service
@Transactional
public class StudentCoursesServiceImpl implements StudentCoursesService {

	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private StuCourseMapper stuCourseMapper;
	
	@Autowired
	private StatusMapper statusMapper;
	
	@Override
	public List<CourseVO> queryAllCourseByDnoAndTerm(String dno, String term) {
		return courseMapper.selectByDnoAndTerm(dno, term);
	}

	@Override
	public List<CourseVO> queryOptionsBySno(StudentDTO student) {
		return courseMapper.selectBySno(student);
	}

	@Override
	public List<CourseVO> querySelectedBySno(String sno) {
		return courseMapper.selectedBySno(sno);
	}

	@Override
	public List<CourseVO> querySelectedOldBySno(String sno) {
		return courseMapper.selectedOldBySno(sno);
	}

	@Override
	public int takeCourse(StuCourseDTO stuCourseDTO) {
		int i = 0;
		Status status = statusMapper.select();
		//选课状态
		if(status.getChoice()) {
			Integer credits = courseMapper.totalCredits(stuCourseDTO.getSno());
			//学分
			if(credits < 25) {
				CourseDTO courseDTO = courseMapper.selectByPrimaryKey(stuCourseDTO.getCno());
				//选课人数未满
				if(courseDTO.getCurrentnum()<courseDTO.getMaxnum()) {
					i = stuCourseMapper.insertSelective(stuCourseDTO);
					courseDTO.setCurrentnum(courseDTO.getCurrentnum()+1);
					courseMapper.updateByPrimaryKeySelective(courseDTO);
				}
			}
		}
		return i;
	}

	@Override
	public int dropCourse(StuCourseDTO stuCourseDTO) {
		int i = 0;
		Status status = statusMapper.select();
		//选课状态
		if(status.getChoice()) {
			CourseDTO courseDTO = courseMapper.selectByPrimaryKey(stuCourseDTO.getCno());
			courseDTO.setCurrentnum(courseDTO.getCurrentnum()-1);
			courseMapper.updateByPrimaryKeySelective(courseDTO);
			i = stuCourseMapper.deleteBySnoAndCno(stuCourseDTO);
		}
		return i;
	}

}
