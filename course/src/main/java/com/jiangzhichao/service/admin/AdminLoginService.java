package com.jiangzhichao.service.admin;

import com.jiangzhichao.entity.AdminDO;

/**
 * ����Ա��½Service
 * 
 * @author BornToWin
 *
 */
public interface AdminLoginService {
	
	/**
	 * ͨ���û����������ѯ����Ա��Ϣ
	 * @param username
	 * @param password
	 * @return
	 */
	AdminDO queryAdmin(String username,String password);

}
