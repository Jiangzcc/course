package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseDOMapper;
import com.jiangzhichao.dao.StuCourseDOMapper;
import com.jiangzhichao.dao.StudentDOMapper;
import com.jiangzhichao.dao.SubjectRoleDOMapper;
import com.jiangzhichao.entity.CourseDO;
import com.jiangzhichao.entity.StuCourseDO;
import com.jiangzhichao.entity.StudentDO;
import com.jiangzhichao.entity.SubjectRoleDOKey;
import com.jiangzhichao.service.admin.AdminOpStudentInfoService;

@Service
@Transactional
public class AdminOpStudentInfoServiceImpl implements AdminOpStudentInfoService {

	@Autowired
	private StudentDOMapper studentDOMapper;
	
	@Autowired
	private SubjectRoleDOMapper subjectRoleDOMapper;
	
	@Autowired
	private StuCourseDOMapper stuCourseDOMapper;
	
	@Autowired
	private CourseDOMapper courseDOMapper;
	
	@Override
	public int insertStudent(StudentDO studentDO) {
		//���ý�ɫ
		SubjectRoleDOKey subjectRoleDOKey = new SubjectRoleDOKey();
		subjectRoleDOKey.setNo(studentDO.getSno());
		subjectRoleDOKey.setRoleno("3");
		subjectRoleDOMapper.insert(subjectRoleDOKey);
		return studentDOMapper.insert(studentDO);
	}

	@Override
	public int deleteStudent(String sno) {
		//��ѯ��ѧ������ѡ����Ϣ
		List<StuCourseDO> list = stuCourseDOMapper.selectBySno(sno);
		//��ѧ����ѡ��γ� �γ�����-1
		for (StuCourseDO stuCourseDO : list) {
			CourseDO courseDO = courseDOMapper.selectByPrimaryKey(stuCourseDO.getCno());
			courseDO.setCurrentnum(courseDO.getCurrentnum()-1);
			courseDOMapper.updateByPrimaryKey(courseDO);
		}
		//ɾ����ɫ
		subjectRoleDOMapper.deleteByNo(sno);
		//ɾ����ѧ������ѡ����Ϣ
		stuCourseDOMapper.deleteBySno(sno);
		return studentDOMapper.deleteByPrimaryKey(sno);
	}

	@Override
	public int updateStudent(StudentDO studentDO) {
		return studentDOMapper.updateByPrimaryKey(studentDO);
	}

	@Override
	public StudentDO selectStudentBySno(String sno) {
		return studentDOMapper.selectByPrimaryKey(sno);
	}

	@Override
	public List<StudentDO> selectAllStudent() {
		return studentDOMapper.select();
	}

	@Override
	public void importStudent(List<StudentDO> students) {
		for (StudentDO studentDO : students) {
			this.insertStudent(studentDO);
		}
	}

}
