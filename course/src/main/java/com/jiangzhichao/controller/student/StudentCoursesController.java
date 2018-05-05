package com.jiangzhichao.controller.student;

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
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.service.student.StudentCoursesService;

/**
 * 学生选课信息相关Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/student")
public class StudentCoursesController extends BaseController {

	@Autowired
	private StudentCoursesService studentCoursesService;
	
	private String term;
	@Value(value = "#{prop.term}")
	public void setTerm(String term) {  
		this.term = term;
	}
	
	@RequestMapping("/queryAllCourses")
	@ResponseBody
	public Map<String,List<CourseVO>> queryAllCourses(HttpSession session){
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		List<CourseVO> list = studentCoursesService.queryAllCourseByDnoAndTerm(student.getDno(), term);
		Map<String,List<CourseVO>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/queryOptions")
	@ResponseBody
	public Map<String,List<CourseVO>> queryOptions(HttpSession session){
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		List<CourseVO> list = studentCoursesService.queryOptionsBySno(student);
		Map<String,List<CourseVO>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/selectedCourses")
	@ResponseBody
	public Map<String,List<CourseVO>> selectedCourses(HttpSession session){
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		List<CourseVO> list = studentCoursesService.querySelectedBySno(student.getSno());
		Map<String,List<CourseVO>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/selectedOldCourses")
	@ResponseBody
	public Map<String,List<CourseVO>> selectedOldCourses(HttpSession session){
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		List<CourseVO> list = studentCoursesService.querySelectedOldBySno(student.getSno());
		Map<String,List<CourseVO>> map = new HashMap<>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/takeCourse")
	@ResponseBody
	public Map<String,Object> takeCourse(StuCourseDTO stuCourseDTO,HttpSession session){
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		stuCourseDTO.setSno(student.getSno());
		int i = studentCoursesService.takeCourse(stuCourseDTO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	@RequestMapping("/dropCourse")
	@ResponseBody
	public Map<String,Object> dropCourse(StuCourseDTO stuCourseDTO,HttpSession session){
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		stuCourseDTO.setSno(student.getSno());
		int i = studentCoursesService.dropCourse(stuCourseDTO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
}
