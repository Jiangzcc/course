package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.DepartmentMapper;
import com.jiangzhichao.dao.StudentMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.DepartmentDTO;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.service.admin.AdminOpDepartmentService;

@Service
@Transactional
public class AdminOpDepartmentServiceImpl implements AdminOpDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public int insertDepartment(DepartmentDTO departmentDTO) {
		return departmentMapper.insert(departmentDTO);
	}

	@Override
	public int deleteDepartment(String dno) {
		int i = 0;
		//判断此专业下是否有课程
		List<CourseDTO> courses = courseMapper.selectByDno(dno);
		if(courses.size() == 0) {
			//删除前先判断此专业下是否有学生
			List<StudentDTO> students = studentMapper.selectByDno(dno);
			if(students.size() == 0) {
				//删除专业信息
				i = departmentMapper.deleteByPrimaryKey(dno);
			}
		}
		return i;
	}

	@Override
	public int updateDepartment(DepartmentDTO departmentDTO) {
		return departmentMapper.updateByPrimaryKey(departmentDTO);
	}

	@Override
	public DepartmentDTO selectDepartmentByDno(String dno) {
		return departmentMapper.selectByPrimaryKey(dno);
	}

	@Override
	public List<DepartmentDTO> selectAllDepartment() {
		return departmentMapper.select();
	}

}
