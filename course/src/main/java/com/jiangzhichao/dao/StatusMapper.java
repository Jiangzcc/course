package com.jiangzhichao.dao;

import com.jiangzhichao.entity.Status;

/**
 * ѡ�ο����ر�-¼��ɼ������ر�״̬��ѯ���޸�
 * 
 * @author BornToWin
 *
 */
public interface StatusMapper {

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
