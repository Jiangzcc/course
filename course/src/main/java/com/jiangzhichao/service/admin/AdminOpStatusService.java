package com.jiangzhichao.service.admin;

import com.jiangzhichao.entity.Status;

/**
 * ѡ�ο����ر�-¼��ɼ������ر�״̬��ѯ���޸�Service
 * 
 * @author BornToWin
 *
 */
public interface AdminOpStatusService {
	
	/**
	 * ��ѯ״̬
	 * @return
	 */
	Status select();
	
	/**
	 * �޸�״̬
	 * @return
	 */
	int update(Status status);

}
