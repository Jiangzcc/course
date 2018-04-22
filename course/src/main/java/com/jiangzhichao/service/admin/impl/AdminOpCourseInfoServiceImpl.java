package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseDOMapper;
import com.jiangzhichao.dao.StuCourseDOMapper;
import com.jiangzhichao.entity.CourseDO;
import com.jiangzhichao.entity.StuCourseDO;
import com.jiangzhichao.service.admin.AdminOpCourseInfoService;

@Service
@Transactional
public class AdminOpCourseInfoServiceImpl implements AdminOpCourseInfoService {

	@Autowired
	private CourseDOMapper courseDOMapper;
	
	@Autowired
	private StuCourseDOMapper stuCourseDOMapper;
	
	@Override
	public int insertCourse(CourseDO courseDO) {
		return courseDOMapper.insert(courseDO);
	}

	@Override
	public int deleteCourse(String cno) {
		int i = 0;
		List<StuCourseDO> cnos = stuCourseDOMapper.selectByCno(cno);
		if(cnos.size() == 0) {
			i = courseDOMapper.deleteByPrimaryKey(cno);
		}
		return i;
	}

	@Override
	public int updateCourse(CourseDO courseDO) {
		return courseDOMapper.updateByPrimaryKey(courseDO);
	}

	@Override
	public CourseDO selectCourseByCno(String cno) {
		return courseDOMapper.selectByPrimaryKey(cno);
	}

	@Override
	public List<CourseDO> selectAllCourse() {
		return courseDOMapper.select();
	}

	@Override
	public void importCourse(List<CourseDO> courses) {
		for (CourseDO courseDO : courses) {
			courseDOMapper.insertSelective(courseDO);
		}
	}

}
