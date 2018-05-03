package com.jiangzhichao.service.teacher.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiangzhichao.dao.CourseMapper;
import com.jiangzhichao.dao.StatusMapper;
import com.jiangzhichao.dao.StuCourseMapper;
import com.jiangzhichao.dao.StudentMapper;
import com.jiangzhichao.entity.CourseDTO;
import com.jiangzhichao.entity.Status;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentVO;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.service.teacher.TeacherStudentsService;

@Service
@Transactional
public class TeacherStudentsServiceImpl implements TeacherStudentsService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private StuCourseMapper stuCourseMapper;
	
	@Autowired
	private StatusMapper statusMapper;
	
	@Override
	public List<StudentVO> queryStudentByCno(String cno,HttpSession session) {
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		CourseDTO courseDTO = courseMapper.selectByPrimaryKey(cno);
		// 自己只能查询自己所授课程中的学生信息
		// 防止教师直接通过url查询
		if(teacher.getTno().equals(courseDTO.getTno())) {
			List<StudentVO> list = studentMapper.selectByCno(cno);
			if(list.size() == 1) {
				if(list.get(0) == null) {
					return null;
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public int updateScore(StuCourseDTO stuCourseDTO,HttpSession session) {
		int i = 0;
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		CourseDTO courseDTO = courseMapper.selectByPrimaryKey(stuCourseDTO.getCno());
		// 自己只能修改自己所授课程中的学生成绩
		// 防止教师直接通过url修改
		if(teacher.getTno().equals(courseDTO.getTno())) {
			Status status = statusMapper.select();
			// 当前状态可以录入成绩
			if(status.getEntry()) {
				i = stuCourseMapper.updateScore(stuCourseDTO);
			}
		}
		return i;
	}

	@Override
	public void bulkEditing(String sno_score, String cno, HttpSession session) {
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		CourseDTO courseDTO = courseMapper.selectByPrimaryKey(cno);
		// 自己只能修改自己所授课程中的学生成绩
		// 防止教师直接通过url修改
		if(teacher.getTno().equals(courseDTO.getTno())) {
			Status status = statusMapper.select();
			// 当前状态可以录入成绩
			if(status.getEntry()) {
				StuCourseDTO stuCourseDTO = new StuCourseDTO();
				stuCourseDTO.setCno(cno);
				String[] scs = sno_score.split("&");
				for (String sc : scs) {
					String[] split = sc.split("_");
					if(split.length == 2) {
						stuCourseDTO.setSno(split[0]);
						stuCourseDTO.setScore(Integer.valueOf(split[1]));
						stuCourseMapper.updateScore(stuCourseDTO);
					} else {
						stuCourseDTO.setSno(split[0]);
						stuCourseDTO.setScore(null);
						stuCourseMapper.updateScore(stuCourseDTO);
					}
				}
			}
		}
	}

}
