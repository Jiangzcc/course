package com.jiangzhichao.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.StudentMapper;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.service.student.StudentLoginService;

@Service
@Transactional
public class StudentLoginServiceImpl implements StudentLoginService {
	
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public StudentDTO queryStudent(String sno, String spassword) {
		StudentDTO studentDTO = studentMapper.selectByPrimaryKey(sno);
		//学号存在
		if(null != studentDTO) {
			//密码正确
			if(studentDTO.getSpassword().equals(spassword)) {
				return studentDTO;
			}
		}
		return null;
	}

}
