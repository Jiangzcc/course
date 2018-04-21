package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.TeacherDO;

/**
 * ����ԱCRUD��ʦ��Ϣ
 * 
 * @author BornToWin
 *
 */
public interface AdminOpTeacherInfoService {
	
	/**
	 * ������ʦ��Ϣ
	 * @param teacherDO
	 * @return
	 */
	int insertTeacher(TeacherDO teacherDO);
	
	/**
	 * ɾ����ʦ��Ϣ
	 * @param tno
	 * @return
	 */
	int deleteTeacher(String tno);
	
	/**
	 * �޸Ľ�ʦ��Ϣ
	 * @param teacherDO
	 * @return
	 */
	int updateTeacher(TeacherDO teacherDO);
	
	/**
	 * ͨ����ʦ��Ų�ѯ��ʦ��Ϣ
	 * @param tno
	 * @return
	 */
	TeacherDO selectTeacherByTno(String tno);
	
	/**
	 * ��ѯ���н�ʦ��Ϣ
	 * @return
	 */
	List<TeacherDO> selectAllTeacher();
	
	/**
	 * �����ʦ��Ϣ�б�
	 * @param teachers
	 */
	void importTeacher(List<TeacherDO> teachers);

}
