package com.jiangzhichao.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.DepartmentMapper;
import com.jiangzhichao.dao.StudentMapper;
import com.jiangzhichao.entity.DepartmentDTO;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.service.student.StudentInfoService;

@Service
@Transactional
public class StudentInfoServiceImpl implements StudentInfoService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public StudentDTO transDno(StudentDTO student) {
		DepartmentDTO departmentDTO = departmentMapper.selectByPrimaryKey(student.getDno());
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setSno(student.getSno());
		studentDTO.setDno(departmentDTO.getDname());
		studentDTO.setIdcard(student.getIdcard());
		studentDTO.setSname(student.getSname());
		studentDTO.setSsex(student.getSsex());
		return studentDTO;
	}

	@Override
	public int updatePassword(StudentDTO student) {
		return studentMapper.updateByPrimaryKeySelective(student);
	}
	
}
