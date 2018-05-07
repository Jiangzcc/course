package com.jiangzhichao.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.DepartmentMapper;
import com.jiangzhichao.dao.StuCourseMapper;
import com.jiangzhichao.dao.StudentMapper;
import com.jiangzhichao.dao.SubjectRoleMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.DepartmentDTO;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.entity.SubjectRoleDTO;
import com.jiangzhichao.service.admin.AdminOpStudentInfoService;

/**
 * 管理员CRUD学生信息
 * 
 * @author BornToWin
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminOpStudentInfoServiceImpl implements AdminOpStudentInfoService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private SubjectRoleMapper subjectRoleMapper;
	
	@Autowired
	private StuCourseMapper stuCourseMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public int insertStudent(StudentDTO studentDTO) {
		//设置角色
		SubjectRoleDTO subjectRoleDTO = new SubjectRoleDTO();
		subjectRoleDTO.setNo(studentDTO.getSno());
		subjectRoleDTO.setRoleno("3");
		subjectRoleMapper.insert(subjectRoleDTO);
		return studentMapper.insert(studentDTO);
	}

	@Override
	public int deleteStudent(String sno) {
		//查询此学生所有选课信息
		List<StuCourseDTO> list = stuCourseMapper.selectBySno(sno);
		//此学生曾选择课程 课程人数-1
		for (StuCourseDTO stuCourseDTO : list) {
			CourseDTO courseDTO = courseMapper.selectByPrimaryKey(stuCourseDTO.getCno());
			courseDTO.setCurrentnum(courseDTO.getCurrentnum()-1);
			courseMapper.updateByPrimaryKey(courseDTO);
		}
		//删除角色
		subjectRoleMapper.deleteByNo(sno);
		//删除此学生所有选课信息
		stuCourseMapper.deleteBySno(sno);
		//删除学生
		return studentMapper.deleteByPrimaryKey(sno);
	}

	@Override
	public int updateStudent(StudentDTO studentDTO) {
		return studentMapper.updateByPrimaryKey(studentDTO);
	}

	@Override
	public StudentDTO selectStudentBySno(String sno) {
		return studentMapper.selectByPrimaryKey(sno);
	}

	@Override
	public List<StudentDTO> selectAllStudent() {
		List<StudentDTO> students = studentMapper.select();
		//替换专业编号为专业名称--懒得改SQL
		List<DepartmentDTO> depts = departmentMapper.select();
		for (StudentDTO studentDTO : students) {
			for (DepartmentDTO departmentDTO : depts) {
				if(departmentDTO.getDno().equals(studentDTO.getDno())){
					studentDTO.setDno(departmentDTO.getDname());
				}
			}
		}
		return students;
	}

	@Override
	public void importStudent(List<StudentDTO> students) {
		for (StudentDTO studentDTO : students) {
			this.insertStudent(studentDTO);
		}
	}

}
