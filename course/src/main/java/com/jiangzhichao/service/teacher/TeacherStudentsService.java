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
	 * @return
	 */
	List<StudentVO> queryStudentByCno(String cno,HttpSession session);
	
	/**
	 * 修改某学生成绩
	 * @param stuCourseDTO
	 * @return
	 */
	int updateScore(StuCourseDTO stuCourseDTO,HttpSession session);
	
	/**
	 * 批量修改成绩
	 * @param sno_score 学号与成绩拼接而成的字符串
	 * @param cno
	 * @param session
	 */
	void bulkEditing(String sno_score,String cno,HttpSession session);
}
