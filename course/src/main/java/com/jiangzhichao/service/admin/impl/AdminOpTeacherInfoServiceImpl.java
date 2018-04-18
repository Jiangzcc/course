package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.TeacherDOMapper;
import com.jiangzhichao.entity.TeacherDO;
import com.jiangzhichao.service.admin.AdminOpTeacherInfoService;

@Service
@Transactional
public class AdminOpTeacherInfoServiceImpl implements AdminOpTeacherInfoService {

	@Autowired
	private TeacherDOMapper teacherDoMapper;
	
	@Override
	public int insertTeacher(TeacherDO teacherDO) {
		return teacherDoMapper.insert(teacherDO);
	}

	@Override
	public int deleteTeacher(String tno) {
		return teacherDoMapper.deleteByPrimaryKey(tno);
	}

	@Override
	public int updateTeacher(TeacherDO teacherDO) {
		return teacherDoMapper.updateByPrimaryKey(teacherDO);
	}

	@Override
	public TeacherDO selectTeacherByTno(String tno) {
		return teacherDoMapper.selectByPrimaryKey(tno);
	}

	@Override
	public List<TeacherDO> selectAllTeacher() {
		return teacherDoMapper.select();
	}

}
