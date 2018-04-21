package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.StudentDO;

/**
 * ����ԱCRUDѧ����Ϣ
 * 
 * @author BornToWin
 *
 */
public interface AdminOpStudentInfoService {

	/**
	 * ����ѧ����Ϣ
	 * @param studentDO
	 * @return
	 */
	int insertStudent(StudentDO studentDO);
	
	/**
	 * ɾ��ѧ����Ϣ
	 * @param sno
	 * @return
	 */
	int deleteStudent(String sno);
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param studentDO
	 * @return
	 */
	int updateStudent(StudentDO studentDO);
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ��
	 * @param sno
	 * @return
	 */
	StudentDO selectStudentBySno(String sno);
	
	/**
	 * ��ѯ����ѧ����Ϣ
	 * @return
	 */
	List<StudentDO> selectAllStudent();
	
	/**
	 * ����ѧ����Ϣ�б�
	 * @param students
	 */
	void importStudent(List<StudentDO> students);
}
