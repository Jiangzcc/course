package com.jiangzhichao.service.teacher;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jiangzhichao.entity.StuCourseDTO;
import com.jiangzhichao.entity.StudentVO;

/**
 * 教师所授学生信息Service
 * 
 * @author BornToWin
 *
 */
public interface TeacherStudentsService {

	/**
	 * 教师所授专业下所有学生信息
	 * @param cno
	 * @param session
	 * @return
	 */
	List<StudentVO> queryStudentByCno(String cno,HttpSession session);
	
	/**
	 * 修改某学生成绩
	 * @param stuCourseDTO
	 * @param session
	 * @return
	 */
	int updateScore(StuCourseDTO stuCourseDTO,HttpSession session);
	
	/**
	 * 批量修改成绩
	 * @param snoScore 学号与成绩拼接而成的字符串
	 * @param cno
	 * @param session
	 */
	void bulkEditing(String snoScore,String cno,HttpSession session);
}
