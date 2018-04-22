package com.jiangzhichao.service.admin;

import java.util.List;

import com.jiangzhichao.entity.CourseDO;

/**
 * ����Ա�����γ���ϢService
 * 
 * @author BornToWin
 *
 */
public interface AdminOpCourseInfoService {

	/**
	 * �����γ���Ϣ
	 * @param courseDO
	 * @return
	 */
	int insertCourse(CourseDO courseDO);
	
	/**
	 * ɾ���γ���Ϣ
	 * @param cno
	 * @return
	 */
	int deleteCourse(String cno);
	
	/**
	 * �޸Ŀγ���Ϣ
	 * @param courseDO
	 * @return
	 */
	int updateCourse(CourseDO courseDO);
	
	/**
	 * ͨ���γ̱�Ų�ѯ�γ���Ϣ
	 * @param cno
	 * @return
	 */
	CourseDO selectCourseByCno(String cno);
	
	/**
	 * ��ѯ���пγ���Ϣ
	 * @return
	 */
	List<CourseDO> selectAllCourse();
	
	/**
	 * �����ʦ��Ϣ
	 */
	void importCourse(List<CourseDO> courses);
}
