package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseDOMapper;
import com.jiangzhichao.dao.DepartmentDOMapper;
import com.jiangzhichao.dao.StudentDOMapper;
import com.jiangzhichao.entity.CourseDO;
import com.jiangzhichao.entity.DepartmentDO;
import com.jiangzhichao.entity.StudentDO;
import com.jiangzhichao.service.admin.AdminOpDepartmentService;

@Service
@Transactional
public class AdminOpDepartmentServiceImpl implements AdminOpDepartmentService {

	@Autowired
	private DepartmentDOMapper departmentDOMapper;
	
	@Autowired
	private CourseDOMapper courseDOMapper;
	
	@Autowired
	private StudentDOMapper studentDOMapper;
	
	@Override
	public int insertDepartment(DepartmentDO departmentDO) {
		return departmentDOMapper.insert(departmentDO);
	}

	@Override
	public int deleteDepartment(String dno) {
		int i = 0;
		//判断此专业下是否有课程
		List<CourseDO> courses = courseDOMapper.selectByDno(dno);
		if(courses.size() == 0) {
			//删除前先判断此专业下是否有学生
			List<StudentDO> students = studentDOMapper.selectByDno(dno);
			if(students.size() == 0) {
				//删除专业信息
				i = departmentDOMapper.deleteByPrimaryKey(dno);
			}
		}
		return i;
	}

	@Override
	public int updateDepartment(DepartmentDO departmentDO) {
		return departmentDOMapper.updateByPrimaryKey(departmentDO);
	}

	@Override
	public DepartmentDO selectDepartmentByDno(String dno) {
		return departmentDOMapper.selectByPrimaryKey(dno);
	}

	@Override
	public List<DepartmentDO> selectAllDepartment() {
		return departmentDOMapper.select();
	}

}
