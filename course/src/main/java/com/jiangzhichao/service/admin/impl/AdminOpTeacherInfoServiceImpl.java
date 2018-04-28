package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.SubjectRoleMapper;
import com.jiangzhichao.dao.TeacherMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.SubjectRoleDTOKey;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.service.admin.AdminOpTeacherInfoService;

@Service
@Transactional
public class AdminOpTeacherInfoServiceImpl implements AdminOpTeacherInfoService {

	@Autowired
	private TeacherMapper teacherDoMapper;
	
	@Autowired
	private SubjectRoleMapper subjectRoleMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public int insertTeacher(TeacherDTO teacherDTO) {
		int i = teacherDoMapper.insert(teacherDTO);
		//设置角色
		SubjectRoleDTOKey subjectRoleDTOKey = new SubjectRoleDTOKey();
		subjectRoleDTOKey.setNo(teacherDTO.getTno());
		subjectRoleDTOKey.setRoleno("2");
		subjectRoleMapper.insert(subjectRoleDTOKey);
		return i;
	}

	@Override
	public int deleteTeacher(String tno) {
		//删除前先设置课程信息中此教师编号为null
		List<CourseDTO> list = courseMapper.selectByTno(tno);
		for (CourseDTO courseDTO : list) {
			courseDTO.setTno(null);
			courseMapper.updateByPrimaryKey(courseDTO);
		}
		//删除角色
		subjectRoleMapper.deleteByNo(tno);
		//删除用户
		int i = teacherDoMapper.deleteByPrimaryKey(tno);
		return i;
	}

	@Override
	public int updateTeacher(TeacherDTO teacherDTO) {
		return teacherDoMapper.updateByPrimaryKey(teacherDTO);
	}

	@Override
	public TeacherDTO selectTeacherByTno(String tno) {
		return teacherDoMapper.selectByPrimaryKey(tno);
	}

	@Override
	public List<TeacherDTO> selectAllTeacher() {
		return teacherDoMapper.select();
	}

	@Override
	public void importTeacher(List<TeacherDTO> teachers) {
		//循环插入教师信息
		for (TeacherDTO teacherDTO : teachers) {
			this.insertTeacher(teacherDTO);
		}
	}

}
