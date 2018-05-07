package com.jiangzhichao.service.teacher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.TeacherMapper;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.service.teacher.TeacherInfoService;

/**
 * 教师信息Service
 * 
 * @author BornToWin
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TeacherInfoServiceImpl implements TeacherInfoService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public int updatePassword(TeacherDTO teacherDTO) {
		return teacherMapper.updateByPrimaryKeySelective(teacherDTO);
	}

}
