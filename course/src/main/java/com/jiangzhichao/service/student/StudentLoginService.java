package com.jiangzhichao.service.student;

import com.jiangzhichao.entity.StudentDO;

/**
 * ѧ����½Service
 * 
 * @author BornToWin
 *
 */
public interface StudentLoginService {

	/**
	 * ͨ��ѧ�ź������ѯѧ����Ϣ
	 * @param sno
	 * @param spassword
	 * @return
	 */
	StudentDO queryStudent(String sno,String spassword);
}
