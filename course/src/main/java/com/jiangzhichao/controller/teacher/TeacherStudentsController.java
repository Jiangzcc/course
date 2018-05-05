package com.jiangzhichao.controller.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentVO;
import com.jiangzhichao.service.teacher.TeacherStudentsService;

/**
 * 教师所授课程中学生相关Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherStudentsController extends BaseController {

	@Autowired
	private TeacherStudentsService teacherStudentsService;
	
	@RequestMapping("queryStudentsByCno")
	@ResponseBody
	public Map<String,Object> queryStudentsByCno(String cno,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		List<StudentVO> list = teacherStudentsService.queryStudentByCno(cno,session);
		if(list == null) {
			map.put("data", "");
			return map;
		} else {
			for (StudentVO studentVO : list) {
				if(studentVO.getScore() == null) {
					studentVO.setScore(-1);
				}
			}
			map.put("data", list);
			return map;
		}
	}
	
	@RequestMapping("scoreRegistration")
	@ResponseBody
	public Map<String,Object> scoreRegistration(StuCourseDTO stuCourseDTO,HttpSession session){
		int i = teacherStudentsService.updateScore(stuCourseDTO, session);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	@RequestMapping("bulkEditing")
	@ResponseBody
	public Map<String,Object> bulkEditing(String sno_score,String cno,HttpSession session){
		teacherStudentsService.bulkEditing(sno_score, cno, session);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}
	
	
}
