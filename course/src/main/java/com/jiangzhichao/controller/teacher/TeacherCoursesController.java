package com.jiangzhichao.controller.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.CourseVO;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.service.teacher.TeacherCoursesService;

/**
 * 教师所授课程相关Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherCoursesController extends BaseController {

	@Autowired
	private TeacherCoursesService teacherCoursesService;
	
	private String term;
	@Value(value = "#{prop.term}")
	public void setTerm(String term) {  
		this.term = term;
	}
	
	@RequestMapping("/queryCourses")
	@ResponseBody
	public Map<String,List<CourseVO>> queryCourses(HttpSession session){
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		List<CourseVO> list = teacherCoursesService.queryCourseByTnoAndTerm(teacher.getTno(), term);
		Map<String,List<CourseVO>> map = new HashMap<String,List<CourseVO>>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/queryOldCourses")
	@ResponseBody
	public Map<String,List<CourseVO>> queryOldCourses(HttpSession session){
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		List<CourseVO> list = teacherCoursesService.queryOldCourseByTnoAndTerm(teacher.getTno(), term);
		Map<String,List<CourseVO>> map = new HashMap<String,List<CourseVO>>();
		map.put("data", list);
		return map;
	}
}
