package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseDOMapper;
import com.jiangzhichao.dao.SubjectRoleDOMapper;
import com.jiangzhichao.dao.TeacherDOMapper;
import com.jiangzhichao.entity.CourseDO;
import com.jiangzhichao.entity.SubjectRoleDOKey;
import com.jiangzhichao.entity.TeacherDO;
import com.jiangzhichao.service.admin.AdminOpTeacherInfoService;

@Service
@Transactional
public class AdminOpTeacherInfoServiceImpl implements AdminOpTeacherInfoService {

	@Autowired
	private TeacherDOMapper teacherDoMapper;
	
	@Autowired
	private SubjectRoleDOMapper subjectRoleDOMapper;
	
	@Autowired
	private CourseDOMapper courseDOMapper;
	
	@Override
	public int insertTeacher(TeacherDO teacherDO) {
		int i = teacherDoMapper.insert(teacherDO);
		//���ý�ɫ
		SubjectRoleDOKey subjectRoleDOKey = new SubjectRoleDOKey();
		subjectRoleDOKey.setNo(teacherDO.getTno());
		subjectRoleDOKey.setRoleno("2");
		subjectRoleDOMapper.insert(subjectRoleDOKey);
		return i;
	}

	@Override
	public int deleteTeacher(String tno) {
		//ɾ��ǰ�����ÿγ���Ϣ�д˽�ʦ���Ϊnull
		List<CourseDO> list = courseDOMapper.selectByTno(tno);
		for (CourseDO courseDO : list) {
			courseDO.setTno(null);
			courseDOMapper.updateByPrimaryKey(courseDO);
		}
		//ɾ����ɫ
		subjectRoleDOMapper.deleteByNo(tno);
		//ɾ���û�
		int i = teacherDoMapper.deleteByPrimaryKey(tno);
		return i;
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

	@Override
	public void importTeacher(List<TeacherDO> teachers) {
		//ѭ�������ʦ��Ϣ
		for (TeacherDO teacherDO : teachers) {
			teacherDoMapper.insert(teacherDO);
		}
	}

}
