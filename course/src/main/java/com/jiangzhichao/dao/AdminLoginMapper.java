package com.jiangzhichao.dao;

import com.jiangzhichao.entity.AdminDO;

/**
 * ����Ա��½Mapper
 * 
 * @author BornToWin
 *
 */
public interface AdminLoginMapper {

	/**
	 * ͨ��username��ѯ����Ա��Ϣ
	 * @param ausername
	 * @return
	 */
	AdminDO selectByUsername(String ausername);
	
}
