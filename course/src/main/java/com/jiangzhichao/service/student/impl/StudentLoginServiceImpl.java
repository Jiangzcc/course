package com.jiangzhichao.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.StudentDOMapper;
import com.jiangzhichao.entity.StudentDO;
import com.jiangzhichao.service.student.StudentLoginService;

@Service
@Transactional
public class StudentLoginServiceImpl implements StudentLoginService {
	
	@Autowired
	private StudentDOMapper studentDOMapper;

	@Override
	public StudentDO queryStudent(String sno, String spassword) {
		StudentDO studentDO = studentDOMapper.selectByPrimaryKey(sno);
		//ѧ�Ŵ���
		if(null != studentDO) {
			//������ȷ
			if(studentDO.getSpassword().equals(spassword)) {
				return studentDO;
			}
		}
		return null;
	}

}
